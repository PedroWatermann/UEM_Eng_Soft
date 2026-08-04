from sys import argv
import os

arquivoBase = 'games.dat'
listaInvertidaIndicesPrimarios = []

def main(args):
    if len(args) <= 0:
        raise IndexError('Nenhum argumento fornecido')
    
    match args[0]:
        case '-b':
            construirIndicePrimario(0, 'primario.ind')
            listaIndSecPosPriGen = construirIndiceSecundario(0, 3, 'genero.ind')
            listaIndSecPosPriPub = construirIndiceSecundario(0, 4, 'publicadora.ind')
            construirListaInvertida(listaIndSecPosPriGen, listaIndSecPosPriPub, 'listainvertida.lst')
        case '-e':
            if len(args) < 2:
                raise ValueError('Está faltando o argumento "nome_do_arquivo"')
            executarOperacoes(args[1])
        case '-c':
            compactar()
        case _:
            raise ValueError('O argumento fornecido é inválido')

#region Construção de índices
def construirIndicePrimario(campoChave, nomeArquivoSaida):
    with open(arquivoBase, 'rb') as arq:
        chave = ''
        contador = 0
        listaFinal = []

        tam = int.from_bytes(arq.read(2), 'little')
        while tam:
            byteOffset = arq.tell()
            chave = arq.read(tam).decode().split('|')[campoChave]
            
            listaFinal.append([chave, byteOffset - 2])
            listaInvertidaIndicesPrimarios.append([chave, contador])
            contador += 1
                
            tam = int.from_bytes(arq.read(2), 'little')
    
    listaFinal.sort()
    escreverArquivo(listaFinal, nomeArquivoSaida)

def construirIndiceSecundario(campoChavePrimaria, campoChave, nomeArquivoSaida):
    with open(arquivoBase, 'rb') as arq:
        listaFinal = []
        lista_ind_sec = []
        lista_ind_pri_sec = []
        listaIndSecPosPri = []
        
        # Preenchendo as listas com as relações de todos os indices primário e secundário e a lista de indices secundários sem repetição
        tam_reg = int.from_bytes(arq.read(2), 'little')
        while tam_reg:    
            reg = arq.read(tam_reg).decode().split('|')
            chave_secundaria = reg[campoChave]
            chave_primaria = reg[campoChavePrimaria]
            lista_ind_pri_sec.append([chave_primaria, chave_secundaria])

            if chave_secundaria not in lista_ind_sec:
                lista_ind_sec.append(chave_secundaria)
                
            tam_reg = int.from_bytes(arq.read(2), 'little')

        # Ordenando as listas para facilitar as comparações
        lista_ind_sec.sort()
        lista_ind_pri_sec.sort()
        listaInvIndPri = sorted(listaInvertidaIndicesPrimarios)

        for i in range(len(lista_ind_sec)):
            listaTemp = []
            for j in range(len(lista_ind_pri_sec)):
                if lista_ind_pri_sec[j][1] == lista_ind_sec[i]:
                    for indicePrimario in listaInvIndPri:
                        if lista_ind_pri_sec[j][0] == indicePrimario[0]:
                            listaTemp.append(indicePrimario[1])
                            break
            indSec = lista_ind_sec[i]
            listaIndSecPosPri.append([indSec, listaTemp])
            listaFinal.append([indSec, listaTemp[0]])
    
    escreverArquivo(listaFinal, nomeArquivoSaida)
    return listaIndSecPosPri

def construirListaInvertida(listaIndSecPosPriGen, listaIndSecPosPriPub, nomeArquivo):
    listaFinal = []
    
    for indice in listaInvertidaIndicesPrimarios:
        listaFinal.append([indice[0]])
    
    # Aqui preenhemos a lista invertida com o prox de cada índice secundário
    preencherListaInvertidaFinal(listaIndSecPosPriGen, listaFinal)
    preencherListaInvertidaFinal(listaIndSecPosPriPub, listaFinal)
    
    escreverArquivo(listaFinal, nomeArquivo)
#endregion

#region CRUD

def executarOperacoes(nomeArquivoOperacoes):
    indicePrimario, indiceSecGen, indiceSecPub, listaInvertida = carregarIndicesParaMemoria()
    
    linhasOperacoes = []
    try:
        with open(nomeArquivoOperacoes, 'r') as f:
            linhasOperacoes = f.readlines()
    except UnicodeDecodeError:
        with open(nomeArquivoOperacoes, 'rb') as f:
            for linha in f.readlines():
                linhasOperacoes.append(linha.decode())
        
    for linha in linhasOperacoes:
        linha = linha.strip()
        if not linha:
            continue
            
        partes = linha.split(' ', 1)
        comando = partes[0]
        argumento = partes[1] if len(partes) > 1 else ''

        print() # separador top
        
        match comando:
            case 'i':
                inserir(argumento, indicePrimario, indiceSecGen, indiceSecPub, listaInvertida)
            case 'bp':
                offset = buscarIndicePrimario(argumento, indicePrimario)
                print(f'Busca pelo registro de ID "{argumento}"')
                if offset != -1:
                    mostrarRegistro([offset])
                else:
                    print('Registro não encontrado!')
            case 'bs1':
                offsets = buscarIndiceSecundario(argumento, indiceSecGen, listaInvertida, indicePrimario, 1)
                print(f'Busca por registros de gênero "{argumento}" ({len(offsets)} registros)')
                if len(offsets) > 0:
                    mostrarRegistro(offsets)
            case 'bs2':
                offsets = buscarIndiceSecundario(argumento, indiceSecPub, listaInvertida, indicePrimario, 2)
                print(f'Busca por registros de publicadora "{argumento}" ({len(offsets)} registros)')
                if len(offsets) > 0:
                    mostrarRegistro(offsets)
            case 'r':
                remover(argumento, indicePrimario, listaInvertida)
            case _:
                print(f'Comando inválido: {comando}')
                
    salvarIndicesNoDisco(indicePrimario, indiceSecGen, indiceSecPub, listaInvertida)

def buscarIndicePrimario(chave, indicePrimario):
    indiceByteOffset = buscaBinaria(chave, indicePrimario)

    if indiceByteOffset != -1:
        byteOffset = int(indicePrimario[indiceByteOffset][1])
        return byteOffset
    else:
        return -1
    
def buscarIndiceSecundario(chaveSecundaria, indiceSecundario, listaInvertida, indicePrimario, colunaListaInvertida):
    posicaoCabeca = buscaBinaria(chaveSecundaria, indiceSecundario)
    
    if posicaoCabeca == -1:
        return []

    rrnAtual = int(indiceSecundario[posicaoCabeca][1])
    byteOffsetsEncontrados = []

    while rrnAtual != -1:
        chavePrimaria = listaInvertida[rrnAtual][0]
        
        if chavePrimaria != '*':
            posicaoPrimario = buscaBinaria(chavePrimaria, indicePrimario)
            if posicaoPrimario != -1:
                byteOffset = int(indicePrimario[posicaoPrimario][1])
                byteOffsetsEncontrados.append(byteOffset)
                
        rrnAtual = int(listaInvertida[rrnAtual][colunaListaInvertida])

    return byteOffsetsEncontrados

def mostrarRegistro(byteOffsets):
    with open('games.dat', 'rb') as f:
        for offset in byteOffsets:

            if offset == -1:
                continue

            f.seek(offset)
            tam = int.from_bytes(f.read(2), 'little')
            registro = f.read(tam).decode()
            print(registro)


def inserir(registro, indicePrimario, indiceSecGen, indiceSecPub, listaInvertida):
    partesRegistro = registro.split('|')
    chavePrimaria = partesRegistro[0]
    genero = partesRegistro[3]  
    publicadora = partesRegistro[4]
    
    posicao = buscaBinaria(chavePrimaria, indicePrimario)
    if posicao != -1:
        print('Registro já existe!')
        return

    with open(arquivoBase, 'ab') as f:
        byteOffset = f.tell()
        registroBytes = registro.encode()
        tamRegistro = len(registroBytes)
        f.write(tamRegistro.to_bytes(2, 'little'))
        f.write(registroBytes)

    print(f'Inserção do registro de chave "{chavePrimaria}" ({len(registroBytes)} bytes)')

    indicePrimario.append([chavePrimaria, str(byteOffset)])
    
    indicePrimario.sort(key=lambda x: x[0]) # ordena pela chave primária (ID)
    
    novoRrn = len(listaInvertida)
    
    listaInvertida.append([chavePrimaria, '-1', '-1'])

    atualizarPonteirosSecundarios(genero, indiceSecGen, listaInvertida, 1, novoRrn)
    
    atualizarPonteirosSecundarios(publicadora, indiceSecPub, listaInvertida, 2, novoRrn)



def remover(chave, indicePrimario, listaInvertida):
    
    posicaoPrimario = buscaBinaria(chave, indicePrimario)
    
    if posicaoPrimario == -1:
        print(f'Remoção do registro de chave "{chave}"')
        print('Registro não encontrado!')
        return
        
    byteOffset = int(indicePrimario[posicaoPrimario][1])
    print(f'Remoção do registro de chave "{chave}" (offset = {byteOffset})')
    
    with open(arquivoBase, 'r+b') as f:
        f.seek(byteOffset)
        f.read(2) 
        f.write(b'*')
        
    indicePrimario.pop(posicaoPrimario)
    
    for i in range(len(listaInvertida)):
        if listaInvertida[i][0] == chave:
            listaInvertida[i][0] = '*'
            break
#endregion

def compactar():
    with open(arquivoBase, 'rb') as arquivo_original:
        tam_bytes = arquivo_original.read(2)
        tam = int.from_bytes(tam_bytes, 'little')
        
        with open("temp.dat", 'wb') as saida:
            while tam_bytes:
                registro = arquivo_original.read(tam).decode()
                if registro[0] != '*':
                    saida.write(tam_bytes + registro.encode())             
                tam_bytes = arquivo_original.read(2)
                tam = int.from_bytes(tam_bytes,'little')
                
    os.remove(arquivoBase) 
    os.rename("temp.dat", arquivoBase)
    
    if os.path.exists('primario.ind'):
        construirIndicePrimario(0, 'primario.ind')

#region Funções auxiliares

def carregarIndicesParaMemoria():
    arquivosNecessarios = [
        'games.dat', 
        'primario.ind', 
        'genero.ind', 
        'publicadora.ind', 
        'listainvertida.lst'
    ]
    
    for nomeArquivo in arquivosNecessarios:
        if not os.path.exists(nomeArquivo):
            raise ValueError(f'Erro: O arquivo "{nomeArquivo}" não existe. Execute o modo de criação de índices (-b) primeiro.')

    indicePrimario = lerArquivo('primario.ind')
    indiceSecGen = lerArquivo('genero.ind')
    indiceSecPub = lerArquivo('publicadora.ind')
    listaInvertida = lerArquivo('listainvertida.lst')

    return indicePrimario, indiceSecGen, indiceSecPub, listaInvertida

def lerArquivo(nomeArquivo):
        listaRetorno = [] 
        with open(nomeArquivo, 'rb') as f:
            linhas = f.readlines()
            for linha in linhas:
                linhaDecodificada = linha.decode().strip()
                if linhaDecodificada: # Garante que não vai ler linhas vazias
                    listaRetorno.append(linhaDecodificada.split('|'))
        return listaRetorno

def salvarIndicesNoDisco(indicePrimario, indiceSecGen, indiceSecPub, listaInvertida):
    def escreverArquivo(dados, nomeArquivo):
        with open(nomeArquivo, 'wb') as f:
            for registro in dados:
                linhaFormatada = '|'.join(map(str, registro)) + '\n'
                f.write(linhaFormatada.encode())

    escreverArquivo(indicePrimario, 'primario.ind')
    escreverArquivo(indiceSecGen, 'genero.ind')
    escreverArquivo(indiceSecPub, 'publicadora.ind')
    escreverArquivo(listaInvertida, 'listainvertida.lst')

def atualizarPonteirosSecundarios(chaveSecundaria, indiceSecundario, listaInvertida, colunaListaInvertida, novoRrn):
    posicao = buscaBinaria(chaveSecundaria, indiceSecundario)
    
    if posicao == -1:
        indiceSecundario.append([chaveSecundaria, str(novoRrn)])
        indiceSecundario.sort(key=lambda x: x[0])
    else:
        rrnAtual = int(indiceSecundario[posicao][1])
        rrnAnterior = -1
        
        while rrnAtual != -1:
            rrnAnterior = rrnAtual
            rrnAtual = int(listaInvertida[rrnAtual][colunaListaInvertida])
            
        listaInvertida[rrnAnterior][colunaListaInvertida] = str(novoRrn)

def buscaBinaria(chave, lista):
    inicio = 0
    fim = len(lista) - 1

    while inicio <= fim:
        meio = (inicio + fim) // 2
        if lista[meio][0] == chave:
            return meio
        elif lista[meio][0] < chave:
            inicio = meio + 1
        else:
            fim = meio - 1

    return -1

def converterListaDeLinhasEmListaDeListas(linhas):
    listaFinal = []
    for linha in linhas:
        listaFinal.append(linha.decode().split('|'))
    return listaFinal

def preencherListaInvertidaFinal(listaIndicesSecundarios, listaFinal):
    for i in range(len(listaIndicesSecundarios)):
        listaPosIndPri = listaIndicesSecundarios[i][1]
        for j in range(len(listaPosIndPri)):
            for k in range(len(listaFinal)):
                if k == listaPosIndPri[j]:
                    if j + 1 == len(listaPosIndPri):
                        listaFinal[k].append(-1)
                    else:
                        listaFinal[k].append(listaPosIndPri[j + 1])

def escreverArquivo(dados, nomeArquivo):
    listaFinal = []
    tamListaPai = len(dados)
    for i in range(tamListaPai):
        tamListaFilha = len(dados[i])
        registro = ''
        for j in range(tamListaFilha):
            if j == tamListaFilha - 1:
                registro += str(dados[i][j]) + '\n'
            else:
                registro += str(dados[i][j]) + '|'
        listaFinal.append(registro.encode())
        
    with open(nomeArquivo, 'wb') as arq:
        arq.writelines(listaFinal)
#endregion

if __name__ == '__main__':
    try:
        main(argv[1:])
    except IndexError as ex:
        print(f'\033[31mErro de acesso a índice: {ex}\033[0m')
    except ValueError as ex:
        print(f'\033[31mErro de valor: {ex}\033[0m')
    except Exception as ex:
        print(f'\033[31mErro inesperado: {ex}\033[0m')

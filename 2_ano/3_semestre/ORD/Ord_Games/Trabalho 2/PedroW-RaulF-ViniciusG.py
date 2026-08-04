from sys import argv
from enum import Enum
from struct import pack, unpack, calcsize

import os

ORDEM = 4
NULO = -1
RAIZ = 0

FORMATO_PAG = f'i{ORDEM - 1}i{ORDEM - 1}i{ORDEM}i'
FORMATO_CAB = 'i'
FORMATO_TAM = 'h'
TAM_PAG = calcsize(FORMATO_PAG)
TAM_CAB = calcsize(FORMATO_CAB)
TAM_TAM = calcsize(FORMATO_TAM)

class Cores(Enum):
    RESET = '\033[0m'
    PRETO = '\033[30m'
    VERMELHO = '\033[31m'
    VERDE = '\033[32m'
    AMARELO = '\033[33m'
    AZUL = '\033[34m'
    MAGENTA = '\033[35m'
    CIANO = '\033[36m'
    BRANCO_CINZA = '\033[37m'

class Arquivos(Enum):
    BASE = 'games.dat'
    ARVORE = 'btree.dat'

class Pagina:
    def __init__(
        self, 
        numChaves: int = 0, 
        chavesPai: list = [NULO] * (ORDEM - 1),
        rrnReg: list = [NULO] * (ORDEM - 1), 
        filhos: list = [NULO] * ORDEM
    ):
        self.numChaves: int = numChaves
        self.chavesPai: list = chavesPai
        self.rrnReg: list = rrnReg
        self.filhos: list = filhos

def main(args):
    if len(args) <= 0:
        raise IndexError(f'{Cores.VERMELHO.value}Nenhum argumento fornecido{Cores.RESET.value}')
    
    if ORDEM <= 1:
        raise ValueError(f'{Cores.VERMELHO.value}O valor de ordem deve ser maior que 1{Cores.RESET.value}')
    
    match args[0]:
        case '-b':
            try:
                construirIndice()
                print(f'{Cores.VERDE.value}Criação dos índices concluída com sucesso{Cores.RESET.value}')
            except Exception as ex:
                raise Exception(f'{Cores.VERMELHO.value}Houveram problemas na criação dos índices: \n\t-> {ex}{Cores.RESET.value}')
        case '-e':
            try:
                if not os.path.exists(Arquivos.ARVORE.value):
                    construirIndice()
                carregar_raiz()
                executarOperacoes(args[1])
            except Exception as ex:
                raise Exception(f'{Cores.VERMELHO.value} Erro ao executar operações: \n\t-> {ex}{Cores.RESET.value}')
        case '-p':
            try:
                carregar_raiz()
                imprimirArvore()
                print(f'{Cores.VERDE.value}Impressão da árvore concluída com sucesso{Cores.RESET.value}')
                pass
            except Exception as ex:
                raise Exception(f'{Cores.VERMELHO.value} Erro ao imprimir árvore: \n\t-> {ex}{Cores.RESET.value}')
                pass
            pass
        case _:
            raise ValueError(f'{Cores.VERMELHO.value}O argumento fornecido é inválido{Cores.RESET.value}')

def construirIndice():
    if os.path.exists(Arquivos.ARVORE.value):
        os.remove(Arquivos.ARVORE.value)
    inserir()

#region Inserção
def inserir():
    global RAIZ
    pag = Pagina()
    if os.path.exists(Arquivos.ARVORE.value):
        with open(Arquivos.ARVORE.value, 'r+b') as bTreeDat:
            RAIZ = unpack(FORMATO_CAB, bTreeDat.read(TAM_CAB))[0]
    else:
        with open(Arquivos.ARVORE.value, 'wb') as bTreeDat:
            bTreeDat.write(pack(FORMATO_CAB, RAIZ))
        escrevePag(novoRrn(), pag)
    
    with open(Arquivos.BASE.value, 'rb') as gamesDat:
        rrnReg = gamesDat.tell()
        tamReg = gamesDat.read(TAM_TAM)
        while tamReg:
            tamReg = unpack(FORMATO_TAM, tamReg)[0]
            chave = int(gamesDat.read(tamReg).decode().split('|')[0])
            RAIZ = insereNaArvore(chave, rrnReg, RAIZ)
            rrnReg = gamesDat.tell()
            tamReg = gamesDat.read(TAM_TAM)
    
    with open(Arquivos.ARVORE.value, 'r+b') as bTreeDat:
        bTreeDat.write(pack(FORMATO_CAB, RAIZ))
        
def carregar_raiz():
    global RAIZ
    if os.path.exists(Arquivos.ARVORE.value):
        with open(Arquivos.ARVORE.value, 'rb') as bTreeDat:
            RAIZ = unpack(FORMATO_CAB, bTreeDat.read(TAM_CAB))[0]
    else:
        RAIZ = NULO

def escrevePag(rrn, pag: Pagina):
    with open(Arquivos.ARVORE.value, 'r+b') as bTreeDat:
        offset = rrn * TAM_PAG + TAM_CAB
        bTreeDat.seek(offset)
        bTreeDat.write(pack(FORMATO_PAG, pag.numChaves, *pag.chavesPai, *pag.rrnReg, *pag.filhos))

def novoRrn():
    with open(Arquivos.ARVORE.value, 'rb') as bTreeDat:
        bTreeDat.seek(0, os.SEEK_END)
        offset = bTreeDat.tell()
        return (offset - TAM_CAB) // TAM_PAG

def insereNaArvore(chave, rrnReg, raiz):
    chavePro,rrnRegPro, filhoDirPro, promocao = insereChave(chave, rrnReg, raiz)
    
    if promocao:
        pNova = Pagina()
        pNova.chavesPai[0] = chavePro
        pNova.rrnReg[0] = rrnRegPro
        pNova.filhos[:2] = [raiz, filhoDirPro]
        pNova.numChaves += 1
        raiz = novoRrn()
        escrevePag(raiz, pNova)
    
    return raiz

def insereChave(chave, rrnReg, rrnAtual):
    pag: Pagina
    
    if rrnAtual == NULO:
        chavePro = chave
        filhoDirPro = NULO
        return chavePro, rrnReg, filhoDirPro, True
    else:
        pag = leiaPag(rrnAtual)
        achou, filho = buscaNaPagina(chave, pag)
    
    if achou:
       raise ValueError("Chave duplicada")

    chavePro, rrnRegPro, filhoDirPro, promo = insereChave(chave, rrnReg, pag.filhos[filho])

    if not promo:
        return NULO, NULO, NULO, False
    else:
        if NULO in pag.chavesPai:
            insereChavePromo(chavePro, rrnRegPro , filhoDirPro, pag)
            
            escrevePag(rrnAtual, pag)
            
            return NULO,NULO, NULO, False
        else:
            chavePro, rrnRegPro, filhoDirPro, pag, novaPag = divide(chavePro, rrnRegPro, filhoDirPro, pag)
            
            escrevePag(rrnAtual, pag)
            
            escrevePag(filhoDirPro, novaPag)
            
            return chavePro, rrnRegPro, filhoDirPro, True

def leiaPag(rrn):
    with open(Arquivos.ARVORE.value, 'rb') as bTreeDat:
        offset = rrn * TAM_PAG + TAM_CAB
        bTreeDat.seek(offset)
        reg = unpack(FORMATO_PAG, bTreeDat.read(TAM_PAG))
        return Pagina(
            reg[0], 
            [*reg[1:ORDEM]],
            [*reg[ORDEM:2 * ORDEM - 1]], 
            [*reg[2 * ORDEM - 1:]]
        )

def buscaNaPagina(chave, pag: Pagina):
    filho = 0
    
    while filho < pag.numChaves and chave > pag.chavesPai[filho]:
        filho += 1
        
    if filho < pag.numChaves and chave == pag.chavesPai[filho]:
        return True, filho
    else:
        return False, filho

def divide(chave,rrnReg, filhoDir, pag: Pagina):
    insereChavePromo(chave, rrnReg, filhoDir, pag)
    
    meio = ORDEM // 2
    chavePro = pag.chavesPai[meio]
    rrnRegPro = pag.rrnReg[meio]
    filhoDirPro = novoRrn()
    
    pAtual = Pagina(
        len(pag.chavesPai[:meio]),
        completaChavesPaiERrnReg(pag.chavesPai[:meio]),
        completaChavesPaiERrnReg(pag.rrnReg[:meio]),
        completaFilhos(pag.filhos[:meio + 1])
    )
    pNova = Pagina(
        len(pag.chavesPai[meio + 1:]),
        completaChavesPaiERrnReg(pag.chavesPai[meio + 1:]),
        completaChavesPaiERrnReg(pag.rrnReg[meio + 1:]),
        completaFilhos(pag.filhos[meio + 1:])
    )
    
    return chavePro, rrnRegPro, filhoDirPro, pAtual, pNova

def insereChavePromo(chave, rrnReg, filhoDir, pag: Pagina):
    if NULO not in pag.chavesPai:
        pag.chavesPai.append(NULO)
        pag.rrnReg.append(NULO)
        pag.filhos.append(NULO)
    
    i = pag.numChaves
    while i > 0 and chave < pag.chavesPai[i - 1]:
        pag.chavesPai[i] = pag.chavesPai[i - 1]
        pag.rrnReg[i] = pag.rrnReg[i - 1]   
        pag.filhos[i + 1] = pag.filhos[i]
        i -= 1
    
    pag.chavesPai[i] = chave
    pag.rrnReg[i] = rrnReg
    pag.filhos[i + 1] = filhoDir
    pag.numChaves += 1

def ordenaChaves(chaves: list):
    chaves.sort(key=lambda x: (x == -1, x))
    return chaves

def completaChavesPaiERrnReg(chaves):
    return chaves + [NULO] * ((ORDEM - 1) - len(chaves))

def completaFilhos(filhos: list):
    return filhos + [NULO] * (ORDEM - len(filhos))

def inserirArquivoBase(registro):
    modo = 'r+b' if os.path.exists(Arquivos.BASE.value) else 'w+b'
    with open(Arquivos.BASE.value, modo) as gamesDat:
        gamesDat.seek(0, os.SEEK_END)
        offset = gamesDat.tell()
        regBytes = registro.encode()
        tamReg = len(regBytes)
        gamesDat.write(pack(FORMATO_TAM, tamReg))
        gamesDat.write(regBytes)
    return offset
#endregion

#region Busca
def ler_reg(rrn_na_Arvore, pos_na_pagina):
    
    pag = leiaPag(rrn_na_Arvore)
    
    offset = pag.rrnReg[pos_na_pagina]
    
    with open(Arquivos.BASE.value, "rb") as entrada:
        entrada.seek(offset)
        tam_reg_bytes = entrada.read(TAM_TAM)
        tam_reg = unpack(FORMATO_TAM, tam_reg_bytes)[0]
        reg = entrada.read(tam_reg).decode()
        
        return reg, offset, tam_reg
        
def buscaNaArvore(chave, rrn):
    if rrn == NULO:
        return False, NULO, NULO
    else:
        pag = leiaPag(rrn)
        achou, pos = buscaNaPagina(chave, pag)
        
        if achou:
            return True, rrn, pos
        else:
            return buscaNaArvore(chave, pag.filhos[pos])

def executarOperacoes(nomeArquivoOperacoes):    
    global RAIZ
    linhasOperacoes = []
    try:
        with open(nomeArquivoOperacoes, 'rb') as f:
            for linha in f.readlines():
                linhasOperacoes.append(linha.decode())
    except FileNotFoundError:
        print(f"Erro: arquivo '{nomeArquivoOperacoes}' arquivo não existe.")
        exit(1)
        
    for linha in linhasOperacoes:
        linha = linha.strip()
        
        if not linha:
            continue
        
        comando = linha[0]
        argumento = linha[2:]

        match comando:
            case 'b':
                chave = int(argumento)
                achou, rrn_da_pagina, pos_na_pagina = buscaNaArvore(chave, RAIZ)
                print(f'Busca pelo registro de chave "{chave}"')
                 
                if not achou:
                    print(f"Registo não encontrado {chave}\n")
                else:
                    reg, offset, tam_reg = ler_reg(rrn_da_pagina, pos_na_pagina) 
                    print(f'{reg} ({tam_reg} bytes - offset {offset})\n')
                    
            case 'i':
                chave = int(argumento.split('|')[0])
                achou, rrn_da_pagina, pos_na_pagina = buscaNaArvore(chave, RAIZ)

                print(f'Inserção do registro de chave "{chave}"')

                if achou:
                    print(f"Erro: chave {chave} duplicada\n")
                else:
                    offset = inserirArquivoBase(argumento)
                    RAIZ = insereNaArvore(chave, offset, RAIZ)

                    achou, rrn_da_pagina, pos_na_pagina = buscaNaArvore(chave, RAIZ)

                    reg, offset, tam_reg = ler_reg(rrn_da_pagina, pos_na_pagina)

                    print(f'{reg} ({tam_reg} bytes - offset {offset})\n')

            case _:
                print(f'Comando inválido: {comando}')
    
    with open(Arquivos.ARVORE.value, 'r+b') as bTreeDat:
        bTreeDat.write(pack(FORMATO_CAB, RAIZ))
        
    print(f'As operações do arquivo "{nomeArquivoOperacoes}" foram executadas com sucesso!')
    
#endregion

#region Impressão
def imprimirArvore():
    if not os.path.exists(Arquivos.ARVORE.value):
        raise FileNotFoundError(f'{Cores.VERMELHO.value}Arquivo {Arquivos.ARVORE.value} não encontrado{Cores.RESET.value}')
    ultimaPagina = novoRrn() - 1
    rrn = 0
    while rrn <= ultimaPagina:
        imprimirPagina(rrn)
        rrn += 1
            
def imprimirPagina(rrn):
    ehRaiz = rrn == RAIZ
    pag = leiaPag(rrn)

    print('- - - - - - - - - - Raiz - - - - - - - - - -' if ehRaiz else '')
    print(f'Página {rrn}:')
    print(f'  Chaves: {" | ".join(map(str, pag.chavesPai))}')
    print(f'  Offsets: {" | ".join(map(str, pag.rrnReg))}')
    print(f'  Filhos: {" | ".join(map(str, pag.filhos))}')
    print('- - - - - - - - - - - -  - - - - - - - - - -' if ehRaiz else '')

#endregion

if __name__ == '__main__':
    try:
        main(argv[1:])
    except Exception as ex:
        print(ex)

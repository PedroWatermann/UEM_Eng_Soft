import sys
from enum import Enum
from dataclasses import dataclass

@dataclass
class Time:
    nome: str
    pontos: int
    vitorias: int
    saldo_de_gols: int
    aproveitamento: float
    gols_feitos: int
    gols_sofridos: int
    pontos_possiveis: int
    pontos_mandante: int

class Atributos(Enum):
    PONTOS = 0
    VITORIAS = 1
    SALDO_DE_GOLS = 2
    NOME = 3

class Pergunta(Enum):
    CLASSIFICACAO = 1
    APROVEITAMENTO = 2

info_times: list[Time] = []
empate_pontos: list[list[int]] = []
empate_vitorias: list[list[int]] = []
empate_saldo_gols: list[list[int]] = []
info_jogos: list[list[list[str]]] = []

def main():
    if len(sys.argv) < 2:
        print('Nenhum nome de arquivo informado.')
        sys.exit(1)
    
    if len(sys.argv) > 2:
        print('Muitos parâmetros. Informe apenas um nome de arquivo.')
        sys.exit(1)
        
    campeonato = le_arquivo(sys.argv[1])
    jogos: list[str] = []
    
    for jogo in campeonato:
        jogos.append(split(jogo, '\n')[0])
    
    separar_info_jogos(jogos)
    
    # Solução da pergunta 1
    print('Pergunta 1 - Classificação dos times')
    classificacao_times()
    imprime_formatado()
    
    # Solução da pergunta 2
    print()
    print('Pergunta 2 - Melhor aproveitamento')
    for e in melhor_aproveitamento():
        print(e)
    
    # Solução da pergunta 3
    print()
    print('Pergunta 3 - Melhor defesa')
    print(melhor_defesa())

def classificacao_times():
    """
    Preenche e ordena conforme estabelecido nas regras da atividade a lista gloabal *info_times* a partir das informações contidas na lista global *info_jogos*. Essa função deve ser chamada apenas após a execução da função separar_info_jogos().
    Exemplos:
    >>> info_jogos[:] = []
    >>> info_times[:] = []
    >>> info_jogos.extend([[['Sao-Paulo'], ['1'], ['Atletico-MG'], ['2']], [['Flamengo'], ['2'], ['Palmeiras'], ['1']], [['Palmeiras'], ['0'], ['Sao-Paulo'], ['0']], [['Atletico-MG'], ['1'], ['Flamengo'], ['2']]])
    >>> info_times.extend([])
    >>> classificacao_times()
    >>> print(info_times)
    [Time(nome='Flamengo', pontos=6, vitorias=2, saldo_de_gols=2, aproveitamento=0.0, gols_feitos=4, gols_sofridos=2, pontos_possiveis=0, pontos_mandante=0), Time(nome='Atletico-MG', pontos=3, vitorias=1, saldo_de_gols=0, aproveitamento=0.0, gols_feitos=3, gols_sofridos=3, pontos_possiveis=0, pontos_mandante=0), Time(nome='Palmeiras', pontos=1, vitorias=0, saldo_de_gols=-1, aproveitamento=0.0, gols_feitos=1, gols_sofridos=2, pontos_possiveis=0, pontos_mandante=0), Time(nome='Sao-Paulo', pontos=1, vitorias=0, saldo_de_gols=-1, aproveitamento=0.0, gols_feitos=1, gols_sofridos=2, pontos_possiveis=0, pontos_mandante=0)]
    >>> info_jogos[:] = []
    >>> info_times[:] = []
    >>> info_jogos.extend([[['Criciuma'], ['4'], ['Red-Bull-Bragantino'], ['4']], [['Santos'], ['3'], ['Corinthians'], ['1']], [['Corinthians'], ['4'], ['Criciuma'], ['0']], [['Red-Bull-Bragantino'], ['0'], ['Santos'], ['2']]])
    >>> info_times.extend([])
    >>> classificacao_times()
    >>> print(info_times)
    [Time(nome='Santos', pontos=6, vitorias=2, saldo_de_gols=4, aproveitamento=0.0, gols_feitos=5, gols_sofridos=1, pontos_possiveis=0, pontos_mandante=0), Time(nome='Corinthians', pontos=3, vitorias=1, saldo_de_gols=2, aproveitamento=0.0, gols_feitos=5, gols_sofridos=3, pontos_possiveis=0, pontos_mandante=0), Time(nome='Red-Bull-Bragantino', pontos=1, vitorias=0, saldo_de_gols=-2, aproveitamento=0.0, gols_feitos=4, gols_sofridos=6, pontos_possiveis=0, pontos_mandante=0), Time(nome='Criciuma', pontos=1, vitorias=0, saldo_de_gols=-4, aproveitamento=0.0, gols_feitos=4, gols_sofridos=8, pontos_possiveis=0, pontos_mandante=0)]
    """
    
    # Preenche a lista principal com o nome dos times
    existe: int = 0
    for jogo in info_jogos:
        existe = 0
        time: str = jogo[0][0]

        for i in range(len(info_times)):
            if info_times != []:
                if time == info_times[i].nome:
                    existe = 1

        if existe == 0:
            info_times.append(Time(time, 0, 0, 0, 0.0, 0, 0, 0, 0))

    # Soma os pontos, conta as vitórias, os gols feitos e sofridos e adiciona na lista principal
    for jogo in info_jogos:
        time_anfitriao: str = jogo[0][0]
        time_convidado: str = jogo[2][0]
        gols_anfitriao: int = int(jogo[1][0])
        gols_convidado: int = int(jogo[3][0])
        
        if gols_anfitriao > gols_convidado:
            for i in range(len(info_times)):
                if time_anfitriao == info_times[i].nome:
                    info_times[i].pontos += 3
                    info_times[i].vitorias += 1
                    info_times[i].gols_feitos += gols_anfitriao
                    info_times[i].gols_sofridos += gols_convidado
                if time_convidado == info_times[i].nome:
                    info_times[i].gols_feitos += gols_convidado
                    info_times[i].gols_sofridos += gols_anfitriao
        elif gols_anfitriao < gols_convidado:
            for i in range(len(info_times)):
                if time_convidado == info_times[i].nome:
                    info_times[i].pontos += 3
                    info_times[i].vitorias += 1
                    info_times[i].gols_feitos += gols_convidado
                    info_times[i].gols_sofridos += gols_anfitriao
                if time_anfitriao == info_times[i].nome:
                    info_times[i].gols_feitos += gols_anfitriao
                    info_times[i].gols_sofridos += gols_convidado
        else:
            for i in range(len(info_times)):
                if time_anfitriao == info_times[i].nome:
                    info_times[i].pontos += 1
                    info_times[i].gols_feitos += gols_anfitriao
                    info_times[i].gols_sofridos += gols_convidado
                if time_convidado == info_times[i].nome:
                    info_times[i].pontos += 1
                    info_times[i].gols_feitos += gols_convidado
                    info_times[i].gols_sofridos += gols_anfitriao
    
    # Define o saldo de gols de cada time
    for t in info_times:
        t.saldo_de_gols = t.gols_feitos - t.gols_sofridos
    
    # Ordena pelos pontos, número de vitórias, saldo de gols e ordem alfabética
    selection_sort()

def melhor_aproveitamento() -> list[str]:
    """
    Calcula o melhor aproveitamento dos times como mandantes contidos na lista global *info_times* a partir da lista global *info_jogos* e retorna uma lista com esses times, ordenada conforme as regras indicadas na atividade. O melhor aproveitamento como mandante é calculado dividindo-se a quantidade de pontos possíveis que o time poderia fazer como mandante pela quantidade de pontos que o time realmente fez. Esta função deve ser executada apenas após a execução da função classificação_times().
    Exemplos:
    >>> info_jogos[:] = []
    >>> info_times[:] = []
    >>> info_jogos.extend([[['Sao-Paulo'], ['1'], ['Atletico-MG'], ['2']], [['Flamengo'], ['2'], ['Palmeiras'], ['1']], [['Palmeiras'], ['0'], ['Sao-Paulo'], ['0']], [['Atletico-MG'], ['1'], ['Flamengo'], ['2']]])
    >>> info_times.extend([])
    >>> classificacao_times()
    >>> print(melhor_aproveitamento())
    ['Flamengo 100.0%']
    >>> info_jogos[:] = []
    >>> info_times[:] = []
    >>> info_jogos.extend([[['Criciuma'], ['5'], ['Red-Bull-Bragantino'], ['4']], [['Santos'], ['8'], ['Corinthians'], ['1']], [['Corinthians'], ['3'], ['Criciuma'], ['4']], [['Red-Bull-Bragantino'], ['1'], ['Santos'], ['1']]])
    >>> info_times.extend([])
    >>> classificacao_times()
    >>> print(melhor_aproveitamento())
    ['Criciuma 100.0%', 'Santos 100.0%']
    """
    
    # Calcula os pontos possíveis e os obtidos
    for jogo in info_jogos:
        time_anfitriao: str = jogo[0][0]
        gols_anfitriao: int = int(jogo[1][0])
        gols_convidado: int = int(jogo[3][0])
        
        for j in range(len(info_times)):
            if info_times[j].nome == time_anfitriao:
                if gols_anfitriao > gols_convidado:
                    info_times[j].pontos_mandante += 3
                elif gols_anfitriao == gols_convidado:
                    info_times[j].pontos_mandante += 1
                
                info_times[j].pontos_possiveis += 3
    
    # Calcula o aproveitamento dos times
    for i in range(len(info_times)):
        time_anfitriao = info_jogos[i][0][0]
        
        for j in range(len(info_times)):
            if time_anfitriao == info_times[j].nome:
                info_times[j].aproveitamento = int(((info_times[j].pontos_mandante * 100 / info_times[j].pontos_possiveis) * 100)) / 100

    # Encontra o maior aproveitamento
    aux: int = 0
    for i in range(1, len(info_times)):
        if info_times[i].aproveitamento > info_times[aux].aproveitamento:
            aux = i
    
    # Encontra o time com maior nome
    maior_nome: int = len(info_times[aux].nome)
    for i in range(len(info_times)):
        if info_times[i].aproveitamento == info_times[aux].aproveitamento and len(info_times[i].nome) > maior_nome:
            maior_nome = len(info_times[i].nome)
    maior_nome += 2

    # Armazena em uma lista o(s) time(s) que tiveram o melhor aproveitamento
    aproveitamento_times: list[str] = []
    for i in range(len(info_times)):
        if info_times[i].aproveitamento == info_times[aux].aproveitamento:
            temp_nome: str = info_times[i].nome
            temp_apro: str = str(info_times[i].aproveitamento)
            if len(temp_nome) < maior_nome:
                for j in range(0, maior_nome - len(temp_nome)):
                    temp_nome += ' '
            if len(temp_apro) < 3:
                for k in range(0, 3 - len(temp_apro)):
                    temp_apro += ' '
            aproveitamento_times.append(temp_nome + temp_apro + '%')

    return aproveitamento_times

def melhor_defesa() -> str:
    """
    Seleciona e retorna o time com o menor número de gols sofridos da lista global *info_times*, ordenada conforme as regras indicadas na atividade. Esta função deve ser executada apenas após a execução da função classificação_times().
    Exemplos:
    >>> info_jogos[:] = []
    >>> info_times[:] = []
    >>> info_jogos.extend([[['Sao-Paulo'], ['1'], ['Atletico-MG'], ['2']], [['Flamengo'], ['2'], ['Palmeiras'], ['1']], [['Palmeiras'], ['0'], ['Sao-Paulo'], ['0']], [['Atletico-MG'], ['1'], ['Flamengo'], ['2']]])
    >>> info_times.extend([])
    >>> classificacao_times()
    >>> print(melhor_defesa())
    Flamengo - 2 gols sofridos
    >>> info_jogos[:] = []
    >>> info_times[:] = []
    >>> info_jogos.extend([[['Criciuma'], ['2'], ['Red-Bull-Bragantino'], ['4']], [['Santos'], ['2'], ['Corinthians'], ['1']], [['Corinthians'], ['1'], ['Criciuma'], ['7']], [['Red-Bull-Bragantino'], ['1'], ['Santos'], ['2']]])
    >>> info_times.extend([])
    >>> classificacao_times()
    >>> print(melhor_defesa())
    Santos - 2 gols sofridos
    """    
    
    # Encontra o time com o menor numero de gols sofridos
    melhor_pos: int = melhor_defesa_recursivo(info_times, 0, 0)
    
    return info_times[melhor_pos].nome + ' - ' + str(info_times[melhor_pos].gols_sofridos) + ' gols sofridos'

def imprime_formatado():
    """
    Imprime a tabela do brasileirão de forma formatada, onde cada coluna segue um tamanho padrão, seja ele definido pelo maior elemento ou atribuído um valor base. Essa função deve ser executada apenas após a execução da função classificacao_times().
    Exemplos:
    >>> info_jogos[:] = []
    >>> info_times[:] = []
    >>> info_jogos.extend([[['Sao-Paulo'], ['1'], ['Atletico-MG'], ['2']], [['Flamengo'], ['2'], ['Palmeiras'], ['1']], [['Palmeiras'], ['0'], ['Sao-Paulo'], ['0']], [['Atletico-MG'], ['1'], ['Flamengo'], ['2']]])
    >>> info_times.extend([])
    >>> classificacao_times()
    >>> print(imprime_formatado())
    Flamengo      6    2    2
    Atletico-MG   3    1    0
    Palmeiras     1    0   -1
    Sao-Paulo     1    0   -1
    None
    >>> info_jogos[:] = []
    >>> info_times[:] = []
    >>> info_jogos.extend([[['Criciuma'], ['2'], ['Red-Bull-Bragantino'], ['4']], [['Santos'], ['2'], ['Corinthians'], ['1']], [['Corinthians'], ['1'], ['Criciuma'], ['7']], [['Red-Bull-Bragantino'], ['1'], ['Santos'], ['2']]])
    >>> info_times.extend([])
    >>> classificacao_times()
    >>> print(imprime_formatado())
    Santos                6    2    2
    Criciuma              3    1    4
    Red-Bull-Bragantino   3    1    1
    Corinthians           0    0   -7
    None
    """
    
    # Encontra o time com o maior nome
    maior_nome: str = maior_nome_recursivo(info_times, 0, '')
          
    # Imprime de forma formatada
    formatado: str = ''
    qtd_letras: int = len(maior_nome) + 2
    for time in info_times:
        formatado = time.nome
        if len(time.nome) < qtd_letras:
            for i in range(0, qtd_letras - len(time.nome)):
                formatado += ' '
        if len(str(time.pontos)) < 2:
            formatado += ' '
        formatado += str(time.pontos) + '  '
        if len(str(time.vitorias)) < 3:
            for i in range(0, 3 - len(str(time.vitorias))):
                formatado += ' '
        formatado += str(time.vitorias) + '  '
        if len(str(time.saldo_de_gols)) < 3:
            for i in range(0, 3 - len(str(time.saldo_de_gols))):
                formatado += ' '
        formatado += str(time.saldo_de_gols)
        print(formatado)

def le_arquivo(nome: str) -> list[str]:
    '''
    Lê o conteúdo do arquivo *nome* e devolve uma lista onde cada elemento representa uma linha.
    
    Por exemplo, se o conteudo do arquivo for
    Sao-Paulo 1 Atletico-MG 2
    Flamengo 2 Palmeiras 1
    
    A resposta produzida  ́e
    ['Sao-Paulo 1 Atletico-MG 2’, 'Flamengo 2 Palmeiras 1’]
    '''
    try:
        with open(nome) as f:
            return f.readlines()
    except IOError as e:
        print(f'Erro na leitura do arquivo "{nome}": {e.errno} - {e.strerror}.')
    sys.exit(1)

def split(frase: str, delimitador: str) -> list[str]:
    """ 
    A partir de *frase*, retorna uma lista de strings, delimitando seus elementos pelo caractere *delimitador* fornecido. Caso o *delimitador* possua mais de 1 caractere, será retornada uma string vazia.
    Exemplos:
    >>> split('Internacional 2 Bahia 1\\n', '\\n')
    ['Internacional 2 Bahia 1']
    >>> split('Criciúma 1 Juventude 1', ' ')
    ['Criciúma', '1', 'Juventude', '1']
    >>> split("Sao-Paulo--1--Vasco--2", "--")
    []
    """
    
    if len(delimitador) > 1:
        return []
    
    palavra: str = ''
    nova_frase: list[str] = []
    
    for letra in frase:
        if letra == delimitador:
            nova_frase.append(palavra)
            palavra = ""
        else:
            palavra += letra
    
    if palavra != '':
        nova_frase.append(palavra)
    
    return nova_frase

def separar_info_jogos(jogos: list[str]):
    """
    Recebe uma lista de strings onde cada linha é a informação de uma partida do brasileirão, contendo as informações 'time_mandante gols_mandante time_convidado gols_convidade', separa essas informações e retorna uma lista contendo uma matriz de strings, onde a primeira coluna representa o time_mandante, a segunda coluna representa os gols_mandante, a terceira representa o time_convidado e a quarta representa os gols do convidado.
    Exemplo:
    >>> info_jogos[:] = []
    >>> jogos = []
    >>> jogos.extend(['Sao-Paulo 1 Atletico-MG 2', 'Flamengo 2 Palmeiras 1', 'Palmeiras 0 Sao-Paulo 0', 'Atletico-MG 1 Flamengo 2'])
    >>> separar_info_jogos(jogos)
    [[['Sao-Paulo'], ['1'], ['Atletico-MG'], ['2']], [['Flamengo'], ['2'], ['Palmeiras'], ['1']], [['Palmeiras'], ['0'], ['Sao-Paulo'], ['0']], [['Atletico-MG'], ['1'], ['Flamengo'], ['2']]]
    >>> info_jogos[:] = []
    >>> jogos = []
    >>> jogos.extend(['Atletico-PR 5 Atletico-MG 7', 'Juventude 0 Bahia 5', 'Bahia 8 Atletico-PR 8', 'Atletico-MG 1 Juventude 2'])
    >>> separar_info_jogos(jogos)
    [[['Atletico-PR'], ['5'], ['Atletico-MG'], ['7']], [['Juventude'], ['0'], ['Bahia'], ['5']], [['Bahia'], ['8'], ['Atletico-PR'], ['8']], [['Atletico-MG'], ['1'], ['Juventude'], ['2']]]
    """
    
    jogos_formatados: list[list[str]] = []
    for t in jogos:
        temp: list[str] = []
        temp.append(t)
        jogos_formatados.append(temp)

    for i in range(len(jogos_formatados)):
        for j in jogos_formatados[i]:
            temp_2: list[str] = []
            temp_2 = split(j, ' ')
            temp_4: list[list[str]] = []
            for k in range(len(temp_2)):
                temp_3: list[str] = []
                temp_3 = split(temp_2[k], '')
                temp_4.append(temp_3)
            info_jogos.append(temp_4)
    
    return info_jogos

def selection_sort():
    """
    Algoritmo de ordenação: ordena a lista global *info_jogos* pelos *pontos*, *numero_de_vitorias*, *saldo_de_gols* e *nome* dos times.
    Exemplos:
    >>> info_times = []
    >>> info_times.extend([Time(nome='Corinthians', pontos=3, vitorias=1, saldo_de_gols=2, aproveitamento=0.0, gols_feitos=5, gols_sofridos=3, pontos_possiveis=0, pontos_mandante=0), Time(nome='Santos', pontos=6, vitorias=2, saldo_de_gols=4, aproveitamento=0.0, gols_feitos=5, gols_sofridos=1, pontos_possiveis=0, pontos_mandante=0), Time(nome='Red-Bull-Bragantino', pontos=1, vitorias=0, saldo_de_gols=-2, aproveitamento=0.0, gols_feitos=4, gols_sofridos=6, pontos_possiveis=0, pontos_mandante=0), Time(nome='Criciuma', pontos=1, vitorias=0, saldo_de_gols=-4, aproveitamento=0.0, gols_feitos=4, gols_sofridos=8, pontos_possiveis=0, pontos_mandante=0)])
    >>> selection_sort()
    >>> print(info_times)
    [Time(nome='Corinthians', pontos=3, vitorias=1, saldo_de_gols=2, aproveitamento=0.0, gols_feitos=5, gols_sofridos=3, pontos_possiveis=0, pontos_mandante=0), Time(nome='Santos', pontos=6, vitorias=2, saldo_de_gols=4, aproveitamento=0.0, gols_feitos=5, gols_sofridos=1, pontos_possiveis=0, pontos_mandante=0), Time(nome='Red-Bull-Bragantino', pontos=1, vitorias=0, saldo_de_gols=-2, aproveitamento=0.0, gols_feitos=4, gols_sofridos=6, pontos_possiveis=0, pontos_mandante=0), Time(nome='Criciuma', pontos=1, vitorias=0, saldo_de_gols=-4, aproveitamento=0.0, gols_feitos=4, gols_sofridos=8, pontos_possiveis=0, pontos_mandante=0)]
    >>> info_times = []
    >>> info_times.extend([Time(nome='Botafogo', pontos=29, vitorias=8, saldo_de_gols=9, aproveitamento=0.0, gols_feitos=22, gols_sofridos=13, pontos_possiveis=0, pontos_mandante=0), Time(nome='Vasco-da-Gama', pontos=12, vitorias=3, saldo_de_gols=-8, aproveitamento=0.0, gols_feitos=11, gols_sofridos=19, pontos_possiveis=0, pontos_mandante=0), Time(nome='Athletico-PR', pontos=25, vitorias=7, saldo_de_gols=6, aproveitamento=0.0, gols_feitos=21, gols_sofridos=15, pontos_possiveis=0, pontos_mandante=0), Time(nome='Fortaleza', pontos=18, vitorias=4, saldo_de_gols=-1, aproveitamento=0.0, gols_feitos=15, gols_sofridos=16, pontos_possiveis=0, pontos_mandante=0)])
    >>> selection_sort()
    >>> print(info_times)
    [Time(nome='Botafogo', pontos=29, vitorias=8, saldo_de_gols=9, aproveitamento=0.0, gols_feitos=22, gols_sofridos=13, pontos_possiveis=0, pontos_mandante=0), Time(nome='Vasco-da-Gama', pontos=12, vitorias=3, saldo_de_gols=-8, aproveitamento=0.0, gols_feitos=11, gols_sofridos=19, pontos_possiveis=0, pontos_mandante=0), Time(nome='Athletico-PR', pontos=25, vitorias=7, saldo_de_gols=6, aproveitamento=0.0, gols_feitos=21, gols_sofridos=15, pontos_possiveis=0, pontos_mandante=0), Time(nome='Fortaleza', pontos=18, vitorias=4, saldo_de_gols=-1, aproveitamento=0.0, gols_feitos=15, gols_sofridos=16, pontos_possiveis=0, pontos_mandante=0)]
    """
    
    for i in range(len(info_times)):
        maximo = i
        j = i + 1
        while j < len(info_times):
            if info_times[j].pontos > info_times[maximo].pontos:
                maximo = j
            elif info_times[j].pontos == info_times[maximo].pontos:
                if info_times[j].vitorias > info_times[maximo].vitorias:
                    maximo = j
                elif info_times[j].vitorias == info_times[maximo].vitorias:
                    if info_times[j].saldo_de_gols > info_times[maximo].saldo_de_gols:
                        maximo = j
                    elif info_times[j].saldo_de_gols == info_times[maximo].saldo_de_gols:
                        if info_times[j].nome < info_times[maximo].nome:
                            maximo = j
            j += 1
        
        aux = info_times[i]
        info_times[i] = info_times[maximo]
        info_times[maximo] = aux

def melhor_defesa_recursivo(times: list[Time], pos_atual: int, melhor_pos: int) -> int:
    """
    Função recursiva que encontra o time que sofreu menos gols em todo o campeonato, ou seja, que o time que possui a defesa menos vazada, e retorna sua posição.
    Exemplos:
    >>> info_times = []
    >>> info_times.extend([Time(nome='Corinthians', pontos=3, vitorias=1, saldo_de_gols=2, aproveitamento=0.0, gols_feitos=5, gols_sofridos=3, pontos_possiveis=0, pontos_mandante=0), Time(nome='Santos', pontos=6, vitorias=2, saldo_de_gols=4, aproveitamento=0.0, gols_feitos=5, gols_sofridos=1, pontos_possiveis=0, pontos_mandante=0), Time(nome='Red-Bull-Bragantino', pontos=1, vitorias=0, saldo_de_gols=-2, aproveitamento=0.0, gols_feitos=4, gols_sofridos=6, pontos_possiveis=0, pontos_mandante=0), Time(nome='Criciuma', pontos=1, vitorias=0, saldo_de_gols=-4, aproveitamento=0.0, gols_feitos=4, gols_sofridos=8, pontos_possiveis=0, pontos_mandante=0)])
    >>> melhor_pos = melhor_defesa_recursivo(info_times, 0, 0)
    >>> print(info_times[melhor_pos])
    Time(nome='Santos', pontos=6, vitorias=2, saldo_de_gols=4, aproveitamento=0.0, gols_feitos=5, gols_sofridos=1, pontos_possiveis=0, pontos_mandante=0)
    >>> info_times = []
    >>> info_times.extend([Time(nome='Botafogo', pontos=29, vitorias=8, saldo_de_gols=9, aproveitamento=0.0, gols_feitos=22, gols_sofridos=13, pontos_possiveis=0, pontos_mandante=0), Time(nome='Vasco-da-Gama', pontos=12, vitorias=3, saldo_de_gols=-8, aproveitamento=0.0, gols_feitos=11, gols_sofridos=19, pontos_possiveis=0, pontos_mandante=0), Time(nome='Athletico-PR', pontos=25, vitorias=7, saldo_de_gols=6, aproveitamento=0.0, gols_feitos=21, gols_sofridos=15, pontos_possiveis=0, pontos_mandante=0), Time(nome='Fortaleza', pontos=18, vitorias=4, saldo_de_gols=-1, aproveitamento=0.0, gols_feitos=15, gols_sofridos=16, pontos_possiveis=0, pontos_mandante=0)])
    >>> melhor_pos = melhor_defesa_recursivo(info_times, 0, 0)
    >>> print(info_times[melhor_pos])
    Time(nome='Botafogo', pontos=29, vitorias=8, saldo_de_gols=9, aproveitamento=0.0, gols_feitos=22, gols_sofridos=13, pontos_possiveis=0, pontos_mandante=0)
    """
    
    # Verifica se chegou ao fim da lista
    if pos_atual == len(times):
        return melhor_pos
    
    # Verifica se a quantidade de gols sofridos da posição atual é menor do que a quantidade salva anteriormente
    if times[pos_atual].gols_sofridos < times[melhor_pos].gols_sofridos:
        melhor_pos = pos_atual
    
    return melhor_defesa_recursivo(times, pos_atual + 1, melhor_pos)

def maior_nome_recursivo(times: list[Time], pos_atual: int, maior_nome: str) -> str:
    """
    Função recursiva que encontra o maior nome na lista global *info_times* e retorna este nome como uma string.
    Exemplos:
    >>> info_jogos = []
    >>> info_jogos.extend([Time('Sao-Paulo', 0, 0, 0, 0.0, 0, 0, 0, 0), Time('Atletico-MG', 0, 0, 0, 0.0, 0, 0, 0, 0), Time('Palmeiras', 0, 0, 0, 0.0, 0, 0, 0, 0), Time('Flamengo', 0, 0, 0, 0.0, 0, 0, 0, 0)])
    >>> maior_nome_recursivo(info_jogos, 0, '')
    'Atletico-MG'
    >>> info_jogos = []
    >>> info_jogos.extend([Time('Internacional', 0, 0, 0, 0.0, 0, 0, 0, 0), Time('Santos', 0, 0, 0, 0.0, 0, 0, 0, 0), Time('Cruzeiro', 0, 0, 0, 0.0, 0, 0, 0, 0), Time('Bahia', 0, 0, 0, 0.0, 0, 0, 0, 0)])
    >>> maior_nome_recursivo(info_jogos, 0, '')
    'Internacional'
    """
    
    # Verifica se chegou ao fim da lista
    if pos_atual == len(times):
        return maior_nome
    
    # Verifica se o nome atual da lista é maior do que o maior nome salvo até agora
    if len(times[pos_atual].nome) > len(maior_nome):
        maior_nome = times[pos_atual].nome
    
    return maior_nome_recursivo(times, pos_atual + 1, maior_nome)

if __name__ == '__main__':
    main()

from enum import Enum, auto





# Você está desenvolvendo uma função que determina o resultado de uma rodada de Jokenpô entre dois jogadores.
# Para deixar o código mais claro e seguro, você utilizará enumerações para representar tanto as jogadas quanto os resultados possíveis.




# Análise
#
# Determinar o resultado de uma rodada do Jokenpô (Pedra, Papel, Tesoura)

# Definição de tipos de dados
# - A jogada será representada por uma enumeração
# - O resultado será respresentado por uma enumeração

class Jogada(Enum):
    '''
    Uma das jogadas (símbolo) no jogo Jokenpô.
    '''
    PEDRA = auto()
    PAPEL = auto()
    TESOURA = auto()


class Resultado(Enum):
    '''
    O resultado de uma rodada no jogo Jokenpô.
    '''
    EMPATE = auto()
    PRIMEIRO = auto()
    SEGUNDO = auto()


def jokenpo_resultado(a: Jogada, b: Jogada) -> Resultado:
    '''
    Determina qual é o símbolo vencedor, *a* ou *b*.

    PEDRA ganha de TESOURA, TESOURA ganha de PAPEL e PAPEL ganha de PEDRA.

    Se *a* e *b* forem iguais, o resultado é Resultado.EMPATE.

    Se *a* ganha de *b*, o resultado é Resultado.PRIMEIRO.

    Se *b* ganha de *a*, o resultado é Resultado.SEGUNDO.

    Exemplos
    >>> # Empate
    >>> jokenpo_resultado(Jogada.PEDRA, Jogada.PEDRA).name
    'EMPATE'
    >>> jokenpo_resultado(Jogada.PAPEL, Jogada.PAPEL).name
    'EMPATE'
    >>> jokenpo_resultado(Jogada.TESOURA, Jogada.TESOURA).name
    'EMPATE'
    >>> # Primeiro vence
    >>> jokenpo_resultado(Jogada.PEDRA, Jogada.TESOURA).name
    'PRIMEIRO'
    >>> jokenpo_resultado(Jogada.PAPEL, Jogada.PEDRA).name
    'PRIMEIRO'
    >>> jokenpo_resultado(Jogada.TESOURA, Jogada.PAPEL).name
    'PRIMEIRO'
    >>> # Segundo vence
    >>> jokenpo_resultado(Jogada.PEDRA, Jogada.PAPEL).name
    'SEGUNDO'
    >>> jokenpo_resultado(Jogada.PAPEL, Jogada.TESOURA).name
    'SEGUNDO'
    >>> jokenpo_resultado(Jogada.TESOURA, Jogada.PEDRA).name
    'SEGUNDO'
    '''
    if a == b:
        r = Resultado.EMPATE
    elif (a == Jogada.PEDRA and b == Jogada.TESOURA) or \
            (a == Jogada.TESOURA and b == Jogada.PAPEL) or \
            (a == Jogada.PAPEL and b == Jogada.PEDRA):
        r = Resultado.PRIMEIRO
    else:
        r = Resultado.SEGUNDO
    return r
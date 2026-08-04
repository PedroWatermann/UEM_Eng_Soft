from enum import Enum

class Cartas(Enum):
    C_4 = 0
    C_5 = 1
    C_6 = 2
    C_7 = 3
    C_Q = 4
    C_J = 5
    C_K = 6
    C_A = 7
    C_2 = 8
    C_3 = 9

class Naipes(Enum):
    Ouros = 0.25
    Espadas = 0.5
    Copas = 0.75
    Paus = 1

def truco_basico(carta: Cartas, naipe: Naipes) -> float:
    '''
    Retorna o valor da carta baseado na carta e no naipe fornecidos
    >>> truco_basico(Cartas.C_J, Naipes.Ouros)
    5.25
    >>> truco_basico(Cartas.C_3, Naipes.Paus)
    10
    '''
    return carta.value + naipe.value

def truco_basico_maior(c1: Cartas, c2: Cartas, n1: Naipes, n2: Naipes) -> str:
    '''
    Retorna qual das duas cartas fornecidas é maior baseado nas cartas e nos naipes fornecidos
    >>> truco_basico_maior(Cartas.C_4, Cartas.C_5, Naipes.Ouros, Naipes.Ouros)
    'C_5 de Ouros'
    >>> truco_basico_maior(Cartas.C_3, Cartas.C_3, Naipes.Paus, Naipes.Paus)
    'C_3 de Paus'
    >>> truco_basico_maior(Cartas.C_2, Cartas.C_2, Naipes.Espadas, Naipes.Copas)
    'C_2 de Copas'
    '''

    v1 = truco_basico(c1, n1)
    v2 = truco_basico(c2, n2)

    if v1 > v2:
        return c1.name + ' de ' + n1.name

    return c2.name + ' de ' + n2.name
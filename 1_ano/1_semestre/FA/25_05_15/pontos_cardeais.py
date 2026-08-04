from enum import Enum, auto


class Pontos(Enum):
    Norte = auto()
    Leste = auto()
    Sul = auto()
    Oeste = auto()

def pontos_cardeais_oposto(direcao: Pontos) -> Pontos:
    '''
    Retorna a direção oposta conforme a direção fornecida
    >>> pontos_cardeais_oposto(Pontos.Norte).name
    'Sul'
    >>> pontos_cardeais_oposto(Pontos.Leste).name
    'Oeste'
    '''

    if direcao.name == Pontos.Norte.name:
        return Pontos.Sul
    elif direcao.name == Pontos.Sul.name:
        return Pontos.Norte
    elif direcao.name == Pontos.Leste.name:
        return Pontos.Oeste
    else:
        return Pontos.Leste

def pontos_cardeais_90(direcao: Pontos) -> Pontos:
    '''
    Retorna a direção que está a 90º no sentido horário da direção fornecida
    >>> pontos_cardeais_90(Pontos.Norte).name
    'Leste'
    >>> pontos_cardeais_90(Pontos.Oeste).name
    'Norte'
    '''

    if direcao.name == Pontos.Norte.name:
        return Pontos.Leste
    elif direcao.name == Pontos.Sul.name:
        return Pontos.Oeste
    elif direcao.name == Pontos.Leste.name:
        return Pontos.Sul
    else:
        return Pontos.Norte
from enum import Enum, auto

class Combustivel(Enum):
    Alcool = auto()
    Gasolina = auto()
    Hidrogenio = auto()

def escolhe_combustivel(preco_alcool: float, preco_gasolina: float) -> str:
    '''
    Escolhe o combustível a ser utilizado no abastecimento. Retorna: 'álcool' se o preço do álcool for menor ou igual a 70% do preço da gasolina, caso contrário, retorna 'gasolina'
    >>> escolhe_combustivel(4.0, 6.0)
    'álcool'
    >>> escolhe_combustivel(6.0, 4.0)
    'gasolina'
    '''

    if preco_gasolina * 0.7 < preco_alcool:
        return 'gasolina'
    else:
        return 'álcool'

def escolhe_combustivel_enum(preco_alcool: float, preco_gasolina: float) -> Combustivel:
    '''
    Escolhe o combustível a ser utilizado no abastecimento. Retorna: 'álcool' se o preço do álcool for menor ou igual a 70% do preço da gasolina, caso contrário, retorna 'gasolina'
    >>> escolhe_combustivel_enum(4.0, 6.0).name
    'Alcool'
    >>> escolhe_combustivel_enum(6.0, 4.0).name
    'Gasolina'
    '''

    if preco_gasolina * 0.7 < preco_alcool:
        return Combustivel.Gasolina
    else:
        return Combustivel.Alcool

def escolhe_combustivel_tres(preco_alcool: float, preco_gasolina: float) -> Combustivel:
    '''
    Escolhe o combustível a ser utilizado no abastecimento. Retorna: 'álcool' se o preço do álcool for menor ou igual a 70% do preço da gasolina, caso for maior, retorna 'gasolina'. Caso ambos os combustíveis estivem mais de R$10,00, retorna 'hidrogênio'
    >>> escolhe_combustivel_tres(4.0, 6.0).name
    'Alcool'
    >>> escolhe_combustivel_tres(6.0, 4.0).name
    'Gasolina'
    >>> escolhe_combustivel_tres(10.1, 10.2).name
    'Hidrogenio'
    '''

    if preco_alcool > 10.0 and preco_gasolina > 10.0:
        return Combustivel.Hidrogenio
    elif preco_gasolina * 0.7 < preco_alcool:
        return Combustivel.Gasolina
    else:
        return Combustivel.Alcool
# O dono de uma empresa de locação de veículos, João Localiza da Silva, quer sempre maximizar o lucro da empresa e gastar o mínimo possível com combustível.

# O problema é que seus funcionários, sempre que precisam abastecer um carro da frota, fazem manualmente o seguinte cálculo:
# Se o preço do álcool for até 70% do preço da gasolina, eles abastecem com álcool. Caso contrário, abastecem com gasolina.
# O problema é que seus funcionários são muito lentos para fazer esse cálculo. E muitas vezes erram.

# Você é um programador que está desenvolvendo um aplicativo para a empresa. Como você pode ajudar João Localiza da Silva?


# Objetivo: determinar o que deve ser feito.
# I Quais informações são relevantes e quais podem ser
# descartadas?
# I Existe alguma omissão?
# I Existe alguma ambiguidade?
# I Quais conhecimentos do domínio do problema são
# necessários?

# Análise
#
# Determinar o combustível que será utilizado. Se o preço do
# álcool for até 70% do preço da gasolina, então deve-se usar
# álcool, senão gasolina


# Identificação dos dados
#
# definir como as informações serão representadas como
# dados no programa.
# I Quais são as informações envolvidas no problema?
# I Como as informações serão representadas?

# O preço do litro será representado por um número positivo.
# O tipo de combustível será representado por uma string


def escolhe_combustivel(preco_alcool: float, preco_gasolina: float) -> str:
    """
    Escolhe o combustível que deve ser utilizado no abastecimento. Produz:
    'alcool' se o preco_alcool for menor ou igual a 70% do preco_gasolina.
    'gasolina' caso contrário.
    Exemplos:
    >>> escolhe_combustivel (4.00 , 6.00)
    'alcool'
    >>> escolhe_combustivel (3.50 , 5.00)
    'alcool'
    >>> escolhe_combustivel (4.00 , 5.00)
    'gasolina'
    """
    if preco_alcool <= 0.7 * preco_gasolina:
        combustivel = 'alcool'
    else:
        combustivel = 'gasolina'
    return combustivel


def escolhe_combustivel_versao_2(preco_alcool: float, preco_gasolina: float) -> str:
    """
    Escolhe o combustível que deve ser utilizado no abastecimento. Produz:
    'hidrogenio' se o preco_alcool e preco_gasolina forem maior que 10.0
    'alcool' se o preco_alcool for menor ou igual a 70% do preco_gasolina.
    'gasolina' caso contrário.
    Exemplos:
    >>> escolhe_combustivel_versao_2 (4.00 , 6.00)
    'alcool'
    >>> escolhe_combustivel_versao_2 (3.50 , 5.00)
    'alcool'
    >>> escolhe_combustivel_versao_2 (4.00 , 5.00)
    'gasolina'
    >>> escolhe_combustivel_versao_2 (11.0 , 10.50)
    'hidrogenio'
    """
    if preco_alcool > 10.0 and preco_gasolina > 10.0:
        combustivel = 'hidrogenio'
    elif preco_alcool <= 0.7 * preco_gasolina:
        combustivel = 'alcool'
    else:
        combustivel = 'gasolina'
    return combustivel


def escolhe_combustivel(preco_alcool: float, preco_gasolina: float) -> str:
    """
    Escolhe o combustível que deve ser utilizado no abastecimento. Produz:
    'alcool' se o preco_alcool for menor ou igual a 70% do preco_gasolina.
    'gasolina' caso contrário.
    Exemplos:
    >>> escolhe_combustivel (4.00 , 6.00)
    'alcool'
    >>> escolhe_combustivel (3.50 , 5.00)
    'alcool'
    >>> escolhe_combustivel (4.00 , 5.00)
    'gasolina'
    """
    if preco_alcool <= 0.7 * preco_gasolina:
        combustivel = 'alcool'
    else:
        combustivel = 'gasolina'
    return combustivel


from enum import Enum, auto


class Combustivel(Enum):
    ALCOOL = auto()
    GASOLINA = auto()
    HIDROGENIO = auto()


def escolhe_combustivel_enum(preco_alcool: float, preco_gasolina: float) -> Combustivel:
    """
    Escolhe o combustível que deve ser utilizado no abastecimento. Produz:
    'HIDROGENIO' se o preco_alcool e preco_gasolina maior que 10.0
    'ALCOOL' se o preco_alcool for menor ou igual a 70% do preco_gasolina.
    'GASOLINA' caso contrário.
    Exemplos:
    >>> escolhe_combustivel_enum(4.00, 6.00).name
    'ALCOOL'
    >>> escolhe_combustivel_enum(3.50, 5.00).name
    'ALCOOL'
    >>> escolhe_combustivel_enum(4.00, 5.00).name
    'GASOLINA'
    >>> escolhe_combustivel_enum(11.00, 12.00).name
    'HIDROGENIO'
    >>> escolhe_combustivel_enum(1.00, 12.00).name
    'ALCOOL'
    """
    if preco_alcool > 10.0 and preco_gasolina > 10.0:
        combustivel = Combustivel.HIDROGENIO
    elif preco_alcool <= 0.7 * preco_gasolina:
        combustivel = Combustivel.ALCOOL
    else:
        combustivel = Combustivel.GASOLINA
    return combustivel


print(Combustivel.HIDROGENIO.value)
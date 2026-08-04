from enum import Enum, auto

class Temperatura(Enum):
    CONGELANTE = 0
    MUITO_FRIO = 9.9
    FRIO = 19.9
    AGRADÁVEL = 29.9
    QUENTE = auto()

def categoriza_temperatura(temperatura: float) -> Temperatura:
    '''
    Classifica sensorialmente a temperatura em graus Celcius fornecida de acordo com o valor correspondente ao valor do enum
    >>> categoriza_temperatura(-15)
    'CONGELANTE'
    >>> categoriza_temperatura(0.1)
    'MUITO_FRIO'
    >>> categoriza_temperatura(17.4)
    'FRIO'
    >>> categoriza_temperatura(29.8)
    'AGRADÁVEL'
    >>> categoriza_temperatura(50.4)
    'QUENTE'
    '''
    if temperatura < Temperatura.CONGELANTE.value:
        return Temperatura.CONGELANTE.name
    elif temperatura <= Temperatura.MUITO_FRIO.value:
        return Temperatura.MUITO_FRIO.name
    elif temperatura <= Temperatura.FRIO.value:
        return Temperatura.FRIO.name
    elif temperatura <= Temperatura.AGRADÁVEL.value:
        return Temperatura.AGRADÁVEL.name
    else:
        return Temperatura.QUENTE.name
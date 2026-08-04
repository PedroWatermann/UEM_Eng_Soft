from dataclasses import dataclass
from enum import Enum, auto

class Categoria(Enum):
    HATCH = auto()
    SEDÃ = auto()
    SUV = auto()
    PICKUP = auto()

@dataclass
class Carro:
    marca: str
    modelo: str
    ano: int
    cor: str
    categoria: Categoria
    
@dataclass
class Info:
    modelo: str
    ano: int

def verifica_categoria(carros: list[Carro], categoria: Categoria) -> list[Info]:
    """
    Retorna uma lista com o Modelo e o Ano dos *carros* de uma determinada *categoria*.
    >>> carro1: Carro = Carro('Beetle', 'volkswagen', 1940, 'vermelho', Categoria.HATCH)
    >>> carro2: Carro = Carro('Uno', 'fiat', 1950, 'amarelo', Categoria.SEDÃ)
    >>> carro3: Carro = Carro('Camaro', 'chevrolet', 1960, 'azul', Categoria.SUV)
    >>> carro4: Carro = Carro('911', 'porsche', 1970, 'branco', Categoria.PICKUP)
    >>> carro5: Carro = Carro('Dodge', 'hellcat', 1980, 'preto', Categoria.SUV)
    >>> carro6: Carro = Carro('F8', 'ferrari', 1990, 'roxo', Categoria.SEDÃ)
    >>> verifica_categoria([carro1, carro2, carro3, carro4, carro5, carro6], Categoria.HATCH)
    [Info(modelo='volkswagen', ano=1940)]
    >>> verifica_categoria([carro1, carro2, carro3, carro4, carro5, carro6], Categoria.SEDÃ)
    [Info(modelo='fiat', ano=1950), Info(modelo='ferrari', ano=1990)]
    >>> verifica_categoria([carro1, carro2, carro3, carro4, carro5, carro6], Categoria.SUV)
    [Info(modelo='chevrolet', ano=1960), Info(modelo='hellcat', ano=1980)]
    >>> verifica_categoria([carro1, carro2, carro3, carro4, carro5, carro6], Categoria.PICKUP)
    [Info(modelo='porsche', ano=1970)]
    """
    
    selecionados: list[Info] = []
    for c in carros:
        if c.categoria == categoria:
            selecionados.append(Info(c.modelo, c.ano))
    
    return selecionados

from dataclasses import (dataclass)
from enum import Enum, auto

class Multiplicador(Enum):
    Sul = 1
    Sudeste = 1.1
    CentroOeste = 1.2
    Nordeste = 1.3
    Norte = 1.4

class Frete(Enum):
    Base = 0.5
    Volume = 0.001
    Seguro = 0.01

class Categoria(Enum):
    Alimento = auto()
    Eletronico = auto()
    Limpeza = auto()
    Beleza = auto()
    Higiene = auto()
    Automovel = auto()

@dataclass
class Produto:
    Codigo: int
    Nome: str
    Preco: float
    Categoria: Categoria
    Estoque: int
    Peso: float
    Altura: int
    Largura: int
    Profundidade: int

def calcular_frete(produto: Produto, localidade: Multiplicador) -> float:
    """
        Calcula o valor do frete com base no peso, dimensões e valor do produto, e na região de destino.
        Exemplos:
        >>> calcular_frete(Produto(1, 'ovo', 15.00, Categoria.Alimento, 5, 3, 10, 40, 50), Multiplicador.Sul)
        21.65
        >>> calcular_frete(Produto(1, 'ovo', 10.00, Categoria.Alimento, 5, 3, 10, 40, 50), Multiplicador.CentroOeste)
        25.92
        """
    frete_base = produto.Peso * Frete.Base.value
    frete_volume = produto.Altura * produto.Largura * produto.Profundidade * Frete.Volume.value
    frete_seguro = produto.Preco * Frete.Seguro.value

    return (frete_base + frete_volume + frete_seguro) * localidade.value

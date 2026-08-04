from enum import Enum, auto
from dataclasses import dataclass

class Temperatura(Enum):
    Amena = auto()
    Fria = auto()
    Quente = auto()
    Congelante = auto()
    Muito_quente = auto()

class Nivel_Alerta(Enum):
    Nenhum = auto()
    Baixo = auto()
    Moderado = auto()
    Alto = auto()
    Extremo = auto()

@dataclass
class Alerta:
    Temperatura: Temperatura
    Alerta: Alerta

def classificar_temperatura():

    return 0

def definir_nivel_alerta():
    return 0
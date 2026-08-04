from dataclasses import dataclass
from enum import Enum, auto

class Status(Enum):
    EM_ANDAMENTO = auto()
    EM_PRODUCAO = auto()
    EM_FINALIZACAO = auto()
    FINALIZADO = auto()

@dataclass
class Pedido:
    número: int
    produto: str
    valor: float
    tempo_de_espera: int

def fluxo_lanchonete(pedido: Pedido, status_atual: Status) -> Status:
    """
    Retorna o *status* em que o *pedido* deveria estar, baseado no tempo de espera.
    >>> fluxo_lanchonete(Pedido(2, 'a', 2.2, 8), Status.EM_FINALIZACAO).name
    'FINALIZADO'
    >>> fluxo_lanchonete(Pedido(2, 'a', 2.2, 6), Status.EM_PRODUCAO).name
    'EM_FINALIZACAO'
    >>> fluxo_lanchonete(Pedido(2, 'a', 2.2, 22), Status.EM_ANDAMENTO).name
    'FINALIZADO'
    >>> fluxo_lanchonete(Pedido(2, 'a', 2.2, 22), Status.FINALIZADO).name
    'FINALIZADO'
    """
    
    status_correto: Status = Status.EM_ANDAMENTO
    indice: int = 0
    indice_2: int = 0
    auxiliar: int = 0
    temporario: int = pedido.tempo_de_espera // 5
    
    for s in Status:
        if s != status_atual:
            indice += 1
        if s == status_atual:
            auxiliar = indice
    
    for s in Status:
        if auxiliar + temporario >= 3 and indice_2 == 3:
            status_correto = s
        elif indice_2 == auxiliar + temporario:
            status_correto = s
        indice_2 += 1
    
    return status_correto

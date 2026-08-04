from dataclasses import dataclass
from enum import Enum, auto

class PesoNotas(Enum):
    Prova = 0.6
    Trabalho = 0.3
    Participacao = 0.1

@dataclass
class Aluno:
    Codigo: int
    Nome: str
    NotaProva: float
    NotaTrabalho: float
    NotaParticipacao: float

def nota_final(aluno: Aluno) -> float:
    '''
    Calcula a nota final de um aluno com base em suas notas e seus respectivos pesos
    >>> nota_final(Aluno(1, 'Pedro', 8.8, 9.7, 3.2))
    8.51
    >>> nota_final(Aluno(1, 'Pedro', 1.8, 5.7, 9.2))
    3.71
    >>> nota_final(Aluno(1, 'Pedro', 8.0, 7.5, 9.0))
    7.95
    '''

    p = PesoNotas
    return (aluno.NotaProva * p.Prova.value) + (aluno.NotaTrabalho * p.Trabalho.value) + (aluno.NotaParticipacao * p.Participacao.value)
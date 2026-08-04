from enum import Enum, auto
from dataclasses import dataclass

class Materia(Enum):
    MATEMÁTICA = auto()
    PORTUGUÊS = auto()
    CIÊNCIAS = auto()

class Turma(Enum):
    TURMA_A = auto()
    TURMA_B = auto()
    TURMA_C = auto()

@dataclass
class Aluno:
    Nome: str
    Idade: int
    Turma: Turma
    Matéria: Materia

def altera_materia_aluno(aluno: Aluno, nova_matéria: Materia) -> Aluno:
    '''
    Altera a matéria cursada por um aluno com base na nova matéria e no aluno fornecidos
    >>> altera_materia_aluno(Aluno("Pedro",17,Turma.TURMA_A,Materia.PORTUGUÊS), Materia.MATEMÁTICA).Matéria
    <Materia.MATEMÁTICA: 1>
    >>> altera_materia_aluno(Aluno("Maria",19,Turma.TURMA_B,Materia.CIÊNCIAS), Materia.PORTUGUÊS).Matéria
    <Materia.PORTUGUÊS: 2>
    '''
    aluno.Matéria = nova_matéria
    return aluno

def altera_turma_aluno(aluno: Aluno, nova_turma: Turma) -> Aluno:
    '''
    Altera a turma que o aluno está matriculado com base na nova turma e no aluno fornecidos
    >>> altera_turma_aluno(Aluno("Pedro",17,Turma.TURMA_A,Materia.PORTUGUÊS), Turma.TURMA_C).Turma
    <Turma.TURMA_C: 3>
    >>> altera_turma_aluno(Aluno("Maria",19,Turma.TURMA_C,Materia.CIÊNCIAS), Turma.TURMA_B).Turma
    <Turma.TURMA_B: 2>
    '''
    aluno.Turma = nova_turma
    return aluno

aluno1 = Aluno(
    "Pedro",
    17,
    Turma.TURMA_A,
    Materia.PORTUGUÊS
)

aluno2 = Aluno(
    "Maria",
    19,
    Turma.TURMA_B,
    Materia.CIÊNCIAS
)

print(f"Aluno 1: \n{aluno1}")
print(f"Aluno 2: \n{aluno2}\n\n")

print("Novo aluno 1: ")
print(altera_materia_aluno(aluno1, Materia.MATEMÁTICA))
print(altera_turma_aluno(aluno1, Turma.TURMA_C))

print("\n\nNovo aluno 2:")
print(altera_materia_aluno(aluno2, Materia.MATEMÁTICA))
print(altera_turma_aluno(aluno2, Turma.TURMA_C))

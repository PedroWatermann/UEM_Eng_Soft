from enum import Enum, auto
from dataclasses import dataclass
from datetime import date

class Prioridade(Enum):
    Baixa = auto()
    Media = auto()
    Alta = auto()

class Status(Enum):
    Nao_Iniciada = auto()
    Em_Andamento = auto()
    Aguardando = auto()
    Concluida = auto()

@dataclass
class Funcionario:
    Id: int
    Nome: str
    Departamento: str
    Tarefa: Tarefa

@dataclass
class Tarefa:
    Id = int
    Titulo: str
    Descricao: str
    Prazo: date
    Prioridade: Prioridade
    Status: Status
    Responsavel: Funcionario

def funcionario_esta_disponivel(fun: Funcionario) -> bool:
    if fun.Tarefa.Titulo == "":
        return True
    return False

def tarefa_tem_maior_prioridade(tarefa_atual: Tarefa, tarefa_nova: Tarefa) -> bool:
    if tarefa_atual.Prioridade.value < tarefa_nova.Prioridade.value:
        return True
    return False

def atribuir_tarefa(funcionario: Funcionario, tarefa: Tarefa) -> bool:
    if funcionario_esta_disponivel(funcionario) or (not funcionario_esta_disponivel(funcionario) and tarefa_tem_maior_prioridade(funcionario.Tarefa, tarefa)):
        funcionario.Tarefa = tarefa
        return True
    return False
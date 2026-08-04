# Números magicos
def mensagem_semaforo(estado: int) -> str:
    """
    Retorna a mensagem correspondente ao estado do semáforo.
    """
    if estado == 0:
        return "Pare"
    elif estado == 1:
        return "Atenção"
    elif estado == 2:
        return "Siga em frente"
    else:
        return "Cor inválida"


# Alguém lendo esse código não sabe o que 0 ou 2 significam
print(mensagem_semaforo(0))
print(mensagem_semaforo(2))

# enum

from enum import Enum


class Semaforo(Enum):
    VERMELHO = 0
    AMARELO = 1
    VERDE = 2


def mensagem_semaforo(estado: Semaforo) -> str:
    """
    Retorna a mensagem correspondente ao estado do semáforo.
    """
    if estado == Semaforo.VERMELHO:
        return "Pare"
    elif estado == Semaforo.AMARELO:
        return "Atenção"
    elif estado == Semaforo.VERDE:
        return "Siga em frente"
    else:
        return "Cor inválida"


print(mensagem_semaforo(Semaforo.VERMELHO))
print(mensagem_semaforo(Semaforo.AMARELO))
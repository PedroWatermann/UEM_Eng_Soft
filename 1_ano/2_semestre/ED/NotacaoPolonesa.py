from enum import Enum
from dataclasses import dataclass

@dataclass
class TpPilha:
    Elem: list[int]
    Topo: int

class Simbolo(Enum):
    SOMA = "+"
    SUBTRACAO = "-"
    MULTIPLICACAO = "*"
    DIVISAO = "/"
    PONTO = "."

TAM_MAX: int = 50

def PilhaCheia(P: TpPilha) -> bool:
    return P.Topo >= TAM_MAX

def PilhaVazia(P: TpPilha) -> bool:
    return P.Topo <= 0

def Empilha(P: TpPilha, x: int) -> bool:
    if not PilhaCheia(P):
        P.Elem[P.Topo] = x
        P.Topo += 1
        return True
    return False

## Necessária a implementação da tupla, pois ao remover um elemento de um apilha vazia é necessário retornar algo. Se retornasse apenas o número, quebraria a lógica do código
def Desempilha(P: TpPilha) -> tuple[int, bool]:
    if not PilhaVazia(P):
        P.Topo -= 1
        return (P.Elem[P.Topo], True)
    return (-1, False)

def InicializaPilha() -> TpPilha:
    P: TpPilha = TpPilha([0] * TAM_MAX, 0)
    return P

def ReadFile(path: str) -> list[str]:    
    with open(path, "r", encoding="utf-8") as file:
        return file.readlines()

## Necessária a implementação da tupla devido às mensagens de erro
def ExecutaOperacao(P: TpPilha, elemento: str) -> tuple[str, bool]:
    x: tuple[int, bool] = Desempilha(P)
    y: tuple[int, bool] = Desempilha(P)
    
    if x[1] and y[1]:
        if elemento == Simbolo.SOMA.value:
            Empilha(P, y[0] + x[0])
        elif elemento == Simbolo.SUBTRACAO.value:
            Empilha(P, y[0] - x[0])
        elif elemento == Simbolo.MULTIPLICACAO.value:
            Empilha(P, y[0] * x[0])
        elif elemento == Simbolo.DIVISAO.value:
            if x[0] == 0: return ("Houve uma divisão por 0", False)
            Empilha(P, y[0] // x[0])
        return ("", True)
    
    return ("Excesso de operações", False)

def NotacaoPolonesa(expressao: str, indice: int) -> str:
    P: TpPilha = InicializaPilha()
    caractere: str = ""
    retorno: tuple[str, bool] = ("", False)
    
    for elemento in expressao:
        if elemento in (Simbolo.SOMA.value, Simbolo.SUBTRACAO.value, Simbolo.MULTIPLICACAO.value, Simbolo.DIVISAO.value):
            retorno = ExecutaOperacao(P, elemento)
            if not retorno[1]:
                return f"[ERRO] A expressão {indice + 1} está incorreta: {retorno[0]}."
        elif elemento == Simbolo.PONTO.value:
            if not Empilha(P, int(caractere)):
                return f"[ERRO] A expressão {indice + 1} está incorreta: Houve um estouro de pilha."
            caractere = ""
        else:
            caractere += elemento
    
    x: tuple[float, bool] = Desempilha(P)
    resultado: str = str(x[0])
    
    return f"A resposta da expressão {indice + 1} é {resultado}." if PilhaVazia(P) else f"[ERRO] A expressão {indice + 1} está incorreta: Excesso de operandos."

def main() -> None:
    expressoes: list[str] = []
    
    path: str = input("Digite o caminho do arquivo: ")
    expressoes = ReadFile(path)
    tam_exp: int = len(expressoes)
    
    for i in range(tam_exp):
        ## A condição dentro da função faz a remoção do \n no final de cada expressão
        print(NotacaoPolonesa(expressoes[i][:-1] if i < tam_exp - 1 else expressoes[i], i))

if __name__ == "__main__":
    main()

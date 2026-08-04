TamMax = 50

class pilha:
    pilha_vetor = [0] * TamMax
    topo = -1


def inicializa_pilha(p):
    p.topo = -1


def pilha_vazia(p):
    if p.topo == -1:
        return True
    return False


def pilha_cheia(p):
    if p.topo == TamMax - 1:
        return True
    return False


def empilha(p, x):
    if pilha_cheia(p) != True:
        p.topo = p.topo + 1
        p.pilha_vetor[p.topo] = x


def desempilha(p):
    if pilha_vazia(p) != True:
        x = p.pilha_vetor[p.topo]
        p.topo = p.topo - 1
        return x
    return None


def avaliar_posfixa(lista, p):
    concatena = ""

    for i in range(len(lista)):
        if lista[i] != "." and lista[i] != "+" and lista[i] != "-" and lista[i] != "*" and lista[i] != "/":
            concatena = concatena + lista[i]
        elif lista[i] == "+" or lista[i] == "-" or lista[i] == "*" or lista[i] == "/":
            num1 = desempilha(p)
            num2 = desempilha(p)
            resultado = 0
            
            if num1 is None or num2 is None:
                return "ERRO: Faltando operando"
            
            if lista[i] == "+":
                resultado = num2 + num1
            elif lista[i] == "-":
                resultado = num2 - num1
            elif lista[i] == "*":
                resultado = num2 * num1
            elif lista[i] == "/":
                if num1 == 0:
                    return "ERRO: Divisão por 0"
                resultado = num2 // num1
            
            empilha(p, resultado)
        else:
            empilha(p, int(concatena))
            concatena = ""

    resultado = desempilha(p)

    if pilha_vazia(p) != True:
        return "ERRO: Faltando operador"

    return resultado


def main():
    arquivo = open("notacao.txt", "r")
    num_linha = 1

    for linha in arquivo:
        lista = list(linha)

        p = pilha()
        inicializa_pilha(p)

        r = avaliar_posfixa(lista, p)

        print("Linha", num_linha, "=", r)
        num_linha += 1

    arquivo.close()

main()
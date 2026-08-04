def parametro(lista: list, n: int):
    lista.append(5)
    n = 5

def principal() -> None:
    lista: list = [1, 2, 3, 4]
    n: int = 1
    print(lista)
    print(n)
    parametro(lista, n)
    print(lista)
    print(n)

principal()
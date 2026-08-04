## Projete uma função que encontre o valor máximo de uma lista com 5 números

def valor_maximo(lista: list[int]) -> int:
    """
    Encontra o valor máximo dentre os números da lista fornecida.
    >>> valor_maximo([1, 2, 3])
    3
    >>> valor_maximo([7, 4, 6, 2, 9])
    9
    >>> valor_maximo([0, 8, 7, 6, 5, 4, 3, 2, 1])
    8
    """
    max = 0
    for n in lista:
        if n > max:
            max = n
    return max
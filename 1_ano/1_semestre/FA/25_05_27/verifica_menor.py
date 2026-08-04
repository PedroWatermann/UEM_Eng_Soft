def verifica_menor(lista: list[int]) -> int:
    """
    Verifica quantos elementos da lista de valores inteiros fornecida são menores que 10.
    >>> verifica_menor([1, 2, 3, 4, 5])
    5
    >>> verifica_menor([10, 20, 30, 40, 50])
    0
    >>> verifica_menor([1, 8, 9, 10, 11])
    3
    """
    
    contador: int = 0
    for n in lista:
        if n < 10:
            contador += 1
    return contador
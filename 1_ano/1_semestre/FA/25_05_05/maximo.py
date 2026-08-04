def maximo2(n1: int, n2: int) -> int:
    '''Comparar 2 números e retornar o maior deles
    >>> maximo2(2, 3)
    3
    >>> maximo2(3, 2)
    3'''

    if n1 > n2:
        return n1
    else:
        return n2

def maximo(n1: int, n2: int, n3: int) -> int:
    '''Comparar 3 números e retornar o maior entre os 3
    >>> maximo(2, 3, 4)
    4
    >>> maximo(2, 4, 3)
    4
    >>> maximo(4, 2, 3)
    4'''

    # Primeira forma de ser resolvido
    #if n1 > n2 and n1 > n3:
    #    return n1
    #elif n2 > n1 and n2 > n3:
    #    return n2
    #else:
    #    return n3

    # Resolvendo com reutilização de funções
    return maximo2(n1, maximo2(n2, n3))
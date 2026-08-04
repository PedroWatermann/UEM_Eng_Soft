def repete_elemento(n: int, lista1: list[int]) -> int:
    """
    Retorna quantas vezes o elemento *n* aparece em *lista1*
    >>> repete_elemento(1, [1, 3, 2, 1, 4, 1])
    3
    >>> repete_elemento(3, [2, 5, 4, 1, 6, 7])
    0
    """
    
    if len(lista1) == 0:
        return 0
    else:
        if n == lista1[0]:
            return 1 + repete_elemento(n, lista1[1:])
        else:
            return 0 + repete_elemento(n, lista1[1:])

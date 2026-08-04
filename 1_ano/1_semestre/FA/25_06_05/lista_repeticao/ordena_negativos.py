def ordena_negativos(lst: list[int]) -> list[int]:
    """
    Ordena a lista *lst* fornecida colocando os valores negativos antes dos valores positivos.
    >>> ordena_negativos([-1, 3, 4, -4, -2, -9, 0])
    [-1, -4, -2, -9, 3, 4, 0]
    >>> ordena_negativos([1, 2, 7, 2, 9, 6, 0])
    [1, 2, 7, 2, 9, 6, 0]
    >>> ordena_negativos([-5, -2, -9, -1, -8])
    [-5, -2, -9, -1, -8]
    """
    
    nova_lst: list[int] = []
    
    for i in lst:
        if i < 0:
            nova_lst.append(i)
    for i in lst:
        if i >= 0:
            nova_lst.append(i)
    
    return nova_lst
def lista_recursividade(lista1: list[int]) -> int:
    """
    Soma os elementos de *lista1*
    >>> lista_recursividade([1, 3, 7, 4])
    15
    >>> lista_recursividade([3, 1])
    4
    """
    
    if len(lista1) == 0:
        return 0
    else:
        return lista_recursividade(lista1[1:]) + lista1[0]
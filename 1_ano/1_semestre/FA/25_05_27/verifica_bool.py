def verifica_bool(lista: list[bool]) -> bool:
    """
    Verifica se todos os elementos da lista de valores booleanos fornecida são falsos.
    >>> verifica_bool([False, False, False, True])
    False
    >>> verifica_bool([False, False, True, False])
    False
    >>> verifica_bool([False, False, False, False])
    True
    """
    
    for b in lista:
        if b:
            return False
    return True
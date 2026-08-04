def verifica_elemento(lista: list[int], num: int) -> bool:
    '''
    Verifica se o elemento *num* está na *lista*.
    >>> verifica_elemento([1, 2, 3, 4], 5)
    False
    >>> verifica_elemento([1, 2, 3, 4], 3)
    True
    '''
    
    pos: int = 0
    resp: bool = False
    
    while not resp and pos < len(lista):
        if lista[pos] == num:
            resp = True
        
        pos += 1
    
    return resp
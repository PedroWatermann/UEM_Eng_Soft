def verifica_quantidade(lista: list[float]) -> bool:
    '''
    Verifica se a *lista* de floats possui menos de 4 elementos.
    >>> verifica_quantidade([1.2, 3.5, 3.4])
    True
    >>> verifica_quantidade([1.2, 4.5, 2.3, 7.6])
    False
    '''
    
    pos: int = 0
    res: bool = True
    
    while res and pos < len(lista):
        if pos == 3:
            res = False
        
        pos += 1
        
    return res
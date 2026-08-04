def primo(n: int) -> bool:
    '''
    Verifica se *n* é um número primo e retorna Trueou False.
    Exemplos:
    >>> primo(8)
    False
    >>> primo(11)
    True
    '''
    
    """ 
    num_divisores: int = 0
    
    for i in range(2, n + 1):
        if n % i == 0:
            num_divisores += 1 
    
    if num_divisores == 2:
        True
    
    return False
    """
    
    """ 
    for i in range(2, n // 2 + 1):
        if n % i == 0:
            return False
    
    return True
    """
    
    num_div: int = 0
    for i in range(1, n + 1):
        if n % i == 0:
            num_div += 1
        if num_div > 2:
            return False
    return True

primo(8)
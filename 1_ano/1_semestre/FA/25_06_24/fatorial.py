def fatorial(n: int) -> int:
    """ 
    Calcula e retorna o fatorial de *n*.
    >>> fatorial(5)
    120
    >>> fatorial(20)
    2432902008176640000
    >>> fatorial(0)
    1
    """
    
    res: int = 1
    while n > 1:
        res *= n
        n -= 1
    
    return res
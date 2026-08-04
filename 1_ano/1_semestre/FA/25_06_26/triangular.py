def triangular(n: int) -> bool:
    """ 
    Retorna True se o número inteiro *n* for um número triangular, ou seja, se *n* é um produto de 3 inteiros consecutivos, ou False caso contrário.
    Exemplos:
    >>> triangular(120)
    True
    >>> triangular(1320)
    True
    >>> triangular(40)
    False
    >>> triangular(3360)
    True
    """
    
    res: bool = False
    count: int = 1
    divisor: int = 0
    
    while count <= n / 2 and not res:
        if n % count == 0:
            divisor = count
            if divisor * (divisor + 1) * (divisor + 2) == n:
                res = True
        
        count += 1

    return res

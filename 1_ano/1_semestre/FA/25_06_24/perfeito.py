def perfeito(n: int):
    """
    Verifica se o número inteiro positivo *n* é perfeito, ou seja, quando a soma de seus divisores é igual a *n*.
    >>> perfeito(6)
    True
    >>> perfeito(28)
    True
    >>> perfeito(4)
    False
    """
    
    divisor: int = 1
    soma_div: int = 0
    
    while soma_div < n and divisor <= n / 2:
        if n % divisor == 0:
            soma_div += divisor
        divisor += 1
    
    if soma_div == n:
        return True
    
    return False

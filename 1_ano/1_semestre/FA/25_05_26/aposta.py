def sorteado(n: int, sorteados: list[int]) -> int:
    """
    Produz True se *n* é um dos números em *sorteados*. False caso contrário.
    >>> sorteados = [1, 7, 10, 40, 41, 60]
    >>> sorteado(1, sorteados)
    True
    >>> sorteado(7, sorteados)
    True
    >>> sorteado(10, sorteados)
    True
    >>> sorteado(40, sorteados)
    True
    >>> sorteado(41, sorteados)
    True
    >>> sorteado(60, sorteados)
    True
    >>> sorteado(2, sorteados)
    False
    """
    if n in sorteados:
        res: bool = True
    else:
        res: bool = False
    return res

def numero_acertos(aposta: list[int], sorteados: list[int]) -> int:
    """
    Determina quantos números da *aposta* estão em *sorteados*.
    >>> numero_acertos([1, 2, 3, 4, 5, 6], [8, 12, 20, 41, 52, 57])
    0
    >>> numero_acertos([8, 2, 3, 4, 5, 6], [8, 12, 20, 41, 52, 57])
    1
    >>> numero_acertos([8, 12, 20, 4, 5, 6], [8, 12, 20, 41, 52, 57])
    3
    >>> numero_acertos([8, 12, 20, 41, 52, 57], [8, 12, 20, 41, 52, 57])
    6
    """
    acertos: int = 0
    for n in aposta:
        if (sorteado(n, sorteados)):
            acertos += 1
    return acertos
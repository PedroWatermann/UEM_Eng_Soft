def divide_matriz(m: list[list[int]]) -> list[list[float]]:
    """ 
    Encontra o maior elemento da diagonal principal da matriz 3x3 *m* e divide todos os elemntos dessa matriz pelo maior número encontrado.
    Exemplos:
    >>> divide_matriz([[1, 2, 3], [4, 5,6 ], [7, 8, 9]])
    [[0.1111111111111111, 0.2222222222222222, 0.3333333333333333], [0.4444444444444444, 0.5555555555555556, 0.6666666666666666], [0.7777777777777778, 0.8888888888888888, 1.0]]
    >>> divide_matriz([[2, 4, 6], [8, 10, 12], [14, 16, 2]])
    [[0.2, 0.4, 0.6], [0.8, 1.0, 1.2], [1.4, 1.6, 0.2]]
    """
    
    maior: int = 0
    for p in range(len(m)):
        if m[p][p] > maior:
            maior = m[p][p]
            
    matriz: list[list[float]] = []
    for l in range(len(m)):
        linha: list[float] = []
        for c in range(len(m[l])):
            linha.append(m[l][c] / maior)
        matriz.append(linha)
    
    return matriz

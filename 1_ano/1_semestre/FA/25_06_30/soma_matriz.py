def soma_matriz(m: list[list[int]]) -> int:
    """ 
    Recebe os valores da matriz 4x3 *m* e retorna a soma deles.
    Exemplos:
    >>> soma_matriz([[1, 2, 3], [4, 5, 6], [7, 8, 9], [10, 11, 12]])
    78
    >>> soma_matriz([[3, 6, 1], [8, 3, 8], [0, 9, 1], [5, 7, 2]])
    53
    """
    
    soma: int = 0
    for l in range(len(m)):
        for c in range(len(m[l])):
            soma += m[l][c]
    return soma
def bubble_sort_bubble(arranjo: list):
    """
    Ordena uma lista de números colocando o maior elemento ao final e sequencialmente coloca o menor no início
    >>> bubble_sort_bubble([3, 2, 5, 1, 4])
    [1, 2, 3, 4, 5]
    """
    
    n = len(arranjo)
    for i in range(n):
        for j in range(0, n - i - 1):
            if arranjo[j] > arranjo[j + 1]:
                aux: int = arranjo[j]
                arranjo[j] = arranjo[j + 1]
                arranjo[j + 1] = aux
        for k in range(n - i - 2, 0, -1):
            if arranjo[k] < arranjo[k - 1]:
                aux: int = arranjo[k]
                arranjo[k] = arranjo[k - 1]
                arranjo[k - 1] = aux
    return arranjo

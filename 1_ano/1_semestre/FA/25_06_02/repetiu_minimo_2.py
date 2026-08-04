def repetiu_minimo(lista: list[int]) -> int:
    """
    Conta quantas vezes o valor mínimo da lista de inteiros não vazia fornecida aparece.
    >>> repetiu_minimo([1, 2, 3, 1, 1, 4, 5])
    3
    >>> repetiu_minimo([1, 2, 3, 4, 5, 6, 7])
    1
    >>> repetiu_minimo([7, 2, 0, 8, 9, 1, 0])
    2
    """
    
    minimo: int = lista[0]
    contador: int = 0
    
    for n in range(1, len(lista)):
        if lista[n] < minimo:
            minimo = lista[n]
    
    for m in lista:
        if m == minimo:
            contador += 1

    return contador
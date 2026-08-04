def adiciona_i(lista: list[int], n: int, i: int) -> list[int]:
    """
    Adiciona um número *n* fornecido na posição *i* especificada.
    >>> adiciona_i([1, 2, 3, 5], 4, 3)
    [1, 2, 3, 4, 5]
    >>> adiciona_i([1, 2, 3, 4], 0, 0)
    [0, 1, 2, 3, 4]
    >>> adiciona_i([1, 2, 3, 4], 5, 4)
    [1, 2, 3, 4, 5]
    """
    
    lista_final: list[int] = []     
        
    for j in range(0, i):
        lista_final.append(lista[j])
    
    lista_final.append(n)
    
    for k in range(i, len(lista)):
        lista_final.append(lista[k])   
    
    return lista_final
def troca_caractere(lista: list[str], c1: str, c2: str) -> list[str]:
    """
    Troca todas as ocorrências do caractere *c1* selecionado pelo novo caractere *c2* informado.
    >>> troca_caractere(['b', 'b', 'c', 'd'], 'b', 'a')
    ['a', 'a', 'c', 'd']
    >>> troca_caractere(['a', 'b', 'c', 'd'], 'd', 'x')
    ['a', 'b', 'c', 'x']
    >>> troca_caractere(['o', 'x', 'y', 'o'], 'o', 'z')
    ['z', 'x', 'y', 'z']
    """
    
    for n in range(len(lista)):
        if lista[n] == c1:
            lista[n] = c2
    
    return lista

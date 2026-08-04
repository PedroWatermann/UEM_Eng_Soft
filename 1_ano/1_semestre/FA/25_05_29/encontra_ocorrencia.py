def encontra_ocorrencia(lista: list[str], nome: str) -> list[int]:
    """
    Encontra a posição de todas as ocorrencias do *nome* fornecido na *lista* de nomes fornecida.
    >>> encontra_ocorrencia(['Maria', 'João', 'Pedro', 'Pedro', 'Douglas'], 'Pedro')
    [2, 3]
    >>> encontra_ocorrencia(['Maria', 'João', 'Pedro', 'Pedro', 'Douglas'], 'Maria')
    [0]
    >>> encontra_ocorrencia(['Maria', 'João', 'Pedro', 'Pedro', 'Douglas'], 'Tiago')
    []
    """
    
    lst_pos: list[int] = []
    pos = 0
    for e in lista:
        if e == nome:
            lst_pos.append(pos)
        pos += 1
    
    return lst_pos

def caca_palavras(m: list[list[str]], p: str):
    """ 
    Recebe uma matriz *m* de letras e uma palavra *p* para ser encontrada. Serão retornadas as posições das letas das ocorrências da palavra.
    Exemplo:
    >>> caca_palavras([['A', 'C', 'A', 'S', 'A', 'M', 'C', 'A', 'S', 'W', 'B'], ['C', 'A', 'X', 'R', 'F', 'T', 'P', 'A', 'S', 'A', 'C'], ['A', 'S', 'X', 'I', 'E', 'J', 'H', 'W', 'T', 'Q', 'A'], ['S', 'A', 'E', 'O', 'W', 'M', 'Q', 'Z', 'O', 'D', 'B'], ['A', 'C', 'Y', 'K', 'F', 'W', 'C', 'A', 'A', 'V', 'B']], 'CASA')
    [[[0], [1, 2, 3, 4]], [[1, 2, 3, 4], [0]], [[0, 1, 2, 3], [1]]]
    >>> caca_palavras([['A', 'R', 'A', 'R', 'A', 'R', 'A', 'R', 'A', 'R', 'A'], ['R', 'A', 'X', 'R', 'F', 'T', 'P', 'A', 'S', 'A', 'C'], ['A', 'S', 'X', 'I', 'E', 'J', 'H', 'W', 'T', 'Q', 'A'], ['R', 'A', 'E', 'O', 'W', 'M', 'Q', 'Z', 'O', 'D', 'B'], ['A', 'A', 'E', 'O', 'W', 'M', 'Q', 'Z', 'O', 'D', 'B'], ['R', 'A', 'E', 'O', 'W', 'M', 'Q', 'Z', 'O', 'D', 'B'], ['A', 'A', 'E', 'O', 'W', 'M', 'Q', 'Z', 'O', 'D', 'B'], ['A', 'C', 'Y', 'K', 'F', 'W', 'C', 'A', 'A', 'V', 'B']], 'ARARA')
    [[[0], [0, 1, 2, 3, 4]], [[0], [2, 3, 4, 5, 6]], [[0], [4, 5, 6, 7, 8]], [[0], [6, 7, 8, 9, 10]], [[0, 1, 2, 3, 4], [0]], [[2, 3, 4, 5, 6], [0]]]
    """
    
    pal: list[str] = []
    tam_pal: int = len(p)
    
    qtd_lin: int = len(m)
    qtd_col: int = len(m[0])
    lin: int = 0
    col: int = 0
    pos: int = 0
    
    pos_linha: list[int] = []
    pos_coluna: list[int] = []
    m_temp: list[list[int]] = []
    m_resposta: list[list[list[int]]] = []
    
    for i in p:
        pal.append(i)
    
    for lin in range(qtd_lin):
        for c in range(qtd_col):
            if c <= qtd_col - tam_pal:
                pos_coluna = []
                pos_linha = []
                m_temp = []
                pos = 0
                col = c
                while pos < tam_pal and m[lin][col] == pal[pos]:
                    pos_coluna.append(col)
                    if pos == tam_pal - 1:
                        pos_linha.append(lin)
                    pos += 1
                    col += 1
                if pos == tam_pal:
                    m_temp.append(pos_linha)
                    m_temp.append(pos_coluna)
                    m_resposta.append(m_temp)
    
    for col in range(qtd_col):
        for l in range(qtd_lin):
            if l <= qtd_lin - tam_pal:
                pos_coluna = []
                pos_linha = []
                m_temp = []
                pos = 0
                lin = l
                while pos < tam_pal and m[lin][col] == pal[pos]:
                    pos_linha.append(lin)
                    if pos == tam_pal - 1:
                        pos_coluna.append(col)
                    pos += 1
                    lin += 1
                if pos == tam_pal:
                    m_temp.append(pos_linha)
                    m_temp.append(pos_coluna)
                    m_resposta.append(m_temp)
                        
    return m_resposta

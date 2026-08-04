def splited(frase: str):
    """ 
    Transforma a *frase* em uma lista de strings, delimitadas pelo espaço em branco ' '.
    Exemplos:
    >>> splited('Fundamentos de Algoritmos')
    ['Fundamentos', 'de', 'Algoritmos']
    >>> 
    """
    
    tam: int = len(frase)
    count: int = 0
    lista_frase: list[str] = []
    palavra: str = ''
    letra: str = ''
    
    while count < tam:
        letra = frase[count]
        
        if letra != ' ':
            palavra += letra
        
        if count == tam - 1 or letra == ' ':
            lista_frase.append(palavra)
            palavra = ''
        
        count += 1
    
    return lista_frase

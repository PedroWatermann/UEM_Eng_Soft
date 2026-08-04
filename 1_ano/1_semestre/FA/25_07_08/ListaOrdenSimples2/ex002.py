def ordena_palavra(palavra: str):
    """
    Ordena as letras da *palavra* de forma crescente.
    >>> ordena_palavra('pedro')
    ['d', 'e', 'o', 'p', 'r']
    """
    letras: list[str] = []
    for l in palavra:
        letras.append(l)
    
    for i in range(1, len(letras)):
        pivo: str = letras[i]
        j = i - 1
        while j >= 0 and pivo < letras[j]:
            letras[j + 1] = letras[j]
            j -= 1
        letras[j + 1] = pivo
    
    return letras
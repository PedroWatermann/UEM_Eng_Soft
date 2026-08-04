def retorna_sobrenome(nome: str, sobrenome: str):
    """
    Recebe o nome e o sobrenome e retorna uma única string no formato 'sobrenome, nome'.
    >>> retorna_sobrenome('Pedro', 'Watermann')
    'Watermann, Pedro'
    >>> retorna_sobrenome('José', 'Silva')
    'Silva, José'
    >>> retorna_sobrenome('Epaminondas', 'Souza')
    'Souza, Epaminondas'
    """
    
    return sobrenome + ', ' + nome

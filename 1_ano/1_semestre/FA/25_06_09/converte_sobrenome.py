def converte_sobrenome(nome: str):
    """
    Recebe um nome complete e o converte no formato 'nome, sobrenome'.
    >>> converte_sobrenome('Pedro Augusto Watermann')
    'Watermann, Pedro Augusto'
    >>> converte_sobrenome('José da Silva')
    'Silva, José da'
    >>> converte_sobrenome('Epaminondas de Souza')
    'Souza, Epaminondas de'
    """
    
    branco: int = 0
    for n in range(0, len(nome)):
        if nome[n] == ' ':
            branco = n
    
    return nome[branco + 1:] + ', ' + nome[: branco]
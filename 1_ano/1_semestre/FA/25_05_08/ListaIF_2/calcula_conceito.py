def calcula_conceito(media: float) -> str:
    '''
    Calcula o conceito de um aluno com base em sua média final
    >>> calcula_conceito(3.2)
    'D'
    >>> calcula_conceito(5.5)
    'C'
    >>> calcula_conceito(7.0)
    'B'
    >>> calcula_conceito(9.0)
    'A'
    '''

    if media <= 4.9:
        return "D"
    elif media <= 6.9:
        return "C"
    elif media <= 8.9:
        return "B"
    else:
        return "A"
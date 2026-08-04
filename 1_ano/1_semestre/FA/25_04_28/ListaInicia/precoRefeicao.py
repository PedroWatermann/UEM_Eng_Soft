def precoRefeicao(peso: float) -> float:
    '''Calcula o valor a ser pago pela refeição com base no *peso*, em gramas, do prato do cliente.
    >>> precoRefeicao(1000)
    50.0
    >>> precoRefeicao(750)
    37.5'''

    return peso * 0.05

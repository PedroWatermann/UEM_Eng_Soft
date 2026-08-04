def qtdeAzulejos(comprimento: float, altura: float) -> int:
    '''Calcula, com base no *comprimento* e na *altura* de uma parede, em metros, a quantidade de azulejos necessários
    para azulejá-la.
    >>> qtdeAzulejos(1, 1)
    25
    >>> qtdeAzulejos(10, 8)
    2000'''

    return (comprimento * altura * 10000) // (20 ** 2)

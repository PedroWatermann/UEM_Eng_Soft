def azulejos(comprimento: float, altura: float) -> float:
    '''Calcula, com base no *comprimento* e na *altura* de uma parede, em metros, a quantidade de azulejos necessários
        para azulejá-la.
        >>> azulejos(1, 1)
        25.0
        >>> azulejos(2.5, 1.8)
        113.0'''

    quantidade: float = (comprimento * altura * 10000) / (20 ** 2)
    if quantidade % 1 == 0:
        return quantidade
    else:
        return quantidade + (1 - quantidade % 1)

## Faça uma função que receba uma lista de números inteiros e retorne a soma desses elementos

def soma_lista(lista: list[int]) -> int:
    """
    Faz a soma dos elementos de uma lista.
    >>> soma_lista([1, 2, 3])
    6
    >>> soma_lista([1, 2, 3, 4, 5])
    15
    >>> soma_lista([0, 9, 8])
    17
    """
    soma = 0
    for n in lista:
        soma += n
    return soma
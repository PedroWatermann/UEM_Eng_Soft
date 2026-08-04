def calcula_amplitude(lista: list[int]) -> int:
    """
    Calcula a diferença entre o maior e o menor valor da lista não vazia fornecida.
    >>> calcula_amplitude([1, 2, 3, 4, 5, 6, 7, 8, 9])
    8
    >>> calcula_amplitude([9, 4, 7, 2, 0, 10000])
    10000
    """
    
    maior: int = 0
    menor: int = lista[0]
    
    for n in lista:
        if n < menor:
            menor  = n
        elif n > maior:
            maior = n
    
    return maior - menor
def calcula_triangulo(x: int, y: int, z: int) -> str:
    '''Verifica a partir dos 3 valores fornecidos se estes podem formar um triângulo
    >>> calcula_triangulo(5, 2, 5)
    'Isósceles'
    >>> calcula_triangulo(6, 6, 6)
    'Equilátero'
    >>> calcula_triangulo(3, 5, 7)
    'Escaleno'
    '''

    if (x + y) > z and (x + z) > y and (y + z) > x:
        if (x == y and x != z) or (x == z and x != y) or (y == z and y != x):
            return "Isósceles"
        elif x == y and x == z and y == z:
            return "Equilátero"
        else:
            return "Escaleno"
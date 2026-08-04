lista: list = [3, 4, 9, 2, 10, 5, 8, 1, 7, 6]
#              0, 1, 2, 3,  4, 5, 6, 7, 8, 9

# A)
"""
n = 10
i = 0
    minimo = 0
    j = 1
    j = 2
    j = 3
        minimo = 3
    j = 4
    j = 5
    j = 6
    j = 7
        minimo = 7
    j = 8
    j = 9
    aux = 3
    arranjo[0] = 1
    arranjo[7] = 3
    [1, 4, 9, 2, 10, 5, 8, 3, 7, 6]
i = 1
    minimo = 1
    j = 2
    j = 3
        minimo = 3
    j = 4
    j = 5
    j = 6
    j = 7
    j = 8
    j = 9
    aux = 4
    arranjo[1] = 2
    arranjo[3] = 4
    [1, 2, 9, 4, 10, 5, 8, 3, 7, 6]
i = 2
    minimo = 2
    j = 3
        minimo = 3
    j = 4
    j = 5
    j = 6
    j = 7
        minimo = 7
    j = 8
    j = 9
    aux = 9
    arranjo[2] = 3
    arranjo[7] = 9
    [1, 2, 3, 4, 10, 5, 8, 9, 7, 6]
i = 3
    minimo = 3
    j = 4
    j = 5
    j = 6
    j = 7
    j = 8
    j = 9
    aux = 4
    arranjo[3] = 4
    arranjo[3] = 4
    [1, 2, 3, 4, 10, 5, 8, 9, 7, 6]
i = 4
    minimo = 4
    j = 5
        minimo = 5
    j = 6
    j = 7
    j = 8
    j = 9
    aux = 10
    arranjo[4] = 5
    arranjo[5] = 10
    [1, 2, 3, 4, 5, 10, 8, 9, 7, 6]
i = 5
    minimo = 5
    j = 6
        minimo = 6
    j = 7
    j = 8
        minimo = 8
    j = 9
        minimo = 9
    aux = 10
    arranjo[5] = 6
    arranjo[9] = 10
    [1, 2, 3, 4, 5, 6, 8, 9, 7, 10]
i = 6
    minimo = 6
    j = 7
    j = 8
        minimo = 8
    j = 9
    aux = 8
    arranjo[6] = 7
    arranjo[8] = 8
    [1, 2, 3, 4, 5, 6, 7, 9, 8, 10]
i = 7
    minimo = 7
    j = 8
        minimo = 8
    j = 9
    aux = 9
    arranjo[7] = 8
    arranjo[8] = 9
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
i = 8
    minimo = 8
    j = 9
    aux = 9
    arranjo[8] = 9
    arranjo[8] = 9
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
i = 9
    minimo = 9
    aux = 10
    arranjo[9] = 10
    arranjo[9] = 10
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
"""

# B)
"""
i = 1
    pivo = 4
    j = 0
    arranjo[1] = 4
    [3, 4, 9, 2, 10, 5, 8, 1, 7, 6]
i = 2
    pivo = 9
    j = 1
    arranjo[2] = 9
    [3, 4, 9, 2, 10, 5, 8, 1, 7, 6]
i = 3
    pivo = 2
    j = 2
        arranjo[3] = 9
        j = 1
        arranjo[2] = 4
        j = 0
        arranjo[1] = 3
        j = -1
    arranjo[0] = 2
    [2, 3, 4, 9, 10, 5, 8, 1, 7, 6]
i = 4
    pivo = 10
    j = 3
    arranjo[4] = 10
    [2, 3, 4, 9, 10, 5, 8, 1, 7, 6]
i = 5
    pivo = 5
    j = 4
        arranjo[5] = 10
        j = 3
        arranjo[4] = 9
        j = 2
    arranjo[3] = 5 
    [2, 3, 4, 5, 9, 10, 8, 1, 7, 6]
i = 6
    pivo = 8
    j = 5
        arranjo[6] = 10
        j = 4
        arranjo[5] = 9
        j = 3
    arranjo[4] = 8
    [2, 3, 4, 5, 8, 9, 10, 1, 7, 6]
i = 7
    pivo = 1
    j = 6
        arranjo[7] = 8
        j = 5
        arranjo[6] = 10
        j = 4
        arranjo[5] = 9
        j = 3
        arranjo[4] = 5
        j = 2
        arranjo[3] = 4
        j = 1
        arranjo[2] = 3
        j = 0
        arranjo[1] = 2
        j = -1
    arranjo[0] = 1
    [1, 2, 3, 4, 5, 8, 9, 10, 7, 6]
i = 8
    pivo = 7
    j = 7
        arranjo[8] = 8
        j = 6
        arranjo[7] = 10
        j = 5
        arranjo[6] = 9
        j = 4
    arranjo[5] = 7
    [1, 2, 3, 4, 5, 7, 8, 9, 10, 6]
i = 9
    pivo = 6
    j = 8
        arranjo[9] = 10
        j = 7
        arranjo[8] = 9
        j = 6
        arranjo[7] = 8
        j = 5
        arranjo[6] = 7
        j = 4
    arranjo[5] = 6
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
"""

# C)
"""
n = 10
i = 0
    j = 0
    j = 1
    j = 2
        aux = 9
        arranjo[2] = 2
        arranjo[3] = 9
        [3, 4, 2, 9, 10, 5, 8, 1, 7, 6]
    j = 3
    j = 4
        aux = 10
        arranjo[4] = 5
        arranjo[5] = 10
        [3, 4, 2, 9, 5, 10, 8, 1, 7, 6]
    j = 5
        aux = 10
        arranjo[5] = 8
        arranjo[6] = 10
        [3, 4, 2, 9, 5, 8, 10, 1, 7, 6]
    j = 6
        aux = 10
        arranjo[6] = 1
        arranjo[7] = 10
        [3, 4, 2, 9, 5, 8, 1, 10, 7, 6]
    j = 7
        aux = 10
        arranjo[7] = 7
        arranjo[8] = 10
        [3, 4, 2, 9, 5, 8, 1, 7, 10, 6]
    j = 8
        aux = 10
        arranjo[8] = 6
        arranjo[9] = 10
        [3, 4, 2, 9, 5, 8, 1, 7, 6, 10]
i = 1
    j = 0
    j = 1
        aux = 4
        arranjo[1] = 2
        arranjo[2] = 4
        [3, 2, 4, 9, 5, 8, 1, 7, 6, 10]
    j = 2
    j = 3
        aux = 9
        arranjo[3] = 5
        arranjo[4] = 9
        [3, 4, 2, 5, 9, 8, 1, 7, 6, 10]
    j = 4
        aux = 9
        arranjo[4] = 8
        arranjo[5] = 9
        [3, 4, 2, 5, 8, 9, 1, 7, 6, 10]
    j = 5
        aux = 9
        arranjo[5] = 1
        arranjo[6] = 9
        [3, 4, 2, 5, 8, 1, 9, 7, 6, 10]
    j = 6
        aux = 9
        arranjo[6] = 7
        arranjo[7] = 9
        [3, 4, 2, 5, 8, 1, 7, 9, 6, 10]
    j = 7
        aux = 9
        arranjo[7] = 6
        arranjo[8] = 9
        [3, 4, 2, 5, 8, 1, 7, 6, 9, 10]
i = 2
    j = 0
    j = 1
        aux = 4
        arranjo[1] = 2
        arranjo[2] = 4
        [3, 2, 4, 5, 8, 1, 7, 6, 9, 10]
    j = 2
    j = 3
    j = 4
        aux = 8
        arranjo[4] = 1
        arranjo[5] = 8
        [3, 2, 4, 5, 1, 8, 7, 6, 9, 10]
    j = 5
        aux = 8
        arranjo[5] = 7
        arranjo[6] = 8
        [3, 2, 4, 5, 1, 7, 8, 6, 9, 10]
    j = 6
        aux = 8
        arranjo[6] = 6
        arranjo[7] = 8
        [3, 2, 4, 5, 1, 7, 6, 8, 9, 10]
i = 3
    j = 0
        aux = 3
        arranjo[0] = 2
        arranjo[1] = 3
        [2, 3, 4, 5, 1, 7, 6, 8, 9, 10]
    j = 1
    j = 2
    j = 3
        aux = 5
        arranjo[3] = 1
        arranjo[4] = 5
        [2, 3, 4, 1, 5, 7, 6, 8, 9, 10]
    j = 4
    j = 5
        aux = 7
        arranjo[5] = 6
        arranjo[6] = 7
        [2, 3, 4, 1, 5, 6, 7, 8, 9, 10]
i = 4
    j = 0
    j = 1
    j = 2
        aux = 4
        arranjo[2] = 1
        arranjo[3] = 4
        [2, 3, 1, 4, 5, 6, 7, 8, 9, 10]
    j = 3
    j = 4
i = 5
    j = 0
    j = 1
        aux = 3
        arranjo[1] = 1
        arranjo[2] = 3
        [2, 1, 3, 4, 5, 6, 7, 8, 9, 10]
    j = 2
    j = 3
i = 6
    j = 0
        aux = 2
        arranjo[0] = 1
        arranjo[1] = 2
        [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    j = 1
    j = 2
i = 7
    j = 0
    j = 1
i = 8
    j = 0
"""
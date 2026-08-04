def calcula_hora(hrInicio: int, minInicio: int, hrFim: int, minFim: int) -> int:
    '''
    Calcula quantas horas um jogo foi jogado com base no horário inicial e no horário final do período jogado. Para esta solução foi considerado que o tempo mínimo de jogo é de 1 minuto
    >>> calcula_hora(14, 30, 18, 13)
    223
    >>> calcula_hora(23, 0, 1, 0)
    120
    >>> calcula_hora(2, 14, 2, 14)
    1440
    '''

    minInicio += hrInicio * 60
    minFim += hrFim * 60

    minDiferenca: int = minFim - minInicio
    if minDiferenca > 0 and minDiferenca < 1440:
        return minDiferenca
    elif minDiferenca < 0:
        minDiferenca += 1440
        return minDiferenca
    else: # A consideração de tempo mínimo reflete a esta condição, que poderia significar 0 minutos de jogo, já que a diferença resulta em 0
        return 1440

def calcula_nivel(nivelAtual: int, horasJogadas: int) -> int:
    '''
    Calcula o novo nível de um jogador em um determinado jogo com base no seu nível atual e na quantidade de horas
    jogadas
    >>> calcula_nivel(2, 2)
    0
    >>> calcula_nivel(8, 3)
    7
    >>> calcula_nivel(10, 4)
    10
    >>> calcula_nivel(10, 5)
    10
    >>> calcula_nivel(3, 12)
    10
    >>> calcula_nivel(24, 15)
    25
    '''

    if horasJogadas < 4:
        niveisDescontados: int = 4 - horasJogadas
        novoNivel: int = nivelAtual - niveisDescontados

        if novoNivel < 0:
            return 0
        else:
            return novoNivel
    elif horasJogadas < 6:
        return nivelAtual
    else:
        niveisSomados: int = horasJogadas - 5

        if niveisSomados <= 7:
            novoNivel: int = nivelAtual + niveisSomados
        else:
            novoNivel: int = nivelAtual + 7

        if novoNivel <= 25:
            return novoNivel
        else:
            return 25
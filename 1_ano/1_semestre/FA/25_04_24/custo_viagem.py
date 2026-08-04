def custo_viagem(distancia: float, consumo: float, preco: float) -> float:
    '''Calcula o custo total de uma viagem com base na *distancia* percorrida, no *consumo* do carro e no *preco* do
    combustivel
    >>> custo_viagem(120, 10, 5)
    60.0
    >>> custo_viagem(300, 15, 6)
    120.0'''

    return (distancia / consumo) * preco
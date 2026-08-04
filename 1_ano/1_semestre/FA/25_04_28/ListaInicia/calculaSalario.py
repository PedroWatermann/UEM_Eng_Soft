def calculaSalario(horasTrabalhadas: int) -> float:
    '''Calcula o valor do salário de um engenheiro com base na quantidade de *horas trabalhadas* semanalmente. Por
    convenção, um mês possui 4 semanas.
    Exemplo:
    >>> calculaSalario(20)
    2720.0
    >>> calculaSalario(50)
    6800.0'''

    return horasTrabalhadas * 34.00 * 4

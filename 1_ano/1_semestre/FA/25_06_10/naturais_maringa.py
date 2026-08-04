from dataclasses import dataclass

@dataclass
class Pessoa:
    nome: str
    cpf: str
    naturalidade: str

def naturais_maringa(pessoas: list[Pessoa]) -> list[str]:
    """
    Retorna uma lista com o nome das *pessoas* que são naturais de Maringá.
    >>> pessoa1: Pessoa = Pessoa('Pedro', '1', 'Paiçandu')
    >>> pessoa2: Pessoa = Pessoa('Augusto', '1', 'Maringá')
    >>> pessoa3: Pessoa = Pessoa('Dos', '1', 'Rolândia')
    >>> pessoa4: Pessoa = Pessoa('Santos', '1', 'Carandiru')
    >>> pessoa5: Pessoa = Pessoa('Watermann', '1', 'Maringá')
    >>> pessoa6: Pessoa = Pessoa('José', '1', 'Maringá')
    >>> naturais_maringa([pessoa1, pessoa2])
    ['Augusto']
    >>> naturais_maringa([pessoa3, pessoa4])
    []
    >>> naturais_maringa([pessoa5, pessoa6])
    ['Watermann', 'José']
    """
    
    maringaenses: list[str] = []
    for pessoa in pessoas:
        if pessoa.naturalidade == 'Maringá':
            maringaenses.append(pessoa.nome)
    
    return maringaenses
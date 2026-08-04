from enum import Enum

# Preços base
class Preco(Enum):
    MaoObra = 10.0
    Massa = 5.0

# Preço ingrediente, por unidade
class Ingredientes(Enum):
    Queijo = 2.0
    Bacon = 3.0
    Cebola = 1.0
    Azeitona = 1.0

# Acréscimo do valor em função do tamanho, em porcentagem (aumenta o preço base)
class Tamanho(Enum):
    Pequeno = 0.1
    Medio = 0.2
    Grande = 0.3

def preco_pizza(ing1: Ingredientes, ing2: Ingredientes, ing3: Ingredientes, tamanho: Tamanho) -> (
        float):
    '''
    Calcula o valor final de uma pizza a partir do preço base desta pizza, do seu tamanho e de quais ingredientes estão presentes
    >>> preco_pizza(Ingredientes.Queijo, Ingredientes.Cebola, Ingredientes.Azeitona, Tamanho.Pequeno)
    20.5
    >>> preco_pizza(Ingredientes.Bacon, Ingredientes.Queijo, Ingredientes.Azeitona, Tamanho.Grande)
    25.5
    '''

    preco_base = Preco.MaoObra.value + Preco.Massa.value
    preco_base += preco_base * tamanho.value

    valor_ingredientes = ing1.value + ing2.value + ing3.value

    valor_final = preco_base + valor_ingredientes

    return valor_final
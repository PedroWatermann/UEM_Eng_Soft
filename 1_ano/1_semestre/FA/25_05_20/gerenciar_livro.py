from enum import Enum, auto
from dataclasses import dataclass

class Status(Enum):
    Disponivel = auto()
    Emprestado = auto()
    Manutencao = auto()

class Genero(Enum):
    Terror = auto()
    Romance = auto()
    Fantasia = auto()
    Comedia = auto()
    Ciencia = auto()

@dataclass
class Livro:
    Titulo: str
    Autor: str
    Isbn: str
    AnoPublicacao: int
    Genero: Genero
    Status: Status

def verificar_genero_livro(livro: Livro, genero_desejado: Genero) -> bool:
    return True if livro.Genero.name == genero_desejado.name else False

def o_livro_esta_disponivel(livro: Livro) -> bool:
    return True if livro.Status == Status.Disponivel else False

def emprestar(livro: Livro) -> bool:
    if o_livro_esta_disponivel(livro):
        livro.Status = Status.Emprestado
        return True
    return False

livro1 = Livro(
    "Meu Barquinho", 
    "Eu Mesmo", 
    "1234", 
    2040, 
    Genero.Ciencia, 
    Status.Disponivel
)
livro2 = Livro(
    "Meu Carrinho", 
    "Não Eu", 
    "1235", 
    2041, 
    Genero.Comedia, 
    Status.Emprestado
)
livro3 = Livro(
    "Meu Aviaozinho", 
    "Só Eu", 
    "1236", 
    2042, 
    Genero.Fantasia, 
    Status.Manutencao
)

## Livro 1
if verificar_genero_livro(livro1, Genero.Romance):
    print(f"\nO livro {livro1.Titulo} é do gênero {Genero.Romance.name}.")
else:
    print(f"\nO livro {livro1.Titulo} não é do gênero {Genero.Romance.name}.")

if emprestar(livro1):
    print(f"O livro {livro1.Titulo} está disponível para ser emprestado.")
else:
    print(f"O livro {livro1.Titulo} já foi emprestado ou está em manutenção.")

## Livro 2
if verificar_genero_livro(livro2, Genero.Ciencia):
    print(f"\nO livro {livro2.Titulo} é do gênero {Genero.Comedia.name}.")
else:
    print(f"\nO livro {livro2.Titulo} não é do gênero {Genero.Comedia.name}.")

if emprestar(livro2):
    print(f"O livro {livro2.Titulo} está disponível para ser emprestado.")
else:
    print(f"O livro {livro2.Titulo} já foi emprestado ou está em manutenção.")

## Livro3
if verificar_genero_livro(livro3, Genero.Fantasia):
    print(f"\nO livro {livro3.Titulo} é do gênero {Genero.Fantasia.name}.")
else:
    print(f"\nO livro {livro3.Titulo} não é do gênero {Genero.Fantasia.name}.")
    
if emprestar(livro3):
    print(f"O livro {livro3.Titulo} está disponível para ser emprestado.")
else:
    print(f"O livro {livro3.Titulo} já foi emprestado ou está em manutenção.")
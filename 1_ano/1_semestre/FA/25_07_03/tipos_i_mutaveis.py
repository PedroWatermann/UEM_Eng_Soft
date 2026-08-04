# Tipos imutáveis:
    # Numéricos, strings, entre outros
    # x = 2
    # x = 5
    # Neste caso, o valor de x não muda mas sim a referência ao valor de x
    # Passagem por valor
        # Cópia do valor
# Tipos mutáveis:
    # No caso da lista, ao passar, como parâmetro, cria-se uma referência para a lista, alterando o valor original
    # Passagem por referência
        # Altera o original

def inverte_lista(lista: list):
    i: int = 0
    j: int = len(lista) - 1
    while i < j:
        aux = lista[i]
        lista[i] = lista[j]
        lista[j] = aux
        i += 1
        j -= 1

def inverte_lista2(lista: list) -> list:
    lista2 = []
    for i in range(len(lista) - 1, -1, -1):
        lista2.append(lista[i])
    return lista2
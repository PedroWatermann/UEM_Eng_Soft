# Ordenação por seleção:
    # Selecionar o menor elemento da lista e troca com o elemento da primeira posição. Repetir com os n-1 itens restantes, n-2...
def selection_sort(arranjo: list):
    n: int = len(arranjo)
    for i in range(n):
        minimo: int = i
        for j in range(i + 1, n):
            if arranjo[minimo] > arranjo[j]: 
                minimo = j
        aux: int = arranjo[i]
        arranjo[i] = arranjo[minimo]
        arranjo[minimo] = aux

lista: list = [7, 6]
selection_sort(lista)
print(lista)
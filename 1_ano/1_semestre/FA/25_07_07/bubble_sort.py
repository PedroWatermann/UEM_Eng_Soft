# Empurra o maior elemento para o fim da lista

def bubble_sort(arranjo: list):
    n = len(arranjo)
    for i in range(n):
        for j in range(0, n - i - 1):
            if arranjo[j] > arranjo[j + 1]:
                aux: int = arranjo[j]
                arranjo[j] = arranjo[j + 1]
                arranjo[j + 1] = aux

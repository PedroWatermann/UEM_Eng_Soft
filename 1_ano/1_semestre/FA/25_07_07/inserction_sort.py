# Move os itens maiores para a direta e coloca o menor item no lugar correto

def inserction_sort(arranjo: list):
    for i in range(1, len(arranjo)):
        pivo: int = arranjo[i]
        j = i - 1
        while j >= 0 and pivo < arranjo[j]:
            arranjo[j + 1] = arranjo[j]
            j -= 1
        arranjo[j + 1] = pivo

import time
import random

def gerarMatriz(tam):
    matriz = [[0] * tam for _ in range(tam)]
    
    for i in range(tam):
        for j in range(tam):
            matriz[i][j] = random.randint(1, 10)
    
    return matriz

def multiplicaMatrizes(matrizA, matrizB):
    matrizResultante = [[0] * len(matrizB[0]) for _ in range(len(matrizA))]
    
    for i in range(len(matrizA)):
        for j in range(len(matrizB[0])):
            for k in range(len(matrizA[0])):
                matrizResultante[i][j] += matrizA[i][k] * matrizB[k][j]

    return matrizResultante

def main():
    matrizA = gerarMatriz(1072)
    matrizB = gerarMatriz(1072)
    
    print(f'Hora início: {time.strftime("%H:%M:%S", time.localtime())}')
    inicio = time.process_time()
    
    multiplicaMatrizes(matrizA, matrizB)
    
    fim = time.process_time()
    print(f'Hora fim: {time.strftime("%H:%M:%S", time.localtime())}')
    
    print(f'Diferenca: {fim - inicio}')

main()

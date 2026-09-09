import random
import threading
import time

TAM_MATRIZ = 1072

matrizA = []
matrizB = []
matrizResultante = []

def gerarMatriz(tam, val, matriz):
    for i in range(tam):
        matriz.append([0] * tam)
        
    if val != 0:
        for i in range(tam):
            for j in range(tam):
                matriz[i][j] = random.randint(1, 10)

def multiplicaMatrizes(inicio, fim):
    for i in range(inicio, fim):
        for j in range(TAM_MATRIZ):
            for k in range(TAM_MATRIZ):
                matrizResultante[i][j] += matrizA[i][k] * matrizB[k][j]

def main(quantidadeThreads):
    threads = []
    gerarMatriz(TAM_MATRIZ, -1, matrizA)
    gerarMatriz(TAM_MATRIZ, -1, matrizB)
    gerarMatriz(TAM_MATRIZ, 0, matrizResultante)
    
    for i in range(quantidadeThreads):
        inicio = (TAM_MATRIZ // quantidadeThreads)
        fim = inicio * (i + 1)
        
        inicio *= i

        threads.append(threading.Thread(target=multiplicaMatrizes, args=(inicio, fim)))
        
    for i in threads:
        print(f'\tThread - Início: {time.strftime("%H:%M:%S", time.localtime())}')
        i.start()
        
    for i in threads:
        i.join()

print(f'Hora início: {time.strftime("%H:%M:%S", time.localtime())}')
inicio = time.process_time()

main(2)

fim = time.process_time()
print(f'Hora fim: {time.strftime("%H:%M:%S", time.localtime())}')

print(f'Diferenca: {fim - inicio}')

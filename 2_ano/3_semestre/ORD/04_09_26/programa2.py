# Busca Sobrenome

encontrado = False
SOBRENOME = input('Digite o sobrenome a ser buscado: ')
print()

with open('pessoasGOT.dat', 'rb') as arq:
    tamB = arq.read(2)
    countReg = 1
    while tamB:
        tam = int.from_bytes(tamB, 'little')
        dados = arq.read(tam).decode().split('|')
        
        if dados[1] == SOBRENOME:
            print(f'\nRegistro #{countReg}:')
            encontrado = True
            count = 1
            for campo in dados:
                if campo:
                    print(f'\tCampo #{count}: {campo}')
                    count += 1
            countReg += 1
        tamB = arq.read(2)

    if not encontrado:
        print(f'O(s) registro(s) de sobrenome {SOBRENOME} não foi(ram) encontrado(s).\n')

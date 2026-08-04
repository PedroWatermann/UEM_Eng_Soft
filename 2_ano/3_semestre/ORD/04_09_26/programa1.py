# Busca Id

encontrado = False
ID = input('Digite o ID a ser buscado: ')

with open('pessoasGOT.dat', 'rb') as arq:
    tamB = arq.read(2)
    while tamB:
        tam = int.from_bytes(tamB, 'little')
        dados = arq.read(tam).decode().split('|')
        
        if dados[0] == ID:
            encontrado = True
            count = 1
            for campo in dados:
                if campo:
                    print(f'\tCampo #{count}: {campo}')
                    count += 1
            break
        tamB = arq.read(2)

    if not encontrado:
        print(f'O registro de ID {ID} não foi encontrado.')

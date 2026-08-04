NOME_ARQ = input('Digite o nome do arquivo: ')

entrada = open(NOME_ARQ, 'rb')

temDados = True
contReg = 1
contCam = 1

while temDados:
    tam = int.from_bytes(entrada.read(2), 'little')
    
    if tam > 0:
        print(f'\nRegistro #{contReg} (Tam = {tam}): ')
        
        dados = entrada.read(tam).decode().split('|')[1:-1]
        
        for c in dados:
            print(f'\tCampo #{contCam}: {c}')
            contCam += 1
        
        print()
        contReg += 1
        contCam = 1
    else:
        temDados = False

entrada.close()
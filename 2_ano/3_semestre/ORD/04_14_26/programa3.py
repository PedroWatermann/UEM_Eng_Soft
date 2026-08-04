rrn = int(input('Digite o RRN a ser buscado: '))

print()

with open('pessoasGOTfixo.dat', 'rb') as arq:
    numReg = int.from_bytes(arq.read(4), 'little')
    if numReg > rrn:
        rrn = rrn * 64 + 4
        arq.seek(rrn)
        registro = arq.read(64).decode().split('|')[:-1]
        
        contador = 1
        for campo in registro:
            print(f'\tCAMPO #{contador}: {campo}')
            contador += 1
    else:
        print('O arquivo possui uma quantidade menor de registros.')
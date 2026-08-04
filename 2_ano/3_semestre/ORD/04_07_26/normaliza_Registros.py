NOME_ARQ = input('Digite o nome do arquivo: ')

entrada = open(NOME_ARQ, 'rb')

temDados = True
contReg = 1
contCam = 1
nomeCampos = {
    1: 'Sobrenome',
    2: 'Nome',
    3: 'Endereço',
    4: 'Cidade',
    5: 'Estado',
    6: 'CEP'
}
baseDeDados = []

while temDados:
    tam = int.from_bytes(entrada.read(2), 'little')
    
    if tam > 0:        
        dados = entrada.read(tam).decode().split('|')[1:-1]
        
        for c in dados:
            if not c:
                print(f'\n\tErro: \tO registro #{contReg} não possui o campo #{nomeCampos[contCam]}.')
            if contCam == 5 and len(c) != 2:
                print(f'\n\tErro: \tO campo #{nomeCampos[contCam]} do registro #{contReg} possui mais de 2 caracteres.')
            if contCam == 6 and not c.isdigit():
                print(f'\n\tErro: \tO campo #{nomeCampos[contCam]} do registro #{contReg} deve conter apenas caracteres.')
            contCam += 1
        
        contReg += 1
        contCam = 1
        baseDeDados.append(dados)
    else:
        temDados = False

if len(baseDeDados) > 0 and input('\nDeseja imprirmir os dados? (s/n)\n') == 's':
    contReg = 1
    for dado in baseDeDados:
        contCam = 1
        print(f'\nRegistro #{contReg}: ')
        for campo in dado:
            print(f'\tCampo #{nomeCampos[contCam]}: {campo}')
            contCam += 1
        contReg += 1

entrada.close()
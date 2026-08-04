NOME_ARQ = input('Digite o nome do arquivo: ')

saida = open(NOME_ARQ, 'wb')
    
SOBRENOME = input('\nDigite seu sobrenome: ')

while SOBRENOME:
    NOME = input('Digite seu nome: ')
    ENDERECO = input('Digite seu endereco: ')
    CIDADE = input('Digite sua cidade: ')
    ESTADO = input('Digite seu estado: ')
    CEP = input('Digite seu CEP: ')
    
    dados_b = (f'|{SOBRENOME}|{NOME}|{ENDERECO}|{CIDADE}|{ESTADO}|{CEP}|').encode()
    tamanho_b = len(dados_b).to_bytes(2, 'little')
    
    saida.write(tamanho_b + dados_b)
    
    print('___________________________________________________________')
    SOBRENOME = input('Digite seu sobrenome: ')

saida.close()
print('\nDADOS INSERIDOS COM SUCESSO!!!\n')

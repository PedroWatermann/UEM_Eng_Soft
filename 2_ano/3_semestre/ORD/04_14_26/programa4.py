import os

nomeArq = input('Digite o nome do arqiuvo: ')
qtdReg = 0
print()

def escrever(arq):
    global qtdReg
    
    print('Digite os dados do novo registro: ')
    id = input('ID: ')
    sobrenome = input('Sobrenome: ')
    nome = input('Nome: ')
    castelo = input('Castelo: ')
    cidade = input('Cidade: ')
    regiao = input('Região: ')
    
    dados = f'{id}|{nome}|{sobrenome}|{castelo}|{cidade}|{regiao}|'[:63]
    arq.write(dados.encode())
    qtdReg += 1
    arq.seek(0, os.SEEK_SET)
    arq.write(qtdReg.to_bytes(4, 'little'))

def buscaEAtualiza(arq):
    opcao = int(input('Escolha uma opção: \n\t1. Inserir um novo registro \n\t2. Buscar um registro por RRN para alterações \n\t3. Terminar o programa \nOpção: '))
    while opcao != 3:
        if opcao == 1:
            arq.seek(0, os.SEEK_END)
            escrever(arq)
        elif opcao == 2:
            rrn = input('Digite o RRN do registro: ')
            arq.seek(rrn * 64)
            dados = arq.read(64).split('|')[:-1]
            print('Conteúdo do registro: ')
            for campo in dados:
                print(campo)
            mudar = input('Você deseja modificar este registro? (s/N)')
            if mudar == 's':
                escrever(arq)
            
        opcao = int(input('Escolha uma opção: \n\t1. Inserir um novo registro \n\t2. Buscar um registro por RRN para alterações \n\t3. Terminar o programa \nOpção: '))

try:
    arq = open(nomeArq, 'rb+')
    qtdReg = int.from_bytes(arq.read(4), 'little')
    buscaEAtualiza(arq)
except:
    arq = open(nomeArq, 'wb+')
    qtdReg = 0
    arq.write(qtdReg.to_bytes(4, 'little'))
    buscaEAtualiza(arq)

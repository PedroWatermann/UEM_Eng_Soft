NOME_ARQ = input('Digite o nome do arquivo: ')

listaDados = []

with open(NOME_ARQ, 'r') as arq:
    for linha in arq:
        linha_bytes = f'|{linha}'.encode()
        tamLinha = len(linha_bytes).to_bytes(2, 'little')
        dados = tamLinha + linha_bytes
        listaDados.append(dados)

if listaDados:
    with open(NOME_ARQ.replace('.txt', '_convertido.bin'), 'wb') as arq:
        arq.writelines(listaDados)
        print('\nArquivo convertido com sucesso!\n')

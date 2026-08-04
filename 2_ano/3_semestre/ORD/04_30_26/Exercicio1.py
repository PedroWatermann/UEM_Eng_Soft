from sys import argv

def main(arquivoEntrada, arquivoSaida):
    with open(arquivoEntrada, 'rb') as arqEntrada:
        cabecalho = int.from_bytes(arqEntrada.read(4), 'little')
    
        if not cabecalho or cabecalho <= 0:
            raise TypeError(f'Não há registros no arquivo de entrada \'{arquivoEntrada}\'')
        
        listReg = []
        
        tamReg = int.from_bytes(arqEntrada.read(2), 'little')
    
        while tamReg:
            registro = arqEntrada.read(tamReg)
            id = int(registro.decode().split('|')[0])
            
            tupla = (id, tamReg.to_bytes(2, 'little') + registro)
            listReg.append(tupla)
            
            tamReg = int.from_bytes(arqEntrada.read(2), 'little')
    
        listReg.sort()
    
    escreverRegistros(listReg, arquivoSaida, cabecalho)
    
def escreverRegistros(dados, arquivoSaida, cabecalho):
    with open(arquivoSaida, 'wb') as arqSaida:
        listaFinal = []
        
        for i in range(len(dados)):
            if i == 0:
                listaFinal.append(cabecalho.to_bytes(4, 'little') + dados[i][1])
            else:
                listaFinal.append(dados[i][1])
        
        arqSaida.writelines(listaFinal)

if __name__ == '__main__':
    if len(argv) < 3:
        raise TypeError('Número incorreto de argumentos')
    
    main(argv[1], argv[2])
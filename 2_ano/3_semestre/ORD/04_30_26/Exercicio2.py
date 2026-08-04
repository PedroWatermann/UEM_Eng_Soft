from sys import argv
from struct import pack, unpack, calcsize

def keysortplusplus(arqEntrada, arqSaida):
    listaFinal = []
    
    with open(arqEntrada, 'rb') as arqEnt:
        cabacalho = arqEnt.read(4)
        tam = unpack('h', arqEnt.read(2))[0]
        while tam:
            byteOffset = arqEnt.tell()
            id = int(unpack(f'{tam}s', arqEnt.read(tam))[0].decode().split('|')[0])
            listaFinal.append((id, byteOffset))
            tam = arqEnt.read(2)
            if tam: tam = unpack('h', tam)[0]
        
        listaFinal.sort()
    
        with open(arqSaida, 'wb') as arqSai:
            arqSai.write(cabacalho)
            
            for elem in listaFinal:
                arqEnt.seek(elem[1] - 2)
                
                tamReg = unpack('h', arqEnt.read(2))[0]
                reg = arqEnt.read(tamReg)
                
                tamReg = pack('h', tamReg)
                reg = tamReg + reg

                arqSai.write(reg)
    
    with open('primario.ind', 'wb') as arq:
        cabacalho = pack('i', len(listaFinal))
        arq.write(cabacalho)
        
        for elem in listaFinal:
            elemByte = pack('2i', *elem)
            arq.write(elemByte)

def main() -> None:
    if len(argv) < 3:
        raise TypeError('Número incorreto de argumentos\nModo de uso: nome_arq_entrada nome_arq_saida')
    keysortplusplus(argv[1], argv[2])

if __name__ == '__main__':
    main() 
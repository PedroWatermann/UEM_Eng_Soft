def main():
    contador = 0
    
    NOME_ARQ = input("Digite o nome do arquivo: ")
    entrada = open(NOME_ARQ, "r")
    
    CAMPO = leiaCampo(entrada)
    
    while CAMPO:
        contador += 1
        print(f"\tcampo #{contador}: {CAMPO}")
        CAMPO = leiaCampo(entrada)
    
    entrada.close()
        
    
def leiaCampo(entrada):
    CAMPO = ""
    
    C = entrada.read(1)
    while C and C != "|" and C:
        CAMPO += C
        C = entrada.read(1)
    
    return CAMPO

main()
def main():
    escreveCampos()

def escreveCampos():
    NOME_ARQ = input("Digite o nome do arquivo: ")
    saida = open(NOME_ARQ, "w", encoding="utf-8")
    
    SOBRENOME = input("\nDigite seu sobrenome: ")
    
    while SOBRENOME:
        NOME = input("Digite seu nome: ")
        ENDERECO = input("Digite seu endereco: ")
        CIDADE = input("Digite sua cidade: ")
        ESTADO = input("Digite seu estado: ")
        CEP = input("Digite seu CEP: ")
        
        dados = f"{SOBRENOME}|{NOME}|{ENDERECO}|{CIDADE}|{ESTADO}|{CEP}|"
        
        saida.write(dados)
        
        print("___________________________________________________________")
        SOBRENOME = input("Digite seu sobrenome: ")
    
    saida.close()
    print("\nDADOS INSERIDOS COM SUCESSO!!!\n")
    
if __name__ == "__main__":
    main()

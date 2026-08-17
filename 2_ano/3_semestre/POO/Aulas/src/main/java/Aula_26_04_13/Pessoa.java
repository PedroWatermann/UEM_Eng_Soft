package Aula_26_04_13;

public class Pessoa {
    private String nome;
    private int idade;
    private String email;
    private String telefone;

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getIdade() {
        return this.idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return this.telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Pessoa() { }

    public Pessoa(String nome, int idade, String email, String telefone) {
        setNome(nome);
        setIdade(idade);
        setEmail(email);
        setTelefone(telefone);
    }

    public String apresentar() {
        return "Olá, sou " + getNome() + ", tenho " + getIdade() + " anos. Para mais informações entrar em contato através do email " + getEmail() + " ou do telefone " + getTelefone() + ".";
    }
}

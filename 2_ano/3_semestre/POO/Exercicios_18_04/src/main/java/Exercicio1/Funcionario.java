package Exercicio1;

public abstract class Funcionario {
    protected String nome;
    protected String cpf;
    protected double salarioBase;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("O nome não pode ser vazio ou nulo");
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        if (salarioBase <= 0) throw new IllegalArgumentException("O valor do salário base deve ser maior que 0");
        this.salarioBase = salarioBase;
    }

    public Funcionario(String nome, String cpf, double salarioBase) {
        setNome(nome);
        setCpf(cpf);
        setSalarioBase(salarioBase);
    }

    public abstract double calcularSalarioFinal();

    public String gerarContraCheque() {
        return String.format(
            "- Nome: %s%n\t\t- CPF: %s%n\t\t- Salário final: R$%.2f",
            nome,
            cpf,
            calcularSalarioFinal()
        );
    }
}

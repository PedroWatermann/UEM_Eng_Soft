package Exercicio1;

public class Gerente extends Funcionario implements ITributavel {
    private String departamento;
    private double bonificacao;

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getBonificacao() {
        return bonificacao;
    }

    public void setBonificacao(double bonificacao) {
        this.bonificacao = bonificacao;
    }

    public Gerente(String nome, String cpf, double salarioBase, String departamento, double bonificacao) {
        super(nome, cpf, salarioBase);
        setDepartamento(departamento);
        setBonificacao(bonificacao);
    }

    @Override
    public double calcularSalarioFinal() {
        return this.salarioBase + this.bonificacao;
    }

    @Override
    public String gerarContraCheque() {
        return String.format(
            "%s%n\t\t- Departamento: %s",
            super.gerarContraCheque(),
            this.departamento
        );
    }

    @Override
    public double calcularImpostoRenda() {
        return this.calcularSalarioFinal() * 27.5 / 100;
    }

    @Override
    public String getFaixaIR() {
        return "27,5%, Faixa 4";
    }
}

package Exercicio1;

public class Desenvolvedor extends Funcionario implements ITributavel {
    private String linguagemPrincipal;
    private int horasExtras;

    public String getLinguagemPrincipal() {
        return linguagemPrincipal;
    }

    public void setLinguagemPrincipal(String linguagemPrincipal) {
        this.linguagemPrincipal = linguagemPrincipal;
    }

    public int getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

    public Desenvolvedor(String nome, String cpf, double salarioBase, String linguagemPrincipal, int horasExtras) {
        super(nome, cpf, salarioBase);
        setLinguagemPrincipal(linguagemPrincipal);
        setHorasExtras(horasExtras);
    }

    @Override
    public double calcularSalarioFinal() {
        return this.salarioBase + this.horasExtras * 80;
    }

    @Override
    public String gerarContraCheque() {
        return String.format(
            "%s%n\t\t- Linguagem principal: %s",
            super.gerarContraCheque(),
            this.linguagemPrincipal
        );
    }

    @Override
    public double calcularImpostoRenda() {
        return this.calcularSalarioFinal() * 15 / 100;
    }

    @Override
    public String getFaixaIR() {
        return "15%, Faixa 2";
    }
}

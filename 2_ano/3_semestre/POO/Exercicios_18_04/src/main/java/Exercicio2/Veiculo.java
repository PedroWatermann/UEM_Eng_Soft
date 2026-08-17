package Exercicio2;

public abstract class Veiculo {
    protected String placa;
    protected String modelo;
    protected int ano;
    protected double valorVenal;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getValorVenal() {
        return valorVenal;
    }

    public void setValorVenal(double valorVenal) {
        this.valorVenal = valorVenal;
    }

    public Veiculo(String placa, String modelo, int ano, double valorVenal) {
        setPlaca(placa);
        setModelo(modelo);
        setAno(ano);
        setValorVenal(valorVenal);
    }

    abstract double calcularIPVA();

    public String exibirDados() {
        return String.format(
                "- Placa: %s%n\t\t- Modelo: %s%n\t\t- Ano: %d%n\t\t- Valor venal: R$%.2f%n\t\t- IPVA: R$%.2f",
                this.placa,
                this.modelo,
                this.ano,
                this.valorVenal,
                calcularIPVA()
        );
    }
}

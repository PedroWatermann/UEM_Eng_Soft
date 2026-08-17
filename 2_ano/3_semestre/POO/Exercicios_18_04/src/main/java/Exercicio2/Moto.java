package Exercicio2;

public class Moto extends Veiculo implements Financiavel {
    private int cilindradas;

    public int getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(int cilindradas) {
        this.cilindradas = cilindradas;
    }

    public Moto(String placa, String modelo, int ano, double valorVenal, int cilindradas) {
        super(placa, modelo, ano, valorVenal);
        setCilindradas(cilindradas);
    }

    @Override
    double calcularIPVA() {
        return this.valorVenal / 100 * (cilindradas <= 150 ? 1 : 2);
    }

    @Override
    public double calcularParcela(int numParcelas, double taxaMensal) {
        return this.valorVenal * (1 + taxaMensal * numParcelas) / numParcelas;
    }

    @Override
    public double calcularCustoTotal(int numParcelas, double taxaMensal) {
        return this.calcularParcela(numParcelas, taxaMensal) * numParcelas;
    }
}

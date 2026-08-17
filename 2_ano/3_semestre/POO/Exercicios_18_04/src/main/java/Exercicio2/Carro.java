package Exercicio2;

public class Carro extends Veiculo implements Financiavel {
    private int numPortas;
    private String tipoCombustivel;

    public int getNumPortas() {
        return numPortas;
    }

    public void setNumPortas(int numPortas) {
        this.numPortas = numPortas;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public Carro(String placa, String modelo, int ano, double valorVenal, int numPortas, String tipoCombustivel) {
        super(placa, modelo, ano, valorVenal);
        setNumPortas(numPortas);
        setTipoCombustivel(tipoCombustivel);
    }

    @Override
    double calcularIPVA() {
        return this.valorVenal / 100 * (this.tipoCombustivel.equals("Elétrico") ? 2 : 4);
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

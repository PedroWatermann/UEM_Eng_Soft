package Exercicio2;

public class Caminhao extends Veiculo implements VeiculoCarga {
    private int numEixos;
    private double capacidadeToneladas;

    public int getNumEixos() {
        return numEixos;
    }

    public void setNumEixos(int numEixos) {
        this.numEixos = numEixos;
    }

    public double getCapacidadeToneladas() {
        return capacidadeToneladas;
    }

    public void setCapacidadeToneladas(double capacidadeToneladas) {
        this.capacidadeToneladas = capacidadeToneladas;
    }

    public Caminhao(String placa, String modelo, int ano, double valorVenal, int numEixos, double capacidadeToneladas) {
        super(placa, modelo, ano, valorVenal);
        setNumEixos(numEixos);
        setCapacidadeToneladas(capacidadeToneladas);
    }

    @Override
    double calcularIPVA() {
        return this.valorVenal / 100 * 1.5;
    }

    @Override
    public double getCapacidadeCarga() {
        return this.getCapacidadeToneladas();
    }

    @Override
    public boolean suportaCarga(double peso) {
        return peso <= this.getCapacidadeCarga();
    }
}

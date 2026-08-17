public class Pedido {

    private String numero;
    private String cliente;
    private double pesoKg;
    private double distanciaKm;
    private double valorProdutos;
    private boolean fragil;

    public String getNumero() {
        return this.numero;
    }

    public String getCliente() {
        return this.cliente;
    }

    public double getPesoKg() {
        return this.pesoKg;
    }

    public double getDistanciaKm() {
        return this.distanciaKm;
    }

    public double getValorProdutos() {
        return this.valorProdutos;
    }

    public boolean getFragil() {
        return this.fragil;
    }

    public Pedido(String numero, String cliente, double pesoKg, double distanciaKm, double valorProdutos, boolean fragil) {
        this.numero = numero;
        this.cliente = cliente;
        this.pesoKg = pesoKg > 0 ? pesoKg : 0;
        this.distanciaKm = distanciaKm  > 0 ? distanciaKm : 0;
        this.valorProdutos = valorProdutos > 0 ? valorProdutos : 0;
        this.fragil = fragil;
    }

    public String resumo() {
        return "Pedido: " + this.getNumero() +
               " \nCliente: " + this.getCliente() +
               " \nPeso (Kg): " + getPesoKg() +
               " \nDistância (Km): " + this.getDistanciaKm() +
               " \nValor dos produtos: " + getValorProdutos() +
               " \nÉ frágil: " + (getFragil() ? "Sim" : "Não");
    }
}

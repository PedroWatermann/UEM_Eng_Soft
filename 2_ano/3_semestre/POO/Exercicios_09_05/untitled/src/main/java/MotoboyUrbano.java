public class MotoboyUrbano implements ModalidadeEntrega, Rastreavel {
    private String empresaParceira;
    private double taxaBase;

    public MotoboyUrbano(String empresaParceira, double taxaBase) {
        this.empresaParceira = empresaParceira;
        this.taxaBase = taxaBase;
    }

    @Override
    public String getNome() {
        return "Motoboy Urbano";
    }

    @Override
    public boolean disponivelPara(Pedido pedido) {
        return pedido.getDistanciaKm() <= 12 && pedido.getPesoKg() <= 10;
    }

    @Override
    public double calcularFrete(Pedido pedido) {
        double valorBase = this.taxaBase + 1.5 * pedido.getDistanciaKm();
        return pedido.getFragil() ? valorBase + 4 : valorBase;
    }

    @Override
    public int estimarPrazoDias(Pedido pedido) {
        return pedido.getDistanciaKm() <= 5 ? 0 : 1;
    }

    @Override
    public String gerarResumo(Pedido pedido) {
        return "Modalidade de entrega: " + this.getNome() +
                " | Valor do frete: R$ " + this.calcularFrete(pedido) +
                " | Prazo estimado: " + this.estimarPrazoDias(pedido) + " dia(s)";
    }

    @Override
    public String despachar(Pedido pedido) {
        return this.disponivelPara(pedido)
                ? "O pedido " + pedido.getNumero() + " " + this.consultarStatus() + "."
                : "Para a modalidade " + this.getNome() + ", a distância deve ser menor ou igual a 12Km e o peso deve ser menor ou igual a 10Kg.";
    }

    @Override
    public String gerarCodigoRastreio(Pedido pedido) {
        return "MOTO-<" + pedido.getNumero() + ">";
    }

    @Override
    public String consultarStatus() {
        return "saiu para entrega";
    }
}
public class TransportadoraNacional implements ModalidadeEntrega, Rastreavel {
    private String nomeTransportadora;
    private double percentualSeguro;

    public TransportadoraNacional(String nomeTransportadora, double percentualSeguro) {
        this.nomeTransportadora = nomeTransportadora;
        this.percentualSeguro = percentualSeguro;
    }

    @Override
    public String getNome() {
        return "Transportadora Nacional";
    }

    @Override
    public boolean disponivelPara(Pedido pedido) {
        return true;
    }

    @Override
    public double calcularFrete(Pedido pedido) {
        double valorBase = 18 + 0.6 * pedido.getDistanciaKm() + 3 * pedido.getPesoKg();
        return pedido.getFragil() ? valorBase + pedido.getValorProdutos() * this.percentualSeguro : valorBase;
    }

    @Override
    public int estimarPrazoDias(Pedido pedido) {
        return 2 + (int)(Math.ceil(pedido.getDistanciaKm() / 200));
    }

    @Override
    public String gerarResumo(Pedido pedido) {
        return "Modalidade de entrega: " + this.getNome() +
                " | Valor do frete: R$ " + this.calcularFrete(pedido) +
                " | Prazo estimado: " + this.estimarPrazoDias(pedido) + " dia(s)";
    }

    @Override
    public String despachar(Pedido pedido) {
        return "O pedido " + pedido.getNumero() + " foi " + this.consultarStatus() + ".";
    }

    @Override
    public String gerarCodigoRastreio(Pedido pedido) {
        return "TRANS-<" + pedido.getNumero() + ">";
    }

    @Override
    public String consultarStatus() {
        return "recebido no centro de distribuição";
    }
}
public class RetiradaNaLoja implements ModalidadeEntrega {
    private String nomeLoja;

    public RetiradaNaLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    @Override
    public String getNome() {
        return "Retirada na Loja";
    }

    @Override
    public boolean disponivelPara(Pedido pedido) {
        return pedido.getDistanciaKm() <= 25;
    }

    @Override
    public double calcularFrete(Pedido pedido) {
        return 0;
    }

    @Override
    public int estimarPrazoDias(Pedido pedido) {
        return 1;
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
                ? "O pedido " + pedido.getNumero() + " está aguardando retirada na loja."
                : "Para a modalidade " + this.getNome() + ", a distância deve ser menor ou igual a 25Km.";
    }
}
public interface ModalidadeEntrega {
    String getNome();
    boolean disponivelPara(Pedido pedido);
    double calcularFrete(Pedido pedido);
    int estimarPrazoDias(Pedido pedido);
    String gerarResumo(Pedido pedido);
    String despachar(Pedido pedido);
}

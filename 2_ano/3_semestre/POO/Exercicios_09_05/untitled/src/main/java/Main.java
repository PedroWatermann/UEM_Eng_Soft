public class Main {
    public static void main(String[] args) {

        // === Criando a Central de Logística ===
        CentralLogistica central = new CentralLogistica(3);
        central.cadastrarModalidade(new RetiradaNaLoja("Loja Centro"));
        central.cadastrarModalidade(new MotoboyUrbano("MotoFast", 5.00));
        central.cadastrarModalidade(new TransportadoraNacional("Rapidão Cometa", 0.02));

        // === Pedido 1: leve, urbano, não frágil ===
        Pedido pedido1 = new Pedido("P001", "Ana Silva", 2.0, 8.0, 150.00, false);

        System.out.println("==========================================");
        System.out.println("PEDIDO 1");
        System.out.println("==========================================");
        System.out.println(pedido1.resumo());
        System.out.println();

        central.imprimirCotacoes(pedido1);
        System.out.println();

        ModalidadeEntrega maisBarata1 = central.obterMaisBarata(pedido1);
        ModalidadeEntrega maisRapida1 = central.obterMaisRapida(pedido1);

        System.out.println("Modalidade mais barata: " + maisBarata1.getNome()
                + " (R$ " + maisBarata1.calcularFrete(pedido1) + ")");
        System.out.println("Modalidade mais rápida: " + maisRapida1.getNome()
                + " (" + maisRapida1.estimarPrazoDias(pedido1) + " dia(s))");
        System.out.println();

        // Despachando com Motoboy (mais rápida para pedido urbano)
        ModalidadeEntrega escolha1 = maisRapida1;
        System.out.println("Despacho: " + escolha1.despachar(pedido1));

        // Se implementar Rastreavel, exibe rastreio
        if (escolha1 instanceof Rastreavel) {
            Rastreavel rastreavel1 = (Rastreavel) escolha1;
            System.out.println("Código de rastreio: " + rastreavel1.gerarCodigoRastreio(pedido1));
            System.out.println("Status: " + rastreavel1.consultarStatus());
        }

        double totalPedido1 = pedido1.getValorProdutos() + escolha1.calcularFrete(pedido1);
        System.out.println("Valor total (produto + frete): R$ " + totalPedido1);

        // === Pedido 2: pesado, distante, frágil ===
        Pedido pedido2 = new Pedido("P002", "Carlos Mendes", 18.0, 450.0, 1200.00, true);

        System.out.println();
        System.out.println("==========================================");
        System.out.println("PEDIDO 2");
        System.out.println("==========================================");
        System.out.println(pedido2.resumo());
        System.out.println();

        central.imprimirCotacoes(pedido2);
        System.out.println();

        ModalidadeEntrega maisBarata2 = central.obterMaisBarata(pedido2);
        ModalidadeEntrega maisRapida2 = central.obterMaisRapida(pedido2);

        System.out.println("Modalidade mais barata: " + maisBarata2.getNome()
                + " (R$ " + maisBarata2.calcularFrete(pedido2) + ")");
        System.out.println("Modalidade mais rápida: " + maisRapida2.getNome()
                + " (" + maisRapida2.estimarPrazoDias(pedido2) + " dia(s))");
        System.out.println();

        // Despachando com Transportadora (única disponível para pedido pesado e distante)
        ModalidadeEntrega escolha2 = maisBarata2;
        System.out.println("Despacho: " + escolha2.despachar(pedido2));

        if (escolha2 instanceof Rastreavel) {
            Rastreavel rastreavel2 = (Rastreavel) escolha2;
            System.out.println("Código de rastreio: " + rastreavel2.gerarCodigoRastreio(pedido2));
            System.out.println("Status: " + rastreavel2.consultarStatus());
        }

        double totalPedido2 = pedido2.getValorProdutos() + escolha2.calcularFrete(pedido2);
        System.out.println("Valor total (produto + frete): R$ " + totalPedido2);
    }
}
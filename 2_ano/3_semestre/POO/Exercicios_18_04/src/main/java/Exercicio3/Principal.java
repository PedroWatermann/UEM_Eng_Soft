package Exercicio3;

public class Principal {
    static void main() {

        Produto p1 = new Produto("Computador", 5236.99);
        Produto p2 = new Produto("Mouse", 143.76);
        Produto p3 = new Produto("Teclado", 259.32);
        double total = p1.getPreco() + p2.getPreco() + p3.getPreco();

        System.out.println("=== Produtos ===");
        System.out.printf("  %s: R$ %.2f%n", p1.getNome(), p1.getPreco());
        System.out.printf("  %s: R$ %.2f%n", p2.getNome(), p2.getPreco());
        System.out.printf("  %s: R$ %.2f%n", p3.getNome(), p3.getPreco());
        System.out.printf("  Total: R$ %.2f%n", total);


        System.out.println("\n=== PAGAMENTO VIA PIX ===");
        Pix pix = new Pix("pedro@banco.com", "Banco do Brasil");
        double valorFinalPix = pix.calcularDesconto(total);
        System.out.printf("  Desconto: %.0f%% | Original: R$ %.2f | Final: R$ %.2f%n",
                pix.getPercentualDesconto() * 100, total, valorFinalPix);
        boolean aprovadoPix = pix.processarPagamento(valorFinalPix);
        System.out.println("  Aprovado: " + aprovadoPix);
        System.out.println("  " + pix.getComprovante());


        System.out.println("\n=== PAGAMENTO VIA BOLETO ===");
        Boleto boleto = new Boleto("12345.67890 00001.000001 00000.000001 1 00010000000", "2026-05-01");
        double valorFinalBoleto = boleto.calcularDesconto(total);
        System.out.printf("  Desconto: %.0f%% | Original: R$ %.2f | Final: R$ %.2f%n",
                boleto.getPercentualDesconto() * 100, total, valorFinalBoleto);
        boolean aprovadoBoleto = boleto.processarPagamento(valorFinalBoleto);
        System.out.println("  Aprovado: " + aprovadoBoleto);
        System.out.println("  " + boleto.getComprovante());


        System.out.println("\n=== PAGAMENTO COM CARTÃO (parcelado) ===");
        CartaoCredito cartao = new CartaoCredito("Pedro", "1234", 10000.00);
        int parcelas = 6;
        double valorParcela = cartao.calcularValorParcela(total, parcelas);
        System.out.printf("  %d× de R$ %.2f (com juros de 1,5%% a.m.)%n", parcelas, valorParcela);
        boolean aprovadoParcelado = cartao.processarParcelado(total, parcelas);
        System.out.println("  Aprovado: " + aprovadoParcelado);
        System.out.println("  " + cartao.getComprovante());
        System.out.printf("  Limite restante: R$ %.2f%n", cartao.getLimite());


        System.out.println("\n=== PAGAMENTO COM CARTÃO (à vista) ===");
        boolean aprovadoVista = cartao.processarPagamento(total);
        System.out.println("  Aprovado: " + aprovadoVista);
        System.out.println("  " + cartao.getComprovante());
        System.out.printf("  Limite restante: R$ %.2f%n", cartao.getLimite());


        System.out.println("\n=== TENTATIVA COM LIMITE Esgotado ===");
        boolean recusado = cartao.processarPagamento(total);
        System.out.println("  Aprovado: " + recusado);
        System.out.println("  " + cartao.getComprovante());
    }
}
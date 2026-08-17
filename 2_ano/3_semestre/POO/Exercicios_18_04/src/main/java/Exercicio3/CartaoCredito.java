package Exercicio3;

public class CartaoCredito implements MeioPagamento, Parcelavel {
    private String nomeTitular;
    private String ultimosDigitos;
    private double limite;
    private String ultimoComprovante;

    public String getNomeTitular() { return nomeTitular; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }

    public String getUltimosDigitos() { return ultimosDigitos; }
    public void setUltimosDigitos(String ultimosDigitos) {
        if (ultimosDigitos.length() != 4)
            throw new IllegalArgumentException("Os últimos dígitos devem ter exatamente 4 dígitos");
        this.ultimosDigitos = ultimosDigitos;
    }

    public double getLimite() { return limite; }
    public void setLimite(double limite) { this.limite = limite; }

    public CartaoCredito(String nomeTitular, String ultimosDigitos, double limite) {
        setNomeTitular(nomeTitular);
        setUltimosDigitos(ultimosDigitos);
        setLimite(limite);
    }

    @Override
    public boolean processarPagamento(double valor) {
        if (valor > this.limite) {
            this.ultimoComprovante = "Pagamento recusado: limite insuficiente.";
            return false;
        }
        this.limite -= valor;
        this.ultimoComprovante = String.format(
                "Comprovante - %s%nTitular: %s | Cartão final: %s%nValor: R$ %.2f (à vista)",
                getNomeMeio(), nomeTitular, ultimosDigitos, valor
        );
        return true;
    }

    @Override
    public String getComprovante() {
        return ultimoComprovante;
    }

    @Override
    public String getNomeMeio() { return "Cartão de Crédito"; }

    @Override
    public double calcularValorParcela(double valor, int parcelas) {
        double totalComJuros = valor * (1 + 0.015 * parcelas);
        return totalComJuros / parcelas;
    }

    @Override
    public boolean processarParcelado(double valor, int parcelas) {
        if (parcelas < 1 || parcelas > 12) return false;

        double totalComJuros = valor * (1 + 0.015 * parcelas);
        double valorParcela = totalComJuros / parcelas;

        if (totalComJuros > this.limite) {
            this.ultimoComprovante = "Pagamento recusado: limite insuficiente.";
            return false;
        }
        this.limite -= totalComJuros;
        this.ultimoComprovante = String.format(
                "Comprovante - %s%nTitular: %s | Cartão final: %s%n%d× de R$ %.2f | Total: R$ %.2f",
                getNomeMeio(), nomeTitular, ultimosDigitos, parcelas, valorParcela, totalComJuros
        );
        return true;
    }
}
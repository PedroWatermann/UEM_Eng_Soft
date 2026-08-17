package Exercicio3;

import java.time.LocalDate;

public class Boleto implements MeioPagamento, DescontoAVista {
    private String codigoBarras;
    private String dataVencimento;
    private String ultimoComprovante;

    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }

    public String getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(String dataVencimento) {

        LocalDate.parse(dataVencimento);
        this.dataVencimento = dataVencimento;
    }

    public Boleto(String codigoBarras, String dataVencimento) {
        setCodigoBarras(codigoBarras);
        setDataVencimento(dataVencimento);
    }

    @Override
    public double getPercentualDesconto() { return 5.0 / 100; }

    @Override
    public double calcularDesconto(double valor) {
        return valor * (1 - getPercentualDesconto());
    }

    @Override
    public boolean processarPagamento(double valor) {
        String hoje = LocalDate.now().toString();

        if (dataVencimento.compareTo(hoje) < 0) {
            this.ultimoComprovante = "Pagamento recusado: boleto vencido.";
            return false;
        }
        this.ultimoComprovante = String.format(
                "Comprovante - %s%nCódigo: %s | Vencimento: %s%nValor: R$ %.2f",
                getNomeMeio(), codigoBarras, dataVencimento, valor
        );
        return true;
    }

    @Override
    public String getComprovante() { return ultimoComprovante; }

    @Override
    public String getNomeMeio() { return "Boleto"; }
}
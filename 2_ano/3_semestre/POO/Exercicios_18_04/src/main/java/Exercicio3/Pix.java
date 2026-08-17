package Exercicio3;

public class Pix implements MeioPagamento, DescontoAVista {
    private String chavePix;
    private String banco;
    private String ultimoComprovante;

    public String getChavePix() { return chavePix; }
    public void setChavePix(String chavePix) { this.chavePix = chavePix; }

    public String getBanco() { return banco; }
    public void setBanco(String banco) { this.banco = banco; }

    public Pix(String chavePix, String banco) {
        setChavePix(chavePix);
        setBanco(banco);
    }

    @Override
    public double getPercentualDesconto() { return 10.0 / 100; }

    @Override
    public double calcularDesconto(double valor) {
        return valor * (1 - getPercentualDesconto());
    }

    @Override
    public boolean processarPagamento(double valor) {
        this.ultimoComprovante = String.format(
                "Comprovante - %s%nChave: %s | Banco: %s%nValor: R$ %.2f",
                getNomeMeio(), chavePix, banco, valor
        );
        return true;
    }

    @Override
    public String getComprovante() { return ultimoComprovante; }

    @Override
    public String getNomeMeio() { return "Pix"; }
}
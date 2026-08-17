package Exercicio3;

public interface MeioPagamento {
    boolean processarPagamento(double valor);
    String getComprovante();
    String getNomeMeio();
}

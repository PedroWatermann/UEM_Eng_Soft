package Exercicio1;

public class Estagiario extends  Funcionario {
    private String instituicaoEnsino;
    private String orientador;

    public String getInstituicaoEnsino() {
        return instituicaoEnsino;
    }

    public void setInstituicaoEnsino(String instituicaoEnsino) {
        this.instituicaoEnsino = instituicaoEnsino;
    }

    public String getOrientador() {
        return orientador;
    }

    public void setOrientador(String orientador) {
        this.orientador = orientador;
    }

    public Estagiario(String nome, String cpf, double salarioBase, String instituicaoEnsino, String orientador) {
        super(nome, cpf, salarioBase);
        setInstituicaoEnsino(instituicaoEnsino);
        setOrientador(orientador);
    }

    @Override
    public double calcularSalarioFinal() {
        return this.salarioBase;
    }

    @Override
    public String gerarContraCheque() {
        return String.format(
            "%s%n\t\t- Instituição de ensino: %s%n\t\t- Orientador: %s",
            super.gerarContraCheque(),
            this.instituicaoEnsino,
            this.orientador
        );
    }
}

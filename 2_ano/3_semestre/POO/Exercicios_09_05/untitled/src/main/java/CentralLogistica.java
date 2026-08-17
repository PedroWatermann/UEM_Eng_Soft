public class CentralLogistica {
    private ModalidadeEntrega[] modalidades;
    private int quantidadeDeModalidadesUsadas = 0;

    public CentralLogistica(int capacidadeMaximaModalidades) {
        this.modalidades = new ModalidadeEntrega[capacidadeMaximaModalidades];
    }

    public void cadastrarModalidade(ModalidadeEntrega modalidadeEntrega) {
        if (quantidadeDeModalidadesUsadas < modalidades.length) {
            this.modalidades[quantidadeDeModalidadesUsadas] = modalidadeEntrega;
            quantidadeDeModalidadesUsadas++;
        }
    }

    // Método auxiliar: retorna apenas as posições ocupadas
    private ModalidadeEntrega[] getModalidadesCadastradas() {
        ModalidadeEntrega[] cadastradas = new ModalidadeEntrega[quantidadeDeModalidadesUsadas];
        for (int i = 0; i < quantidadeDeModalidadesUsadas; i++) {
            cadastradas[i] = modalidades[i];
        }
        return cadastradas;
    }

    public ModalidadeEntrega[] obterDisponiveis(Pedido pedido) {
        int count = 0;
        for (ModalidadeEntrega m : getModalidadesCadastradas()) {
            if (m.disponivelPara(pedido)) count++;
        }

        ModalidadeEntrega[] disponiveis = new ModalidadeEntrega[count];
        int i = 0;
        for (ModalidadeEntrega m : getModalidadesCadastradas()) {
            if (m.disponivelPara(pedido)) {
                disponiveis[i++] = m;
            }
        }
        return disponiveis;
    }

    public ModalidadeEntrega obterMaisBarata(Pedido pedido) {
        ModalidadeEntrega[] disponiveis = obterDisponiveis(pedido);
        if (disponiveis.length == 0) return null;

        ModalidadeEntrega maisBarata = disponiveis[0];
        for (ModalidadeEntrega m : disponiveis) {
            if (m.calcularFrete(pedido) < maisBarata.calcularFrete(pedido)) {
                maisBarata = m;
            }
        }
        return maisBarata;
    }

    public ModalidadeEntrega obterMaisRapida(Pedido pedido) {
        ModalidadeEntrega[] disponiveis = obterDisponiveis(pedido);
        if (disponiveis.length == 0) return null;

        ModalidadeEntrega maisRapida = disponiveis[0];
        for (ModalidadeEntrega m : disponiveis) {
            if (m.estimarPrazoDias(pedido) < maisRapida.estimarPrazoDias(pedido)) {
                maisRapida = m;
            }
        }
        return maisRapida;
    }

    public void imprimirCotacoes(Pedido pedido) {
        ModalidadeEntrega[] disponiveis = obterDisponiveis(pedido);
        System.out.println("=== Cotações disponíveis ===");
        if (disponiveis.length == 0) {
            System.out.println("Nenhuma modalidade disponível para este pedido.");
            return;
        }
        for (ModalidadeEntrega m : disponiveis) {
            System.out.println(m.gerarResumo(pedido));
        }
    }
}
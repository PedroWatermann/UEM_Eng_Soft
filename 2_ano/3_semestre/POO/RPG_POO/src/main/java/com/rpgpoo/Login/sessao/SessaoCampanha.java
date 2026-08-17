package com.rpgpoo.Login.sessao;

import com.rpgpoo.Campanha.model.CampanhaModel;

public class SessaoCampanha {
    private static SessaoCampanha instancia;

    private CampanhaModel campanhaLogada;

    private SessaoCampanha() {}

    public static SessaoCampanha getInstancia() {
        if (instancia == null) {
            instancia = new SessaoCampanha();
        }
        return instancia;
    }

    public CampanhaModel getCampanhaLogada() {
        return campanhaLogada;
    }

    public void setCampanhaLogada(CampanhaModel jogador) {
        this.campanhaLogada = jogador;
    }

    public boolean estaLogado() {
        return campanhaLogada != null;
    }

    public boolean jogadorLogadoEhMestre() {
        return this.campanhaLogada.getMestre().getId() == SessaoUsuario.getInstancia().getJogadorLogado().getId();
    }

    public void logout() {
        this.campanhaLogada = null;
    }
}

package com.rpgpoo.Login.sessao;

import com.rpgpoo.Jogador.model.JogadorModel;

public class SessaoUsuario {
    private static SessaoUsuario instancia;

    private JogadorModel jogadorLogado;

    private SessaoUsuario() {}

    public static SessaoUsuario getInstancia() {
        if (instancia == null) {
            instancia = new SessaoUsuario();
        }
        return instancia;
    }

    public JogadorModel getJogadorLogado() {
        return jogadorLogado;
    }

    public void setJogadorLogado(JogadorModel jogador) {
        this.jogadorLogado = jogador;
    }

    public boolean estaLogado() {
        return jogadorLogado != null;
    }

    public void logout() {
        this.jogadorLogado = null;
    }
}

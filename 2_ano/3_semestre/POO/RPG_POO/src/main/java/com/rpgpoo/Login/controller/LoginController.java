package com.rpgpoo.Login.controller;

import com.rpgpoo.Campanha.view.CampanhaSelectView;
import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Jogador.model.JogadorModel;
import com.rpgpoo.Login.sessao.SessaoUsuario;
import com.rpgpoo.Login.view.LoginView;
import com.rpgpoo.utils.JpaUtil;
import jakarta.persistence.EntityManager;

import javax.swing.*;

public class LoginController {
    private final LoginView view;
    private final Gerenciador gerenciador;

    public LoginController(LoginView view, Gerenciador gerenciador) {
        this.view = view;
        this.gerenciador = gerenciador;
    }

    public void btnEntrarClick() {
        String usuario = view.getTxtUsuario().getText().trim();
        String senha = new String(view.getTxtSenha().getPassword()).trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Preencha todos os campos.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (EntityManager em = JpaUtil.getEntityManager()) {
            JogadorModel jogador = em
                    .createNamedQuery("Jogador.login", JogadorModel.class)
                    .setParameter("nome", usuario).setParameter("senha", senha)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (jogador == null) {
                JOptionPane.showMessageDialog(view, "Usuário e/ou senha incorretos. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                SessaoUsuario.getInstancia().setJogadorLogado(jogador);

                gerenciador.addPainel(new CampanhaSelectView(gerenciador), "campanhaSelect");
                gerenciador.navegarPara("campanhaSelect", true, "Selecionar Campanha", true);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void lblRegistrarClick() {
        String usuario = view.getTxtUsuario().getText().trim();
        String senha = new String(view.getTxtSenha().getPassword()).trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Preencha todos os campos.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (usuario.contains(" ")) {
            JOptionPane.showMessageDialog(view, "O nome de usuário não deve conter espaços vazios.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (EntityManager em = JpaUtil.getEntityManager()) {
            JogadorModel jogador = em
                    .createNamedQuery("Jogador.porNome", JogadorModel.class)
                    .setParameter("nome", usuario)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (jogador != null) {
                JOptionPane.showMessageDialog(view, "Este nome de usuário já está em uso.", "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }

            em.getTransaction().begin();

            jogador = new JogadorModel(usuario, senha);
            em.persist(jogador);

            SessaoUsuario.getInstancia().setJogadorLogado(jogador);

            em.getTransaction().commit();

            gerenciador.addPainel(new CampanhaSelectView(gerenciador), "campanhaSelect");
            gerenciador.navegarPara("campanhaSelect", true, "Selecionar Campanha", true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

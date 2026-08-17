package com.rpgpoo.Campanha.controller;

import com.rpgpoo.Campanha.model.CampanhaModel;
import com.rpgpoo.Campanha.view.CampanhaCreateView;
import com.rpgpoo.Dado.model.DadoModel;
import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Jogador.model.JogadorModel;
import com.rpgpoo.Login.sessao.SessaoCampanha;
import com.rpgpoo.Login.sessao.SessaoUsuario;
import com.rpgpoo.utils.JpaUtil;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.util.List;

public class CampanhaCreateController {
    private final CampanhaCreateView view;
    private final Gerenciador gerenciador;
    private final boolean ehNovo;
    private final Runnable sucesso;

    private JogadorModel mestre;

    public CampanhaCreateController(CampanhaCreateView view, Gerenciador gerenciador, boolean ehNovo, Runnable sucesso) {
        this.view = view;
        this.gerenciador = gerenciador;
        this.ehNovo = ehNovo;
        this.sucesso = sucesso;
    }

    public void btnCriarCampanhaClick() {
        String nome = view.getTxtNome().getText();
        String descricao = view.getTxtDescricao().getText();
        DadoModel dado = (DadoModel) view.getCbxDadoPadrao().getSelectedItem();

        if (nome.isEmpty() || dado == null) {
            JOptionPane.showMessageDialog(
                    view,
                    "Preencha todos os campos obrigatórios ('Nome', 'Dado padrão').",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try (EntityManager em = JpaUtil.getEntityManager()) {
            em.getTransaction().begin();

            if (ehNovo){
                CampanhaModel campanha = new CampanhaModel(
                    nome,
                    descricao,
                    null,
                    null,
                    dado,
                    this.mestre
                );
                em.persist(campanha);
            } else {
                CampanhaModel campanha = em.find(CampanhaModel.class, SessaoCampanha.getInstancia().getCampanhaLogada().getId());
                campanha.setNome(nome);
                campanha.setDescricao(descricao);
                campanha.setDado(dado);
                campanha.setMestre(this.mestre);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Ocorreu um erro ao salvar a campanha.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        if (sucesso != null) {
            sucesso.run();
        }
    }

    public void preencherCampos() {
        this.mestre = SessaoUsuario.getInstancia().getJogadorLogado();
        if (this.ehNovo) {
            view.getTxtMestre().setText(this.mestre.toString());
            this.listarDados().forEach(view.getCbxDadoPadrao()::addItem);
        } else {
            int idCampanha = SessaoCampanha.getInstancia().getCampanhaLogada().getId();
            try (EntityManager em = JpaUtil.getEntityManager()) {
                CampanhaModel campanha = em
                        .createNamedQuery("Campanha.porIdCampanha",  CampanhaModel.class)
                        .setParameter("id", idCampanha)
                        .getResultStream()
                        .findFirst()
                        .orElse(null);

                if (campanha != null) {
                    view.getTxtNome().setText(campanha.getNome());
                    view.getTxtDescricao().setText(campanha.getDescricao());
                    view.getTxtMestre().setText(campanha.getMestre().getNome());
                    this.listarDados().forEach(view.getCbxDadoPadrao()::addItem);
                    view.getCbxDadoPadrao().setSelectedItem(campanha.getDado());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<DadoModel> listarDados() {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            return em.createQuery("FROM DadoModel d ORDER BY d.lados", DadoModel.class).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return List.of();
        }
    }
}

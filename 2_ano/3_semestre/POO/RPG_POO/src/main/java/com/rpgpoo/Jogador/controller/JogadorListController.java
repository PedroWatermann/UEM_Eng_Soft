package com.rpgpoo.Jogador.controller;

import com.rpgpoo.Campanha.model.CampanhaModel;
import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Jogador.model.JogadorModel;
import com.rpgpoo.Jogador.view.JogadorListView;
import com.rpgpoo.Login.sessao.SessaoCampanha;
import com.rpgpoo.Login.sessao.SessaoUsuario;
import com.rpgpoo.Personagem.model.PersonagemModel;
import com.rpgpoo.utils.JpaUtil;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class JogadorListController {
    private final JogadorListView view;
    private final Gerenciador gerenciador;
    private final Runnable sucesso;

    public JogadorListController(JogadorListView view, Gerenciador gerenciador, Runnable sucesso) {
        this.view = view;
        this.gerenciador = gerenciador;
        this.sucesso = sucesso;
    }

    public List<JogadorModel> listarJogadores() {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            int mestreId = SessaoUsuario.getInstancia().getJogadorLogado().getId();

            return em.createQuery("""
                    SELECT j
                    FROM JogadorModel j
                    WHERE j.id <> :idMestre
                """, JogadorModel.class)
                    .setParameter("idMestre", mestreId)
                    .getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Dictionary<String, Object> listarPersonagensDoJogadorParaTabela(JogadorModel jogador) {
        try (EntityManager em = JpaUtil.getEntityManager()) {

            List<PersonagemModel> personagens = em
                    .createQuery("""
                    SELECT p
                    FROM PersonagemModel p
                    WHERE p.jogador.id = :idJogador
                """, PersonagemModel.class)
                    .setParameter("idJogador", jogador.getId())
                    .getResultList();

            Object[][] dados = new Object[personagens.size()][4];

            for (int i = 0; i < personagens.size(); i++) {
                PersonagemModel personagem = personagens.get(i);
                dados[i][0] = personagem;
                dados[i][1] = personagem.getNivel();
                dados[i][2] = personagem.getRaca();
                dados[i][3] = personagem.getClasse();
            }

            Dictionary<String, Object> dicionarioRetorno = new Hashtable<>();
            dicionarioRetorno.put("dados", dados);
            dicionarioRetorno.put("colunas", new Object[]{"Nome", "Nível", "Raça", "Classe"});
            return dicionarioRetorno;

        } catch (Exception ex) {
            ex.printStackTrace();
            return new Hashtable<>();
        }
    }

    public void cbxJogadoresItemSelect(JComboBox<JogadorModel> cbxJogadores, ItemEvent e) {
        JTable tblPersonagens = view.getTblPersonagens();
        if (e.getStateChange() != ItemEvent.SELECTED) {
            tblPersonagens.setEnabled(false);
            return;
        }

        JogadorModel jogadorSelecionado = (JogadorModel) cbxJogadores.getSelectedItem();
        if (jogadorSelecionado == null) return;

        Dictionary<String, Object> dadosPersonagens = this.listarPersonagensDoJogadorParaTabela(jogadorSelecionado);
        Object[][] dados = (Object[][]) dadosPersonagens.get("dados");
        Object[] colunas = (Object[]) dadosPersonagens.get("colunas");

        tblPersonagens.setModel(new DefaultTableModel(dados, colunas));

        boolean temPersonagens = dados != null && dados.length > 0;
        tblPersonagens.setEnabled(temPersonagens);

        view.getBtnAdicionar().setEnabled(temPersonagens);
    }

    public void btnAdicionarClick() {
        JogadorModel jogadorSelecionado = (JogadorModel) view.getCbxJogadores().getSelectedItem();
        if (jogadorSelecionado == null) return;

        JTable tblPersonagens = view.getTblPersonagens();
        int linha = tblPersonagens.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(
                view,
                "Selecione um personagem para adicionar!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int i = tblPersonagens.convertRowIndexToModel(linha);
        PersonagemModel personagemSelecionado = (PersonagemModel) tblPersonagens.getModel().getValueAt(i, 0);

        try (EntityManager em = JpaUtil.getEntityManager()) {
            em.getTransaction().begin();

            CampanhaModel campanha = em.find(CampanhaModel.class, SessaoCampanha.getInstancia().getCampanhaLogada().getId());
            JogadorModel jogador = em.find(JogadorModel.class, jogadorSelecionado.getId());
            PersonagemModel personagem = em.find(PersonagemModel.class, personagemSelecionado.getId());

            if (!campanha.getJogadores().contains(jogador)) {
                campanha.getJogadores().add(jogador);
            }
            if (!campanha.getPersonagens().contains(personagem)) {
                campanha.getPersonagens().add(personagem);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (sucesso != null) sucesso.run();
    }
}

package com.rpgpoo.Campanha.controller;

import com.rpgpoo.Campanha.model.CampanhaModel;
import com.rpgpoo.Campanha.view.CampanhaCreateView;
import com.rpgpoo.Campanha.view.CampanhaListView;
import com.rpgpoo.Campanha.view.CampanhaSelectView;
import com.rpgpoo.Dado.model.DadoModel;
import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Jogador.model.JogadorModel;
import com.rpgpoo.Jogador.view.JogadorListView;
import com.rpgpoo.Login.sessao.SessaoCampanha;
import com.rpgpoo.Personagem.model.PersonagemModel;
import com.rpgpoo.utils.AppColors;
import com.rpgpoo.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import org.kordamp.ikonli.fontawesome6.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CampanhaListController {
    private final CampanhaListView view;
    private final Gerenciador gerenciador;

    public CampanhaListController(CampanhaListView view, Gerenciador gerenciador) {
        this.view = view;
        this.gerenciador = gerenciador;
    }

    public Dictionary<String, Object> listarJogadoresEPersonagensParaTabela() {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            int campanhaId = SessaoCampanha.getInstancia().getCampanhaLogada().getId();

            List<PersonagemModel> personagens = em
                    .createQuery("""
                        SELECT p FROM CampanhaModel c
                        JOIN c.personagens p
                        JOIN FETCH p.jogador
                        WHERE c.id = :campanhaId
                    """, PersonagemModel.class)
                    .setParameter("campanhaId", campanhaId)
                    .getResultList();

            Object[][] dados = new Object[personagens.size()][6];
            FontIcon iconeUsuario = FontIcon.of(FontAwesomeSolid.USER_ALT, AppColors.ICON_SM, AppColors.PARCHMENT);

            for (int i = 0; i < personagens.size(); i++) {
                PersonagemModel personagem = personagens.get(i);

                dados[i][0] = iconeUsuario;
                dados[i][1] = personagem.getJogador();
                dados[i][2] = personagem;
                dados[i][3] = personagem.getNivel();
                dados[i][4] = personagem.getRaca();
                dados[i][5] = personagem.getClasse();
            }

            Dictionary<String, Object> dicionarioRetorno = new Hashtable<>();
            dicionarioRetorno.put("dados", dados);
            dicionarioRetorno.put("colunas", new Object[]{"", "Jogador", "Personagem", "Nível", "Raça", "Classe"});
            return dicionarioRetorno;

        } catch (Exception ex) {
            ex.printStackTrace();
            return new Hashtable<>();
        }
    }

    public void recarregarTblJogadores() {
        Dictionary<String, Object> campanhas = listarJogadoresEPersonagensParaTabela();

        view.getTblJogadores().setModel(
                new DefaultTableModel(
                        (Object[][]) campanhas.get("dados"),
                        (Object[]) campanhas.get("colunas")
                )
        );

        view.getTblJogadores().getColumnModel().getColumn(0).setMaxWidth(30);
    }

    public void preencherCampos() {
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

    public void btnTrocarCampanhaClick() {
        if (JOptionPane.showConfirmDialog(
            view,
            "Deseja trocar realmente de campanha?",
            "Atenção",
            JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION) {
            JDialog dialog = new JDialog(gerenciador, "Narratus RPG - Selecionar Campanha", true);

            CampanhaSelectView campanhaSelectView = new CampanhaSelectView(gerenciador, true, () -> {
                dialog.dispose();
                recarregarTblJogadores();
                preencherCampos();
            });

            dialog.setContentPane(campanhaSelectView);
            dialog.pack();
            dialog.setLocationRelativeTo(gerenciador);
            dialog.setVisible(true);
        }
    }

    public void btnAdicionarJogadorClick() {
        JDialog dialog = new JDialog(gerenciador, "Narratus RPG - Adicionar Jogador", true);

        JogadorListView jogadorListView = new JogadorListView(this.gerenciador, () -> {
            dialog.dispose();
            recarregarTblJogadores();
        });

        dialog.setContentPane(jogadorListView);
        dialog.setMinimumSize(new Dimension(800,0));
        dialog.pack();
        dialog.setLocationRelativeTo(this.gerenciador);
        dialog.setVisible(true);
    }

    public void btnRemoverJogadorClick() {
        if (JOptionPane.showConfirmDialog(
            view,
            "Deseja realmente excluir este jogador?",
            "Atenção",
            JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION) {
            JTable tblJogadores = view.getTblJogadores();
            int linha = tblJogadores.getSelectedRow();
            if (linha == -1) return;

            int i = tblJogadores.convertRowIndexToModel(linha);
            JogadorModel jogadorSelecionado = (JogadorModel) tblJogadores.getModel().getValueAt(i, 1);
            PersonagemModel personagemSelecionado = (PersonagemModel) tblJogadores.getModel().getValueAt(i, 2);

            try (EntityManager em = JpaUtil.getEntityManager()) {
                em.getTransaction().begin();

                CampanhaModel campanha = em.find(CampanhaModel.class, SessaoCampanha.getInstancia().getCampanhaLogada().getId());
                JogadorModel jogador = em.find(JogadorModel.class, jogadorSelecionado.getId());
                PersonagemModel personagem = em.find(PersonagemModel.class, personagemSelecionado.getId());

                campanha.getPersonagens().remove(personagem);
                campanha.getJogadores().remove(jogador);

                em.getTransaction().commit();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            recarregarTblJogadores();
        }
    }

    public void btnEditarCampanhaClick() {
        JDialog dialog = new JDialog(gerenciador, "Narratus RPG - Editar Campanha", true);

        CampanhaCreateView campanhaCreateView = new CampanhaCreateView(this.gerenciador, false, dialog::dispose);

        dialog.setContentPane(campanhaCreateView);
        dialog.pack();
        dialog.setLocationRelativeTo(this.gerenciador);
        dialog.setVisible(true);
    }

    public void tblJogadoresSelectRow(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            view.getBtnRemoverJogador().setEnabled(view.getTblJogadores().getSelectedRow() != -1);
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

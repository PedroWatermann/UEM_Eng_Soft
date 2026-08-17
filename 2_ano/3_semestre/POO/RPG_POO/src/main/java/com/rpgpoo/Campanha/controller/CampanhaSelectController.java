package com.rpgpoo.Campanha.controller;

import com.rpgpoo.Campanha.model.CampanhaModel;
import com.rpgpoo.Campanha.view.CampanhaCreateView;
import com.rpgpoo.Campanha.view.CampanhaListView;
import com.rpgpoo.Campanha.view.CampanhaSelectView;
import com.rpgpoo.Jogador.model.JogadorModel;
import com.rpgpoo.Login.sessao.SessaoCampanha;
import com.rpgpoo.Login.sessao.SessaoUsuario;
import com.rpgpoo.Monstro.view.MonstroView;
import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Gerenciador.TabbedView;
import com.rpgpoo.Item.view.ItemView;
import com.rpgpoo.Personagem.view.PersonagemView;
import com.rpgpoo.RpgLog.view.RpgLogView;
import com.rpgpoo.utils.AppColors;
import com.rpgpoo.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import org.kordamp.ikonli.fontawesome6.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class CampanhaSelectController {
    private final CampanhaSelectView view;
    private final Gerenciador gerenciador;
    private final boolean modoTroca;
    private final Runnable aoSelecionar;

    public CampanhaSelectController(CampanhaSelectView view, Gerenciador gerenciador) {
        this(view, gerenciador, false, null);
    }

    public CampanhaSelectController(CampanhaSelectView view, Gerenciador gerenciador, boolean modoTroca, Runnable aoSelecionar) {
        this.view = view;
        this.gerenciador = gerenciador;
        this.modoTroca = modoTroca;
        this.aoSelecionar = aoSelecionar;
    }

    public void btnNovoClick() {
        JDialog dialog = new JDialog(gerenciador, "Narratus RPG - Nova Campanha", true);

        CampanhaCreateView campanhaCreateView = new CampanhaCreateView(this.gerenciador, true, () -> {
            dialog.dispose();
            recarregarTblCampanha();
        });

        dialog.setContentPane(campanhaCreateView);
        dialog.pack();
        dialog.setLocationRelativeTo(this.gerenciador);
        dialog.setVisible(true);
    }

    public void btnEditarClick() {
        JDialog dialog = new JDialog(gerenciador, "Narratus RPG - Editar Campanha", true);

        CampanhaCreateView campanhaCreateView = new CampanhaCreateView(this.gerenciador, false, dialog::dispose);

        dialog.setContentPane(campanhaCreateView);
        dialog.pack();
        dialog.setLocationRelativeTo(this.gerenciador);
        dialog.setVisible(true);
    }

    public void btnExcluirClick() {
        if (JOptionPane.showConfirmDialog(
            view,
            "Deseja realmente excluir essa campanha?",
            "Atenção",
            JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION) {
            int idCampanha = SessaoCampanha.getInstancia().getCampanhaLogada().getId();
            if (idCampanha > 0) {
                try (EntityManager em = JpaUtil.getEntityManager()) {
                    em.getTransaction().begin();
                    CampanhaModel campanha = em.find(CampanhaModel.class, idCampanha);
                    if (campanha != null) {
                        em.remove(campanha);
                        em.getTransaction().commit();
                        recarregarTblCampanha();
                        JOptionPane.showMessageDialog(
                                view,
                                "Campanha excluída com sucesso!",
                                "Sucesso",
                                JOptionPane.PLAIN_MESSAGE
                        );
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void btnSelecionarClick() {
        if (modoTroca) {
            if (aoSelecionar != null) {
                aoSelecionar.run();
            }
            return;
        }

        TabbedView tabbed = new TabbedView();
        tabbed.addAba("Campanha", FontIcon.of(FontAwesomeSolid.MAP, AppColors.ICON_SM, AppColors.GOLD), new CampanhaListView(gerenciador));
        tabbed.addAba("Personagem", FontIcon.of(FontAwesomeSolid.USER, AppColors.ICON_SM, AppColors.GOLD), new PersonagemView(gerenciador));
        tabbed.addAba("Monstro", FontIcon.of(FontAwesomeSolid.SKULL, AppColors.ICON_SM, AppColors.GOLD), new MonstroView(gerenciador));
        tabbed.addAba("Itens", FontIcon.of(FontAwesomeSolid.SHOPPING_BAG, AppColors.ICON_SM, AppColors.GOLD), new ItemView(gerenciador));
        tabbed.addAba("Log", FontIcon.of(FontAwesomeSolid.TERMINAL, AppColors.ICON_SM, AppColors.GOLD), new RpgLogView(gerenciador));

        gerenciador.addPainel(tabbed, "tabbed");
        gerenciador.navegarPara("tabbed", false, "", true);
    }

    public Dictionary<String, Object> listarCampanhasParaTabela() {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            JogadorModel jogador = SessaoUsuario.getInstancia().getJogadorLogado();

            List<CampanhaModel> campanhas = em
                    .createQuery("SELECT DISTINCT c FROM CampanhaModel c " +
                                         "JOIN FETCH c.mestre " +
                                         "LEFT JOIN c.jogadores j " +
                                         "WHERE c.mestre.id = :jogadorId OR j.id = :jogadorId", CampanhaModel.class)
                    .setParameter("jogadorId", jogador.getId())
                    .getResultList();

            Object[][] dados = new Object[campanhas.size()][3];

            FontIcon iconeLivro = FontIcon.of(FontAwesomeSolid.BOOK_OPEN, AppColors.ICON_SM, AppColors.PARCHMENT);
            FontIcon iconeCoroa = FontIcon.of(FontAwesomeSolid.CROWN, AppColors.ICON_SM, AppColors.PARCHMENT);
            FontIcon iconeUsuario = FontIcon.of(FontAwesomeSolid.USER_ALT, AppColors.ICON_SM, AppColors.PARCHMENT);

            for (int i = 0; i < campanhas.size(); i++) {
                CampanhaModel campanha = campanhas.get(i);

                dados[i][0] = iconeLivro;
                dados[i][1] = campanha;
                dados[i][2] = campanha.getMestre().getNome().equals(jogador.getNome()) ? iconeCoroa : iconeUsuario;
            }

            Dictionary<String, Object> dicionarioRetorno = new Hashtable<>();
            dicionarioRetorno.put("dados", dados);
            dicionarioRetorno.put("colunas", new Object[]{"", "", ""});
            return dicionarioRetorno;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Hashtable<>();
        }
    }

    public void recarregarTblCampanha() {
        Dictionary<String, Object> campanhas = listarCampanhasParaTabela();

        view.getTblCampanhas().setModel(
                new DefaultTableModel(
                        (Object[][]) campanhas.get("dados"),
                        (Object[]) campanhas.get("colunas")
                )
        );
    }

    public void tblCampanhaSelectRow(ListSelectionEvent e) {
        JTable tblCampanhas = view.getTblCampanhas();

        if (!e.getValueIsAdjusting()) {
            int linha = tblCampanhas.getSelectedRow();
            if (linha != -1) {
                int i = tblCampanhas.convertRowIndexToModel(linha);
                SessaoCampanha.getInstancia().setCampanhaLogada(
                    (CampanhaModel) tblCampanhas.getModel().getValueAt(i, 1)
                );
                view.getBtnEditar().setEnabled(true);
                view.getBtnExcluir().setEnabled(true);
                view.getBtnSelecionar().setEnabled(true);
            } else {
                SessaoCampanha.getInstancia().logout();
                view.getBtnEditar().setEnabled(false);
                view.getBtnExcluir().setEnabled(false);
                view.getBtnSelecionar().setEnabled(false);
            }
        }
    }
}

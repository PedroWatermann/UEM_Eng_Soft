package com.rpgpoo.Item.controller;

import com.rpgpoo.Enum.TipoItemEnum;
import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Item.model.ItemModel;
import com.rpgpoo.Item.view.ItemAddView;
import com.rpgpoo.Item.view.ItemView;
import com.rpgpoo.utils.AppColors;
import com.rpgpoo.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.kordamp.ikonli.fontawesome6.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ItemController {
    private final ItemView view;
    private final Gerenciador gerenciador;

    private List<ItemModel> itens = new ArrayList<>();

    public ItemController(ItemView view, Gerenciador gerenciador) {
        this.view = view;
        this.gerenciador = gerenciador;
    }

    public void init() {
        carregarItens(null, null);
    }

    private void carregarItens(String filtroNome, TipoItemEnum filtroTipo) {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            StringBuilder jpql = new StringBuilder("SELECT i FROM ItemModel i WHERE 1 = 1");

            if (filtroNome != null && !filtroNome.isBlank()) {
                jpql.append(" AND LOWER(i.nome) LIKE LOWER(:nome)");
            }
            if (filtroTipo != null) {
                jpql.append(" AND i.tipoItem = :tipo");
            }
            jpql.append(" ORDER BY i.nome");

            TypedQuery<ItemModel> query = em.createQuery(jpql.toString(), ItemModel.class);

            if (filtroNome != null && !filtroNome.isBlank()) {
                query.setParameter("nome", "%" + filtroNome.trim() + "%");
            }
            if (filtroTipo != null) {
                query.setParameter("tipo", filtroTipo);
            }

            itens = query.getResultList();
            atualizarTabela();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao carregar itens do banco de dados.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) view.getTblItens().getModel();
        model.setRowCount(0);

        for (ItemModel item : itens) {
            model.addRow(new Object[]{
                    FontIcon.of(FontAwesomeSolid.BOX, AppColors.ICON_SM, AppColors.PARCHMENT),
                    item.getNome(),
                    item.getTipoItem().toString(),
                    item.getRaridade().toString(),
                    item.getValor(),
                    item.getId()
            });
        }
    }

    public void btnBuscarClick() {
        String nome = view.getTxtBusca().getText();
        TipoItemEnum tipo = (TipoItemEnum) view.getCbxTipoItem().getSelectedItem();
        carregarItens(nome, tipo);
    }

    public void btnAdicionarClick() {
        JDialog dialog = new JDialog(gerenciador, "Narratus RPG - Adicionar Item", true);

        ItemAddView itemAddView = new ItemAddView(gerenciador, true, null,
                () -> {
                    dialog.dispose();
                    btnBuscarClick();
                },
                dialog::dispose
        );

        dialog.setContentPane(itemAddView);
        dialog.setMinimumSize(new Dimension(500, 0));
        dialog.pack();
        dialog.setLocationRelativeTo(gerenciador);
        dialog.setVisible(true);
    }

    public void btnEditarClick() {
        int linha = view.getTblItens().getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(view, "Selecione um item na tabela para editar.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) view.getTblItens().getModel();
        int idItem = (int) model.getValueAt(linha, 5);

        JDialog dialog = new JDialog(gerenciador, "Narratus RPG - Editar Item", true);

        ItemAddView itemAddView = new ItemAddView(gerenciador, false, idItem,
                () -> {
                    dialog.dispose();
                    btnBuscarClick();
                },
                dialog::dispose
        );

        dialog.setContentPane(itemAddView);
        dialog.setMinimumSize(new Dimension(500, 0));
        dialog.pack();
        dialog.setLocationRelativeTo(gerenciador);
        dialog.setVisible(true);
    }

    public void btnExcluirClick() {
        int linha = view.getTblItens().getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(view, "Selecione um item na tabela para excluir.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) view.getTblItens().getModel();
        int idItem = (int) model.getValueAt(linha, 5);
        String nomeItem = (String) model.getValueAt(linha, 1);

        int confirm = JOptionPane.showConfirmDialog(view,
                "Deseja realmente excluir o item \"" + nomeItem + "\"?",
                "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try (EntityManager em = JpaUtil.getEntityManager()) {
            em.getTransaction().begin();
            ItemModel item = em.find(ItemModel.class, idItem);
            if (item != null) {
                em.remove(item);
            }
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(view, "Item excluído.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            btnBuscarClick();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view,
                    "Não foi possível excluir: o item pode estar em uso no loot de algum monstro ou inventário.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
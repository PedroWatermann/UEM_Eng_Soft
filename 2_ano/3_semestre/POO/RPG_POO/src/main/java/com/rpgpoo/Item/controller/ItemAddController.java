package com.rpgpoo.Item.controller;

import com.rpgpoo.Enum.RaridadeEnum;
import com.rpgpoo.Enum.TipoItemEnum;
import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Item.model.ItemModel;
import com.rpgpoo.Item.view.ItemAddView;
import com.rpgpoo.utils.JpaUtil;
import com.rpgpoo.Arma.model.ArmaModel;
import com.rpgpoo.Dado.model.DadoModel;
import jakarta.persistence.EntityManager;

import javax.swing.*;

public class ItemAddController {
    private final Gerenciador gerenciador;
    private final ItemAddView view;
    private final boolean ehNovo;
    private final Integer idItem;
    private final Runnable sucesso;

    public ItemAddController(Gerenciador gerenciador, ItemAddView view, boolean ehNovo, Integer idItem, Runnable sucesso) {
        this.gerenciador = gerenciador;
        this.view = view;
        this.ehNovo = ehNovo;
        this.idItem = idItem;
        this.sucesso = sucesso;

        if (!ehNovo && idItem != null) {
            carregarItemParaEdicao();
        }
    }

    private void carregarItemParaEdicao() {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            ItemModel item = em.find(ItemModel.class, idItem);

            if (item == null) {
                JOptionPane.showMessageDialog(view, "Item não encontrado no banco de dados.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            view.getLblIdItem().setText(String.valueOf(item.getId()));
            view.getTxtNome().setText(item.getNome());
            view.getCbxTipo().setSelectedItem(item.getTipoItem());
            view.getCbxRaridade().setSelectedItem(item.getRaridade());
            view.getTxtEfeito().setText(String.valueOf(item.getValorEfeito()));
            view.getTxtValor().setText(String.valueOf(item.getValor()));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao carregar dados do item.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void btnSalvarClick() {
        String nome = view.getTxtNome().getText();
        TipoItemEnum tipo = (TipoItemEnum) view.getCbxTipo().getSelectedItem();
        RaridadeEnum raridade = (RaridadeEnum) view.getCbxRaridade().getSelectedItem();
        String efeitoStr = view.getTxtEfeito().getText();
        String valorStr = view.getTxtValor().getText();

        if (nome == null || nome.trim().isEmpty() || tipo == null || raridade == null ||
                efeitoStr == null || efeitoStr.trim().isEmpty() ||
                valorStr == null || valorStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Preencha todos os campos corretamente!");
            return;
        }

        double valorEfeito;
        double valor;
        try {
            valorEfeito = Double.parseDouble(efeitoStr.replace(",", "."));
            valor = Double.parseDouble(valorStr.replace(",", "."));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Valor do Efeito e Valor devem ser números!");
            return;
        }

        if (valorEfeito < 0 || valor < 0) {
            JOptionPane.showMessageDialog(view, "Nenhum valor pode ser negativo!");
            return;
        }

        try (EntityManager em = JpaUtil.getEntityManager()) {
            em.getTransaction().begin();
            ItemModel item;

            if (!ehNovo) {
                item = em.find(ItemModel.class, idItem);
                if (item == null) {
                    em.getTransaction().rollback();
                    JOptionPane.showMessageDialog(view, "Item não encontrado para edição.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                item.setNome(nome);
                item.setTipoItem(tipo);
                item.setRaridade(raridade);
                item.setValorEfeito(valorEfeito);
                item.setValor(valor);
            } else {
                if (tipo == TipoItemEnum.ARMA) {
                    DadoModel dadoDummy = em.createQuery("SELECT d FROM DadoModel d", DadoModel.class).getResultStream().findFirst().orElse(null);
                    item = new ArmaModel(nome, tipo, valorEfeito, raridade, valor, 0, 0, 0, dadoDummy);
                } else {
                    item = new ItemModel(nome, tipo, valorEfeito, raridade, valor);
                }
                em.persist(item);
            }

            em.getTransaction().commit();

            if (sucesso != null) sucesso.run();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao salvar item no banco de dados.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
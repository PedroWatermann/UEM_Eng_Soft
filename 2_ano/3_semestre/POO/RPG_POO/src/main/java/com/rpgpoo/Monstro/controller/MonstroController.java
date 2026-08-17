package com.rpgpoo.Monstro.controller;

import com.rpgpoo.Arma.model.ArmaModel;
import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Item.model.ItemModel;
import com.rpgpoo.Monstro.model.MonstroModel;
import com.rpgpoo.Monstro.view.MonstroEditView;
import com.rpgpoo.Monstro.view.MonstroView;
import com.rpgpoo.Raca.model.RacaModel;
import com.rpgpoo.utils.AppColors;
import com.rpgpoo.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import org.kordamp.ikonli.fontawesome6.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MonstroController {
    private final MonstroView view;
    private final Gerenciador gerenciador;

    private List<MonstroModel> monstros = new ArrayList<>();
    private List<RacaModel> racas = new ArrayList<>();
    private List<ArmaModel> armas = new ArrayList<>();

    public MonstroController(MonstroView view, Gerenciador gerenciador) {
        this.view = view;
        this.gerenciador = gerenciador;

        this.view.getBtnNovo().addActionListener(_ -> btnNovoClick());
        this.view.getBtnEditar().addActionListener(_ -> btnEditarClick());
        this.view.getBtnExcluir().addActionListener(_ -> btnExcluirClick());
        this.view.getBtnAdicionarLoot().addActionListener(_ -> btnAdicionarLootClick());
        this.view.getBtnRemoverLoot().addActionListener(_ -> btnRemoverLootClick());
        this.view.getCbxSelecionar().addActionListener(_ -> aoSelecionarMonstro());

        carregarDadosIniciais();
    }

    private void carregarDadosIniciais() {
        buscarRacasEArmas();
        buscarMonstrosDoBanco();
        atualizarView();
    }

    private void buscarRacasEArmas() {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            racas = em.createQuery("SELECT r FROM RacaModel r ORDER BY r.nome", RacaModel.class)
                    .getResultList();
            armas = em.createQuery("SELECT a FROM ArmaModel a ORDER BY a.nome", ArmaModel.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao carregar raças e armas do banco de dados.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarMonstrosDoBanco() {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            monstros = em.createQuery(
                            "SELECT DISTINCT m FROM MonstroModel m " +
                                    "LEFT JOIN FETCH m.raca " +
                                    "LEFT JOIN FETCH m.arma " +
                                    "ORDER BY m.nome",
                            MonstroModel.class
                    )
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao carregar monstros do banco de dados.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarView() {
        // Preenche Raças
        view.getCbxRaca().removeAllItems();
        for (RacaModel raca : racas) {
            view.getCbxRaca().addItem(raca.getNome());
        }

        // Preenche Armas
        view.getCbxArma().removeAllItems();
        for (ArmaModel arma : armas) {
            view.getCbxArma().addItem(arma.getNome());
        }

        atualizarComboSelecionar();
    }

    private boolean isUpdatingCombo = false;

    private void atualizarComboSelecionar() {
        isUpdatingCombo = true;
        view.getCbxSelecionar().removeAllItems();
        view.getCbxSelecionar().addItem("Novo Monstro...");
        for (MonstroModel m : monstros) {
            view.getCbxSelecionar().addItem(m.getNome());
        }
        isUpdatingCombo = false;
    }

    private void aoSelecionarMonstro() {
        if (isUpdatingCombo) return;

        int index = view.getCbxSelecionar().getSelectedIndex();
        if (index <= 0) {
            limparFormulario();
        } else {
            MonstroModel monstroSelecionado = monstros.get(index - 1);
            preencherFormulario(monstroSelecionado);
        }
    }

    private void limparFormulario() {
        view.getTxtNome().setText("");
        view.getTxtNivel().setText("");
        view.getTxtVida().setText("");
        view.getTxtDefesa().setText("");
        view.getTxtAtaque().setText("");
        view.getTxtDt().setText("");
        view.getCbxRaca().setSelectedIndex(0);
        view.getCbxArma().setSelectedIndex(0);

        DefaultTableModel model = (DefaultTableModel) view.getTblLoot().getModel();
        model.setRowCount(0);
    }

    private void preencherFormulario(MonstroModel m) {
        view.getTxtNome().setText(m.getNome());
        view.getTxtNivel().setText(String.valueOf(m.getNivel()));
        view.getTxtVida().setText(String.valueOf(m.getVida()));
        view.getTxtDefesa().setText(String.valueOf(m.getDefesa()));
        view.getTxtAtaque().setText(String.valueOf(m.getAtaque()));
        view.getTxtDt().setText(String.valueOf(m.getDt()));

        if (m.getRaca() != null) {
            view.getCbxRaca().setSelectedItem(m.getRaca().getNome());
        } else {
            view.getCbxRaca().setSelectedIndex(0);
        }

        if (m.getArma() != null) {
            view.getCbxArma().setSelectedItem(m.getArma().getNome());
        } else {
            view.getCbxArma().setSelectedIndex(0);
        }

        DefaultTableModel model = (DefaultTableModel) view.getTblLoot().getModel();
        model.setRowCount(0);
        if (m.getLoot() != null) {
            for (ItemModel item : m.getLoot()) {
                model.addRow(new Object[]{
                        FontIcon.of(FontAwesomeSolid.BOX, AppColors.ICON_SM, AppColors.PARCHMENT),
                        item.getNome(),
                        item.getRaridade().toString(),
                        item
                });
            }
        }
    }

    public void btnNovoClick() {
        JDialog dialog = new JDialog(gerenciador, "Narratus RPG - Novo Monstro", true);

        MonstroEditView monstroEditView = new MonstroEditView(this.gerenciador, true, null, () -> {
            dialog.dispose();
            recarregarCampos();
        });

        dialog.setContentPane(monstroEditView);
        dialog.setMinimumSize(new Dimension(800, 0));
        dialog.pack();
        dialog.setLocationRelativeTo(this.gerenciador);
        dialog.setVisible(true);
    }

    public void btnEditarClick() {
        int index = view.getCbxSelecionar().getSelectedIndex();
        if (index <= 0) {
            JOptionPane.showMessageDialog(view, "Selecione um monstro existente para editar.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Integer idMonstroSelecionado = monstros.get(index - 1).getId();

        JDialog dialog = new JDialog(gerenciador, "Narratus RPG - Editar Monstro", true);

        MonstroEditView monstroEditView = new MonstroEditView(this.gerenciador, false, idMonstroSelecionado, () -> {
            dialog.dispose();
            recarregarCampos();
        });

        dialog.setContentPane(monstroEditView);
        dialog.setMinimumSize(new Dimension(800, 0));
        dialog.pack();
        dialog.setLocationRelativeTo(this.gerenciador);
        dialog.setVisible(true);
    }

    private void recarregarCampos() {
        buscarMonstrosDoBanco();
        atualizarComboSelecionar();
        limparFormulario();
    }

    private int parseCampo(JTextField campo) throws NumberFormatException {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            throw new NumberFormatException("Campo vazio");
        }
        return Integer.parseInt(texto);
    }

    public void btnExcluirClick() {
        int index = view.getCbxSelecionar().getSelectedIndex();
        if (index <= 0) {
            JOptionPane.showMessageDialog(view, "Selecione um monstro existente para excluir.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, "Deseja realmente excluir este monstro?",
                "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        MonstroModel monstroParaExcluir = monstros.get(index - 1);

        try (EntityManager em = JpaUtil.getEntityManager()) {
            em.getTransaction().begin();
            MonstroModel gerenciado = em.find(MonstroModel.class, monstroParaExcluir.getId());
            if (gerenciado != null) {
                em.remove(gerenciado);
            }
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(view, "Monstro excluído.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            recarregarCampos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao excluir monstro do banco de dados.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void btnAdicionarLootClick() {
        Integer idMonstro = getIdMonstroSelecionado();
        if (idMonstro == null) {
            JOptionPane.showMessageDialog(view, "Selecione (ou salve) um monstro existente antes de adicionar itens ao loot.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (EntityManager em = JpaUtil.getEntityManager()) {
            List<ItemModel> todosItens = em
                    .createQuery("SELECT i FROM ItemModel i ORDER BY i.nome", ItemModel.class)
                    .getResultList();

            ItemModel itemEscolhido = abrirSeletorDeItem(todosItens);
            if (itemEscolhido == null) return;

            em.getTransaction().begin();
            MonstroModel monstro = em.find(MonstroModel.class, idMonstro);

            boolean jaTemItem = monstro.getLoot() != null &&
                    monstro.getLoot().stream().anyMatch(i -> i.getId() == itemEscolhido.getId());

            if (jaTemItem) {
                em.getTransaction().rollback();
                JOptionPane.showMessageDialog(view, "Esse item já está no loot deste monstro.",
                        "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ItemModel itemGerenciado = em.find(ItemModel.class, itemEscolhido.getId());
            monstro.adicionarItemLoot(itemGerenciado);

            em.getTransaction().commit();
            recarregarMonstroSelecionadoNaTela(idMonstro);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao adicionar item ao loot.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void btnRemoverLootClick() {
        Integer idMonstro = getIdMonstroSelecionado();
        if (idMonstro == null) {
            JOptionPane.showMessageDialog(view, "Selecione um monstro existente primeiro.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int linhaSelecionada = view.getTblLoot().getSelectedRow();
        if (linhaSelecionada < 0) {
            JOptionPane.showMessageDialog(view, "Selecione um item na tabela de loot para remover.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) view.getTblLoot().getModel();
        ItemModel itemParaRemover = (ItemModel) model.getValueAt(linhaSelecionada, 3);

        int confirm = JOptionPane.showConfirmDialog(view, "Remover este item do loot do monstro?",
                "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try (EntityManager em = JpaUtil.getEntityManager()) {
            em.getTransaction().begin();
            MonstroModel monstro = em.find(MonstroModel.class, idMonstro);

            ItemModel itemGerenciado = monstro.getLoot().stream()
                    .filter(i -> i.getId() == itemParaRemover.getId())
                    .findFirst()
                    .orElse(null);

            monstro.removerItemLoot(itemGerenciado);

            em.getTransaction().commit();
            recarregarMonstroSelecionadoNaTela(idMonstro);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao remover item do loot.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Integer getIdMonstroSelecionado() {
        int index = view.getCbxSelecionar().getSelectedIndex();
        if (index <= 0) return null;
        return monstros.get(index - 1).getId();
    }

    private void recarregarMonstroSelecionadoNaTela(int idMonstro) {
        buscarMonstrosDoBanco();
        MonstroModel atualizado = monstros.stream()
                .filter(m -> m.getId() == idMonstro)
                .findFirst()
                .orElse(null);
        atualizarComboSelecionar();
        if (atualizado != null) {
            view.getCbxSelecionar().setSelectedItem(atualizado.getNome());
            preencherFormulario(atualizado);
        }
    }

    private ItemModel abrirSeletorDeItem(List<ItemModel> itensDisponiveis) {
        if (itensDisponiveis.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "Não há itens cadastrados. Cadastre um item na tela de Itens primeiro.",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }

        String[] opcoes = itensDisponiveis.stream()
                .map(i -> i.getNome() + " (" + i.getRaridade() + ")")
                .toArray(String[]::new);

        String escolha = (String) JOptionPane.showInputDialog(
                view,
                "Selecione o item a adicionar:",
                "Adicionar Item ao Loot",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        if (escolha == null) return null;

        int index = java.util.Arrays.asList(opcoes).indexOf(escolha);
        return itensDisponiveis.get(index);
    }
}
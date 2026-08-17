package com.rpgpoo.Item.view;

import com.rpgpoo.Enum.TipoItemEnum;
import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Item.controller.ItemController;
import com.rpgpoo.utils.*;
import org.kordamp.ikonli.fontawesome6.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ItemEvent;

public class ItemView extends JPanel {
    JLabel lblTitulo = new JLabel();
    JSeparator separador = new JSeparator();

    JTextField txtBusca = new JTextField();
    JButton btnBuscar;
    JComboBox<TipoItemEnum> cbxTipoItem = new JComboBox<>();

    JTable tblItens = new JTable();
    JScrollPane scrItens = new JScrollPane();

    JButton btnAdicionar;
    JButton btnEditar;
    JButton btnExcluir;

    public ItemView(Gerenciador gerenciador) {
        ItemController controller = new ItemController(this, gerenciador);

        lblTitulo.setIcon(FontIcon.of(FontAwesomeSolid.LIST_ALT, AppColors.ICON_LG, AppColors.GOLD));
        lblTitulo.setText(" ITENS CADASTRADOS");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 18));
        lblTitulo.setForeground(AppColors.GOLD);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        separador.setForeground(AppColors.GOLD);
        separador.setBackground(AppColors.GOLD);

        txtBusca.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtBusca.setOpaque(false);
        txtBusca.setForeground(Color.WHITE);
        txtBusca.setBackground(AppColors.DARK3);
        txtBusca.setCaretColor(AppColors.PARCHMENT);
        txtBusca.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txtBusca.setPreferredSize(new Dimension(0, 28));
        txtBusca.addActionListener(_ -> controller.btnBuscarClick()); // Enter também busca

        btnBuscar = new JButtonCustom(
                "",
                JButtonCustom.Style.SECONDARY,
                FontIcon.of(FontAwesomeSolid.SEARCH, AppColors.ICON_MD1, AppColors.GOLD)
        );
        btnBuscar.setPreferredSize(new Dimension(28, 28));
        btnBuscar.addActionListener(_ -> controller.btnBuscarClick());

        cbxTipoItem.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cbxTipoItem.setForeground(Color.WHITE);
        cbxTipoItem.setBackground(AppColors.DARK);
        GenericUtils.estilizarComboBox(cbxTipoItem);
        cbxTipoItem.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                String texto = (value == null) ? "Todos os Tipos" : value.toString();
                return super.getListCellRendererComponent(list, texto, index, isSelected, cellHasFocus);
            }
        });
        cbxTipoItem.addItem(null); // "Todos os Tipos"
        for (TipoItemEnum tipo : TipoItemEnum.values()) {
            cbxTipoItem.addItem(tipo);
        }

        boolean[] carregandoFiltro = {true};
        cbxTipoItem.addItemListener(e -> {
            if (carregandoFiltro[0]) return;
            if (e.getStateChange() == ItemEvent.SELECTED) {
                controller.btnBuscarClick();
            }
        });
        carregandoFiltro[0] = false;
        GenericUtils.estilizarComboBox(cbxTipoItem);

        tblItens.setModel(new DefaultTableModel(new Object[][]{}, new Object[]{"", "Nome", "Tipo", "Raridade", "Valor (GP)", ""}) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        });
        tblItens.setDefaultRenderer(Object.class, new IconTextCellRender());
        tblItens.getColumnModel().getColumn(0).setMaxWidth(30);
        tblItens.removeColumn(tblItens.getColumnModel().getColumn(5)); // coluna oculta com o id do item
        tblItens.setShowVerticalLines(false);
        tblItens.setShowHorizontalLines(false);
        tblItens.setBackground(AppColors.DARK);
        tblItens.setForeground(AppColors.PARCHMENT);
        tblItens.setSelectionBackground(AppColors.CRIMSON);
        tblItens.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tblItens.setRowHeight(28);
        JTableHeader header = tblItens.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                lbl.setOpaque(true);
                lbl.setBackground(AppColors.DARK);
                lbl.setForeground(AppColors.GOLD);
                lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
                lbl.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, AppColors.GOLD));

                return lbl;
            }
        });

        scrItens.setViewportView(tblItens);
        scrItens.setPreferredSize(new Dimension(0, 150));
        scrItens.getViewport().setBackground(AppColors.DARK);
        scrItens.setBorder(BorderFactory.createLineBorder(AppColors.GOLD, 1));
        scrItens.getVerticalScrollBar().setUI(new DarkScrollBarUI());
        scrItens.getHorizontalScrollBar().setUI(new DarkScrollBarUI());
        scrItens.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        btnAdicionar = new JButtonCustom(
                "Adicionar Item",
                JButtonCustom.Style.PRIMARY,
                FontIcon.of(FontAwesomeSolid.PLUS, AppColors.ICON_SM, AppColors.PARCHMENT)
        );
        btnAdicionar.addActionListener(_ -> controller.btnAdicionarClick());

        btnEditar = new JButtonCustom(
                "Editar Item",
                JButtonCustom.Style.SECONDARY,
                FontIcon.of(FontAwesomeSolid.PENCIL_ALT, AppColors.ICON_SM, AppColors.PARCHMENT)
        );
        btnEditar.addActionListener(_ -> controller.btnEditarClick());

        btnExcluir = new JButtonCustom(
                "Excluir Item",
                JButtonCustom.Style.DANGER,
                FontIcon.of(FontAwesomeSolid.TRASH_ALT, AppColors.ICON_SM, AppColors.PARCHMENT)
        );
        btnExcluir.addActionListener(_ -> controller.btnExcluirClick());

        this.setLayout(new GridBagLayout());
        this.setBackground(AppColors.DARK);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 0),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        JPanel pnlBusca = new JPanel(new GridBagLayout());
        pnlBusca.setOpaque(false);

        GridBagConstraints gbcBusca = new GridBagConstraints();

        gbcBusca.gridx = 0;
        gbcBusca.weightx = 1.0;
        gbcBusca.fill = GridBagConstraints.HORIZONTAL;
        gbcBusca.insets = new Insets(0, 0, 0, 5);
        pnlBusca.add(txtBusca, gbcBusca);

        gbcBusca.gridx = 1;
        gbcBusca.weightx = 0;
        gbcBusca.fill = GridBagConstraints.NONE;
        gbcBusca.insets = new Insets(0, 0, 0, 5);
        pnlBusca.add(btnBuscar, gbcBusca);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 6, 0);
        this.add(lblTitulo, gbc);

        gbc.gridy = 1;
        this.add(separador, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 6, 5);
        this.add(pnlBusca, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 5, 6, 0);
        this.add(cbxTipoItem, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.gridwidth = 3;
        this.add(scrItens, gbc);

        gbc.weighty = 0.0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;

        gbc.insets = new Insets(0, 0, 0, 5);
        this.add(btnAdicionar, gbc);

        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.gridx = 1;
        this.add(btnEditar, gbc);

        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 2;
        this.add(btnExcluir, gbc);

        controller.init();
    }

    public JTextField getTxtBusca() { return txtBusca; }
    public JComboBox<TipoItemEnum> getCbxTipoItem() { return cbxTipoItem; }
    public JTable getTblItens() { return tblItens; }
}
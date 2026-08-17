package com.rpgpoo.Monstro.view;

import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Monstro.controller.MonstroController;
import com.rpgpoo.utils.*;
import org.kordamp.ikonli.fontawesome6.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class MonstroView extends JPanel {
    JLabel lblTitulo = new JLabel();

    JLabel lblSelecionar = new JLabel();
    JComboBox<String> cbxSelecionar = new JComboBox<>();

    JLabel lblNome = new JLabel();
    JTextField txtNome = new JTextField();

    JLabel lblNivel = new JLabel();
    JTextField txtNivel = new JTextField();

    JLabel lblVida = new JLabel();
    JTextField txtVida = new JTextField();

    JLabel lblDefesa = new JLabel();
    JTextField txtDefesa = new JTextField();

    JLabel lblAtaque = new JLabel();
    JTextField txtAtaque = new JTextField();

    JLabel lblDt = new JLabel();
    JTextField txtDt = new JTextField();

    JLabel lblRaca = new JLabel();
    JComboBox<String> cbxRaca = new JComboBox<>();

    JLabel lblArma = new JLabel();
    JComboBox<String> cbxArma = new JComboBox<>();

    JLabel lblLoot = new JLabel();
    JTable tblLoot = new JTable();
    JScrollPane scrLoot = new JScrollPane();

    JButton btnAdicionarLoot;
    JButton btnRemoverLoot;
    JButton btnNovo;
    JButton btnEditar;
    JButton btnExcluir;

    public MonstroView(Gerenciador gerenciador) {

        UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(AppColors.GOLD, 1));
        UIManager.put("List.background", AppColors.DARK);
        UIManager.put("List.foreground", AppColors.PARCHMENT);
        UIManager.put("List.selectionBackground", AppColors.CRIMSON);
        UIManager.put("List.selectionForeground", AppColors.PARCHMENT);

        lblTitulo.setIcon(FontIcon.of(FontAwesomeSolid.SKULL, AppColors.ICON_LG, AppColors.GOLD));
        lblTitulo.setText(" GERENCIAMENTO DE MONSTROS");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 18));
        lblTitulo.setForeground(AppColors.GOLD);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JSeparator sepTopo = new JSeparator();
        sepTopo.setForeground(AppColors.GOLD);
        sepTopo.setBackground(AppColors.GOLD);

        lblSelecionar.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSelecionar.setText("Selecionar Monstro");
        lblSelecionar.setForeground(AppColors.PARCHMENT);

        GenericUtils.estilizarComboBox(cbxSelecionar);
                // Itens agora serão carregados pelo MonstroController

        configurarLabelEField(lblNome, txtNome, "Nome");
        configurarLabelEField(lblNivel, txtNivel, "Nível");
        configurarCampoNumerico(txtNivel);
        configurarLabelEField(lblVida, txtVida, "Vida");
        configurarCampoNumerico(txtVida);
        configurarLabelEField(lblDefesa, txtDefesa, "Defesa");
        configurarCampoNumerico(txtDefesa);
        configurarLabelEField(lblAtaque, txtAtaque, "Ataque");
        configurarCampoNumerico(txtAtaque);
        configurarLabelEField(lblDt, txtDt, "DT");
        configurarCampoNumerico(txtDt);

        lblRaca.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblRaca.setText("Raça");
        lblRaca.setForeground(AppColors.PARCHMENT);
        GenericUtils.estilizarComboBox(cbxRaca);

        lblArma.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblArma.setText("Arma");
        lblArma.setForeground(AppColors.PARCHMENT);
        GenericUtils.estilizarComboBox(cbxArma);

        lblLoot.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblLoot.setText("Loot");
        lblLoot.setHorizontalAlignment(SwingConstants.LEFT);
        lblLoot.setForeground(AppColors.PARCHMENT);

        tblLoot.setModel(new DefaultTableModel(new Object[][]{}, new Object[]{"", "Item", "Raridade", ""}) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        });
        tblLoot.setDefaultRenderer(Object.class, new IconTextCellRender());
        tblLoot.getColumnModel().getColumn(0).setMaxWidth(30);
        tblLoot.removeColumn(tblLoot.getColumnModel().getColumn(3));
        tblLoot.setShowHorizontalLines(false);
        tblLoot.setBackground(AppColors.DARK);
        tblLoot.setForeground(AppColors.PARCHMENT);
        tblLoot.setSelectionBackground(AppColors.CRIMSON);
        tblLoot.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tblLoot.setRowHeight(28);
        JTableHeader header = tblLoot.getTableHeader();
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

        scrLoot.setViewportView(tblLoot);
        scrLoot.setPreferredSize(new Dimension(0, 150));
        scrLoot.getViewport().setBackground(AppColors.DARK);
        scrLoot.setBorder(BorderFactory.createLineBorder(AppColors.GOLD, 1));
        scrLoot.getVerticalScrollBar().setUI(new DarkScrollBarUI());
        scrLoot.getHorizontalScrollBar().setUI(new DarkScrollBarUI());
        scrLoot.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        btnAdicionarLoot = new JButtonCustom("Adicionar Item", JButtonCustom.Style.SECONDARY, FontIcon.of(FontAwesomeSolid.PLUS, AppColors.ICON_SM, AppColors.PARCHMENT));
        btnRemoverLoot = new JButtonCustom("Remover Item", JButtonCustom.Style.DANGER, FontIcon.of(FontAwesomeSolid.TRASH, AppColors.ICON_SM, AppColors.PARCHMENT));
        btnNovo = new JButtonCustom("Novo Monstro", FontIcon.of(FontAwesomeSolid.PLUS, 12, AppColors.PARCHMENT));
        btnEditar = new JButtonCustom("Editar Monstro", FontIcon.of(FontAwesomeSolid.PENCIL_ALT, 12, AppColors.PARCHMENT));
        btnExcluir = new JButtonCustom("Excluir Monstro", JButtonCustom.Style.DANGER, FontIcon.of(FontAwesomeSolid.TRASH, 12, AppColors.PARCHMENT));

        this.setLayout(new GridBagLayout());
        this.setBackground(AppColors.DARK);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 0),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 6, 0);

        // Titulo
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 4; gbc.weightx = 1.0;
        this.add(lblTitulo, gbc);

        gbc.gridy = 1;
        this.add(sepTopo, gbc);

        // Selecionar Monstro (label em cima, combo embaixo)
        gbc.gridy = 2; gbc.insets = new Insets(0, 0, 4, 0);
        this.add(lblSelecionar, gbc);
        gbc.gridy = 3; gbc.insets = new Insets(0, 0, 6, 0);
        this.add(cbxSelecionar, gbc);

        // Espaçador
        gbc.gridy = 4; gbc.insets = new Insets(10, 0, 10, 0);
        JSeparator sepDiv = new JSeparator();
        sepDiv.setForeground(AppColors.GOLD); sepDiv.setBackground(AppColors.GOLD);
        this.add(sepDiv, gbc);

        // Nome
        gbc.gridy = 5; gbc.insets = new Insets(0, 0, 4, 0);
        this.add(lblNome, gbc);
        gbc.gridy = 6; gbc.insets = new Insets(0, 0, 6, 0);
        this.add(txtNome, gbc);

        // Nivel / Vida
        gbc.gridy = 7; gbc.gridx = 0; gbc.gridwidth = 2; gbc.weightx = 0.5; gbc.insets = new Insets(0, 0, 4, 5);
        this.add(lblNivel, gbc);
        gbc.gridx = 2; gbc.insets = new Insets(0, 5, 4, 0);
        this.add(lblVida, gbc);

        gbc.gridy = 8; gbc.gridx = 0; gbc.insets = new Insets(0, 0, 6, 5);
        this.add(txtNivel, gbc);
        gbc.gridx = 2; gbc.insets = new Insets(0, 5, 6, 0);
        this.add(txtVida, gbc);

        // Defesa / Ataque
        gbc.gridy = 9; gbc.gridx = 0; gbc.insets = new Insets(0, 0, 4, 5);
        this.add(lblDefesa, gbc);
        gbc.gridx = 2; gbc.insets = new Insets(0, 5, 4, 0);
        this.add(lblAtaque, gbc);

        gbc.gridy = 10; gbc.gridx = 0; gbc.insets = new Insets(0, 0, 6, 5);
        this.add(txtDefesa, gbc);
        gbc.gridx = 2; gbc.insets = new Insets(0, 5, 6, 0);
        this.add(txtAtaque, gbc);

        // DT / Raça
        gbc.gridy = 11; gbc.gridx = 0; gbc.insets = new Insets(0, 0, 4, 5);
        this.add(lblDt, gbc);
        gbc.gridx = 2; gbc.insets = new Insets(0, 5, 4, 0);
        this.add(lblRaca, gbc);

        gbc.gridy = 12; gbc.gridx = 0; gbc.insets = new Insets(0, 0, 6, 5);
        this.add(txtDt, gbc);
        gbc.gridx = 2; gbc.insets = new Insets(0, 5, 6, 0);
        this.add(cbxRaca, gbc);

        // Arma
        gbc.gridy = 13; gbc.gridx = 0; gbc.gridwidth = 4; gbc.weightx = 1.0; gbc.insets = new Insets(0, 0, 4, 0);
        this.add(lblArma, gbc);
        gbc.gridy = 14; gbc.insets = new Insets(0, 0, 6, 0);
        this.add(cbxArma, gbc);

        // Painel de Loot
        JPanel pnlLootSection = new JPanel(new GridBagLayout());
        pnlLootSection.setLayout(new GridBagLayout());
        pnlLootSection.setBackground(AppColors.DARK);
        pnlLootSection.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(AppColors.GOLD, 1, 20),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        GridBagConstraints gbcLoot = new GridBagConstraints();
        gbcLoot.fill = GridBagConstraints.HORIZONTAL;

        // Cabeçalho "Loot (Itens)" com separadores nas laterais
        JPanel pnlLootHeader = new JPanel(new GridBagLayout());
        pnlLootHeader.setOpaque(false);
        GridBagConstraints g = new GridBagConstraints();
        g.gridy = 0; g.fill = GridBagConstraints.HORIZONTAL;

        JSeparator sepL1 = new JSeparator(); sepL1.setForeground(AppColors.GOLD); sepL1.setBackground(AppColors.GOLD);
        g.gridx = 0; g.gridwidth = 1; g.weightx = 1.0;
        pnlLootHeader.add(sepL1, g);

        g.gridx = 1; g.weightx = 0.0; g.fill = GridBagConstraints.NONE; g.anchor = GridBagConstraints.CENTER; g.insets = new Insets(0, 8, 0, 8);
        pnlLootHeader.add(lblLoot, g);

        JSeparator sepL2 = new JSeparator(); sepL2.setForeground(AppColors.GOLD); sepL2.setBackground(AppColors.GOLD);
        g.gridx = 2; g.weightx = 1.0; g.fill = GridBagConstraints.HORIZONTAL; g.insets = new Insets(0, 0, 0, 0);
        pnlLootHeader.add(sepL2, g);

        gbcLoot.gridy = 0; gbcLoot.gridx = 0; gbcLoot.gridwidth = 2; gbcLoot.weightx = 1.0;
        gbcLoot.insets = new Insets(8, 8, 8, 8);
        pnlLootSection.add(pnlLootHeader, gbcLoot);

        // Tabela Loot
        gbcLoot.gridy = 1; gbcLoot.weighty = 1.0; gbcLoot.fill = GridBagConstraints.BOTH;
        gbcLoot.insets = new Insets(0, 8, 8, 8);
        pnlLootSection.add(scrLoot, gbcLoot);

        // Botões Loot (dentro do painel)
        gbcLoot.gridy = 2; gbcLoot.weighty = 0; gbcLoot.fill = GridBagConstraints.HORIZONTAL;
        gbcLoot.gridwidth = 1; gbcLoot.weightx = 0.5; gbcLoot.insets = new Insets(0, 8, 8, 4);
        pnlLootSection.add(btnAdicionarLoot, gbcLoot);
        gbcLoot.gridx = 1; gbcLoot.insets = new Insets(0, 4, 8, 8);
        pnlLootSection.add(btnRemoverLoot, gbcLoot);

        // Adiciona o painel de Loot inteiro na tela principal
        gbc.gridy = 15; gbc.gridx = 0; gbc.gridwidth = 4; gbc.weightx = 1.0;
        gbc.weighty = 1.0; gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15, 0, 10, 0);
        this.add(pnlLootSection, gbc);

        // Botões Monstro (Novo / Editar / Excluir)
        JPanel pnlBotoesMonstro = new JPanel(new GridLayout(1, 3, 10, 0));
        pnlBotoesMonstro.setOpaque(false);
        pnlBotoesMonstro.add(btnNovo);
        pnlBotoesMonstro.add(btnEditar);
        pnlBotoesMonstro.add(btnExcluir);

        gbc.gridy = 16; gbc.gridx = 0; gbc.gridwidth = 4; gbc.weightx = 1.0;
        gbc.weighty = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        this.add(pnlBotoesMonstro, gbc);

        MonstroController controller = new MonstroController(this, gerenciador);
    }

    private void configurarLabelEField(JLabel lbl, JTextField txt, String texto) {
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lbl.setText(texto);
        lbl.setForeground(AppColors.PARCHMENT);

        txt.setMinimumSize(new Dimension(0, 28));
        txt.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txt.setOpaque(false);
        txt.setForeground(Color.WHITE);
        txt.setCaretColor(AppColors.PARCHMENT);
        txt.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
    }

    private void configurarCampoNumerico(JTextField txt) {
        ((javax.swing.text.AbstractDocument) txt.getDocument()).setDocumentFilter(new javax.swing.text.DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws javax.swing.text.BadLocationException {
                if (string == null) return;
                if (string.isEmpty() || string.matches("\\d+")) {
                    super.insertString(fb, offset, string, attr);
                }
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws javax.swing.text.BadLocationException {
                if (text == null) return;
                if (text.isEmpty() || text.matches("\\d+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    public JTextField getTxtNome() { return txtNome; }
    public JTextField getTxtNivel() { return txtNivel; }
    public JTextField getTxtVida() { return txtVida; }
    public JTextField getTxtDefesa() { return txtDefesa; }
    public JTextField getTxtAtaque() { return txtAtaque; }
    public JTextField getTxtDt() { return txtDt; }

    public JComboBox<String> getCbxSelecionar() { return cbxSelecionar; }
    public JComboBox<String> getCbxRaca() { return cbxRaca; }
    public JComboBox<String> getCbxArma() { return cbxArma; }
    public JTable getTblLoot() { return tblLoot; }

    public JButton getBtnAdicionarLoot() { return btnAdicionarLoot; }
    public JButton getBtnRemoverLoot() { return btnRemoverLoot; }
    public JButton getBtnNovo() { return btnNovo; }
    public JButton getBtnEditar() { return btnEditar; }
    public JButton getBtnExcluir() { return btnExcluir; }
}

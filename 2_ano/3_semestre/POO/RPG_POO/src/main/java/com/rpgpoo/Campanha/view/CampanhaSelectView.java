package com.rpgpoo.Campanha.view;

import com.rpgpoo.Campanha.controller.CampanhaSelectController;
import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.utils.AppColors;
import com.rpgpoo.utils.DarkScrollBarUI;
import com.rpgpoo.utils.IconTextCellRender;
import com.rpgpoo.utils.JButtonCustom;
import org.kordamp.ikonli.fontawesome6.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Dictionary;

public class CampanhaSelectView extends JPanel {
    JLabel lblTitulo = new JLabel();
    JTable tblCampanhas = new JTable();
    JScrollPane scrCampanhas = new JScrollPane();
    JButton btnNovo;
    JButton btnEditar;
    JButton btnExcluir;
    JButton btnSelecionar;

    public CampanhaSelectView(Gerenciador gerenciador) {
        this(gerenciador, false, null);
    }

    public CampanhaSelectView(Gerenciador gerenciador, boolean modoTroca, Runnable aoSelecionar) {
        CampanhaSelectController campanhaSelectController = new CampanhaSelectController(this, gerenciador, modoTroca, aoSelecionar);

        lblTitulo.setIcon(FontIcon.of(FontAwesomeSolid.MAP, AppColors.ICON_LG, AppColors.GOLD));
        lblTitulo.setText(" MINHAS CAMPANHAS");
        lblTitulo.setFont(new Font(Font.SERIF, Font.BOLD, 18));
        lblTitulo.setForeground(AppColors.GOLD);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JSeparator separador = new JSeparator();
        separador.setForeground(AppColors.GOLD);
        separador.setBackground(AppColors.GOLD);

        Dictionary<String, Object> dicionarioDados = campanhaSelectController.listarCampanhasParaTabela();
        tblCampanhas.setModel(
                new DefaultTableModel(
                        (Object[][]) dicionarioDados.get("dados"),
                        (Object[]) dicionarioDados.get("colunas")
                ) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                }
        );
        tblCampanhas.setDefaultRenderer(Object.class, new IconTextCellRender());
        if (tblCampanhas.getColumnModel().getColumnCount() > 0) {
            tblCampanhas.getColumnModel().getColumn(0).setMaxWidth(30);
            tblCampanhas.getColumnModel().getColumn(2).setMaxWidth(30);
        }
        tblCampanhas.setTableHeader(null);
        tblCampanhas.setShowVerticalLines(false);
        tblCampanhas.setBackground(AppColors.DARK);
        tblCampanhas.setForeground(AppColors.PARCHMENT);
        tblCampanhas.setSelectionBackground(AppColors.CRIMSON);
        tblCampanhas.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        tblCampanhas.setRowHeight(28);
        tblCampanhas.setAutoCreateColumnsFromModel(false);
        tblCampanhas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblCampanhas.getSelectionModel().addListSelectionListener(campanhaSelectController::tblCampanhaSelectRow);

        scrCampanhas.setViewportView(tblCampanhas);
        scrCampanhas.setPreferredSize(new Dimension(0, 150));
        scrCampanhas.getViewport().setBackground(AppColors.DARK);
        scrCampanhas.setBorder(BorderFactory.createLineBorder(AppColors.GOLD, 1));
        scrCampanhas.getVerticalScrollBar().setUI(new DarkScrollBarUI());
        scrCampanhas.getHorizontalScrollBar().setUI(new DarkScrollBarUI());
        scrCampanhas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        btnNovo = new JButtonCustom(
                "Criar",
                JButtonCustom.Style.PRIMARY,
                FontIcon.of(FontAwesomeSolid.PLUS, AppColors.ICON_SM, AppColors.PARCHMENT)
        );
        btnNovo.addActionListener(_ -> campanhaSelectController.btnNovoClick());

        btnEditar = new JButtonCustom(
                "Editar",
                JButtonCustom.Style.SECONDARY,
                FontIcon.of(FontAwesomeSolid.PENCIL_ALT, AppColors.ICON_SM, AppColors.PARCHMENT)
        );
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(_ -> campanhaSelectController.btnEditarClick());

        btnExcluir = new JButtonCustom(
                "Excluir",
                JButtonCustom.Style.DANGER,
                FontIcon.of(FontAwesomeSolid.TRASH_ALT, AppColors.ICON_SM, AppColors.PARCHMENT)
        );
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(_ -> campanhaSelectController.btnExcluirClick());

        btnSelecionar = new JButtonCustom(
                "Selecionar",
                FontIcon.of(FontAwesomeSolid.SIGN_IN_ALT, AppColors.ICON_SM, AppColors.PARCHMENT)
        );
        btnSelecionar.setEnabled(false);
        btnSelecionar.addActionListener(_ -> campanhaSelectController.btnSelecionarClick());

        this.setLayout(new GridBagLayout());
        this.setBackground(AppColors.DARK);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 2),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 6, 0);

        // Definições para lblTitulo
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        this.add(lblTitulo, gbc);

        // Definições para separador
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        this.add(separador, gbc);

        // Definições para tblCampanhas
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 10, 0);
        this.add(scrCampanhas, gbc);

        // Definições para botões lado a lado
        gbc.gridy = 3;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);

        // Definições para btnNovo
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(0, 0, 0, 5);
        this.add(btnNovo, gbc);

        // Definições para btnEditar
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 5, 0, 5);
        this.add(btnEditar, gbc);

        // Definições para btnExcluir
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 5, 0, 0);
        this.add(btnExcluir, gbc);

        // Definições para btnSelecionar
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 0, 0, 0);
        this.add(btnSelecionar, gbc);
    }

    public JTable getTblCampanhas() {
        return tblCampanhas;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnExcluir() {
        return btnExcluir;
    }

    public JButton getBtnSelecionar() {
        return btnSelecionar;
    }
}

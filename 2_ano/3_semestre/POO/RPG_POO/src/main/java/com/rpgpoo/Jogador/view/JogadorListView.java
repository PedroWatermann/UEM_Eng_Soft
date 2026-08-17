package com.rpgpoo.Jogador.view;

import com.rpgpoo.utils.AppColors;
import com.rpgpoo.utils.GenericUtils;
import com.rpgpoo.utils.JButtonCustom;
import com.rpgpoo.utils.DarkScrollBarUI;
import org.kordamp.ikonli.swing.FontIcon;
import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Jogador.model.JogadorModel;
import org.kordamp.ikonli.fontawesome6.FontAwesomeSolid;
import com.rpgpoo.Jogador.controller.JogadorListController;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class JogadorListView extends JPanel {
    JLabel lblJogador = new JLabel();
    JComboBox<JogadorModel> cbxJogadores = new JComboBox<>();

    JLabel lblPersonagem = new JLabel();
    JTable tblPersonagens = new JTable();
    JScrollPane scrPersonagens = new JScrollPane();

    JButton btnAdicionar;

    public JogadorListView(Gerenciador gerenciador, Runnable sucesso) {
        JogadorListController jogadorListController = new JogadorListController(this, gerenciador, sucesso);

        lblJogador.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblJogador.setText("Jogador");
        lblJogador.setHorizontalAlignment(SwingConstants.LEFT);
        lblJogador.setForeground(AppColors.PARCHMENT);

        cbxJogadores.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cbxJogadores.setOpaque(false);
        cbxJogadores.setForeground(Color.WHITE);
        cbxJogadores.setBackground(AppColors.DARK);
        GenericUtils.estilizarComboBox(cbxJogadores);
        jogadorListController.listarJogadores().forEach(cbxJogadores::addItem);
        cbxJogadores.setSelectedIndex(-1);
        cbxJogadores.addItemListener(e -> jogadorListController.cbxJogadoresItemSelect(cbxJogadores, e));

        lblPersonagem.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblPersonagem.setText("Personagem");
        lblPersonagem.setHorizontalAlignment(SwingConstants.LEFT);
        lblPersonagem.setForeground(AppColors.PARCHMENT);

        tblPersonagens.setModel(
                new DefaultTableModel(
                        null,
                        new Object[]{"Nome", "Nível", "Raça", "Classe"}
                ) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                }
        );
        tblPersonagens.setShowVerticalLines(false);
        tblPersonagens.setShowHorizontalLines(false);
        tblPersonagens.setBackground(AppColors.DARK);
        tblPersonagens.setForeground(AppColors.PARCHMENT);
        tblPersonagens.setSelectionBackground(AppColors.CRIMSON);
        tblPersonagens.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        tblPersonagens.setRowHeight(28);
        tblPersonagens.setEnabled(false);
        JTableHeader headerPersonagens = tblPersonagens.getTableHeader();
        headerPersonagens.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                lbl.setOpaque(true);
                lbl.setBackground(AppColors.DARK);
                lbl.setForeground(AppColors.GOLD);
                lbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
                lbl.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, AppColors.GOLD));

                return lbl;
            }
        });

        scrPersonagens.setViewportView(tblPersonagens);
        scrPersonagens.setPreferredSize(new Dimension(0, 150));
        scrPersonagens.getViewport().setBackground(AppColors.DARK);
        scrPersonagens.setBorder(BorderFactory.createLineBorder(AppColors.GOLD, 1));
        scrPersonagens.getVerticalScrollBar().setUI(new DarkScrollBarUI());
        scrPersonagens.getHorizontalScrollBar().setUI(new DarkScrollBarUI());
        scrPersonagens.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        btnAdicionar = new JButtonCustom(
                "Adicionar",
                JButtonCustom.Style.SECONDARY,
                FontIcon.of(FontAwesomeSolid.USER_PLUS, AppColors.ICON_SM, AppColors.PARCHMENT)
        );
        btnAdicionar.addActionListener(_ -> jogadorListController.btnAdicionarClick());

        this.setLayout(new GridBagLayout());
        this.setBackground(AppColors.DARK);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 0),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblJogador, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 5, 0);
        this.add(cbxJogadores, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 0, 0);
        this.add(lblPersonagem, gbc);

        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 5, 0);
        this.add(scrPersonagens, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(5, 0, 0, 0);
        this.add(btnAdicionar, gbc);
    }

    public JComboBox<JogadorModel> getCbxJogadores() {
        return cbxJogadores;
    }

    public JTable getTblPersonagens() {
        return tblPersonagens;
    }

    public JButton getBtnAdicionar() {
        return btnAdicionar;
    }
}

package com.rpgpoo.Item.view;

import com.rpgpoo.Enum.RaridadeEnum;
import com.rpgpoo.Enum.TipoItemEnum;
import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Item.controller.ItemAddController;
import com.rpgpoo.utils.AppColors;
import com.rpgpoo.utils.GenericUtils;
import com.rpgpoo.utils.JButtonCustom;
import org.kordamp.ikonli.fontawesome6.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;

public class ItemAddView extends JPanel {
    JLabel lblIdItem = new JLabel();

    JLabel lblTitulo = new JLabel();
    JSeparator sepTopo = new JSeparator();

    JLabel lblNome = new JLabel();
    JTextField txtNome = new JTextField();

    JLabel lblTipo = new JLabel();
    JComboBox<TipoItemEnum> cbxTipo = new JComboBox<>();

    JLabel lblRaridade = new JLabel();
    JComboBox<RaridadeEnum> cbxRaridade = new JComboBox<>();

    JLabel lblEfeito = new JLabel();
    JTextField txtEfeito = new JTextField();

    JLabel lblValor = new JLabel();
    JTextField txtValor = new JTextField();

    JSeparator sepBaixo = new JSeparator();

    JButton btnSalvar;
    JButton btnCancelar;

    public ItemAddView(Gerenciador gerenciador, boolean ehNovo, Integer idItem, Runnable sucesso, Runnable cancelar) {
        ItemAddController controller = new ItemAddController(gerenciador, this, ehNovo, idItem, sucesso);

        lblIdItem.setVisible(false);

        lblTitulo.setIcon(FontIcon.of(FontAwesomeSolid.BOX, AppColors.ICON_LG, AppColors.GOLD));
        lblTitulo.setText(ehNovo ? " ADICIONAR ITEM" : " EDITAR ITEM");
        lblTitulo.setFont(new Font(Font.SERIF, Font.BOLD, 18));
        lblTitulo.setForeground(AppColors.GOLD);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        sepTopo.setForeground(AppColors.GOLD);
        sepTopo.setBackground(AppColors.GOLD);

        lblNome.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        lblNome.setText("Nome");
        lblNome.setHorizontalAlignment(SwingConstants.LEFT);
        lblNome.setForeground(AppColors.PARCHMENT);

        txtNome.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        txtNome.setOpaque(false);
        txtNome.setForeground(Color.WHITE);
        txtNome.setCaretColor(AppColors.PARCHMENT);
        txtNome.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txtNome.setPreferredSize(new Dimension(0, 28));

        lblTipo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        lblTipo.setText("Tipo");
        lblTipo.setHorizontalAlignment(SwingConstants.LEFT);
        lblTipo.setForeground(AppColors.PARCHMENT);

        cbxTipo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cbxTipo.setForeground(Color.WHITE);
        cbxTipo.setBackground(AppColors.DARK);
        GenericUtils.estilizarComboBox(cbxTipo);
        for (TipoItemEnum tipo : TipoItemEnum.values()) {
            cbxTipo.addItem(tipo);
        }

        lblRaridade.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        lblRaridade.setText("Raridade");
        lblRaridade.setHorizontalAlignment(SwingConstants.LEFT);
        lblRaridade.setForeground(AppColors.PARCHMENT);

        cbxRaridade.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cbxRaridade.setForeground(Color.WHITE);
        cbxRaridade.setBackground(AppColors.DARK);
        GenericUtils.estilizarComboBox(cbxRaridade);
        for (RaridadeEnum raridade : RaridadeEnum.values()) {
            cbxRaridade.addItem(raridade);
        }

        lblEfeito.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        lblEfeito.setText("Valor do Efeito");
        lblEfeito.setHorizontalAlignment(SwingConstants.LEFT);
        lblEfeito.setForeground(AppColors.PARCHMENT);

        txtEfeito.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        txtEfeito.setOpaque(false);
        txtEfeito.setForeground(Color.WHITE);
        txtEfeito.setCaretColor(AppColors.PARCHMENT);
        txtEfeito.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txtEfeito.setPreferredSize(new Dimension(0, 28));

        lblValor.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        lblValor.setText("Valor (GP)");
        lblValor.setHorizontalAlignment(SwingConstants.LEFT);
        lblValor.setForeground(AppColors.PARCHMENT);

        txtValor.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        txtValor.setOpaque(false);
        txtValor.setForeground(Color.WHITE);
        txtValor.setCaretColor(AppColors.PARCHMENT);
        txtValor.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txtValor.setPreferredSize(new Dimension(0, 28));

        sepBaixo.setForeground(AppColors.GOLD);
        sepBaixo.setBackground(AppColors.GOLD);

        btnSalvar = new JButtonCustom(
                "Salvar Item",
                JButtonCustom.Style.PRIMARY,
                FontIcon.of(FontAwesomeSolid.SAVE, AppColors.ICON_SM, AppColors.GOLD)
        );
        btnSalvar.addActionListener(_ -> controller.btnSalvarClick());

        btnCancelar = new JButtonCustom(
                "Cancelar",
                JButtonCustom.Style.DANGER,
                FontIcon.of(FontAwesomeSolid.TIMES, AppColors.ICON_SM, AppColors.GOLD)
        );
        btnCancelar.addActionListener(_ -> cancelar.run());

        this.setLayout(new GridBagLayout());
        this.setBackground(AppColors.DARK);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 0),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblTitulo, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        this.add(sepTopo, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 0, 0);
        this.add(lblNome, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 5, 0);
        this.add(txtNome, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(5, 0, 0, 5);
        this.add(lblTipo, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(5, 5, 0, 0);
        this.add(lblRaridade, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 5, 5);
        this.add(cbxTipo, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 5, 5, 0);
        this.add(cbxRaridade, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 0, 5);
        this.add(lblEfeito, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(5, 5, 0, 0);
        this.add(lblValor, gbc);

        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 5, 5);
        this.add(txtEfeito, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 5, 5, 0);
        this.add(txtValor, gbc);

        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 0, 5, 0);
        this.add(sepBaixo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 9;
        gbc.insets = new Insets(5, 0, 0, 5);
        this.add(btnSalvar, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(5, 5, 0, 0);
        this.add(btnCancelar, gbc);
    }

    public JLabel getLblIdItem() { return lblIdItem; }
    public JTextField getTxtNome() { return txtNome; }
    public JComboBox<TipoItemEnum> getCbxTipo() { return cbxTipo; }
    public JComboBox<RaridadeEnum> getCbxRaridade() { return cbxRaridade; }
    public JTextField getTxtEfeito() { return txtEfeito; }
    public JTextField getTxtValor() { return txtValor; }
    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
package com.rpgpoo.Monstro.view;

import com.rpgpoo.Arma.model.ArmaModel;
import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Monstro.controller.MonstroEditController;
import com.rpgpoo.Raca.model.RacaModel;
import com.rpgpoo.utils.AppColors;
import com.rpgpoo.utils.GenericUtils;
import com.rpgpoo.utils.JButtonCustom;
import org.kordamp.ikonli.fontawesome6.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;

public class MonstroEditView extends JPanel {
    JLabel lblIdMonstro = new JLabel();

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
    JComboBox<RacaModel> cbxRaca = new JComboBox<>();

    JLabel lblArma = new JLabel();
    JComboBox<ArmaModel> cbxArma = new JComboBox<>();

    JButton btnSalvar;

    public MonstroEditView(Gerenciador gerenciador, boolean ehNovo, Integer idMonstro, Runnable sucesso) {
        MonstroEditController monstroEditController =
                new MonstroEditController(gerenciador, this, ehNovo, idMonstro, sucesso);

        lblIdMonstro.setVisible(false);

        configurarLabelEField(lblNome, txtNome, false, "Nome");
        configurarLabelEField(lblNivel, txtNivel, true, "Nível");
        configurarLabelEField(lblVida, txtVida, true, "Vida");
        configurarLabelEField(lblDefesa, txtDefesa, true, "Defesa");
        configurarLabelEField(lblAtaque, txtAtaque, true, "Ataque");
        configurarLabelEField(lblDt, txtDt, true, "DT");

        lblRaca.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblRaca.setText("Raça");
        lblRaca.setForeground(AppColors.PARCHMENT);
        GenericUtils.estilizarComboBox(cbxRaca);

        lblArma.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblArma.setText("Arma");
        lblArma.setForeground(AppColors.PARCHMENT);

        cbxArma.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cbxArma.setOpaque(false);
        cbxArma.setForeground(Color.WHITE);
        cbxArma.setBackground(AppColors.DARK);
        GenericUtils.estilizarComboBox(cbxArma);

        btnSalvar = new JButtonCustom(
                "Salvar Monstro",
                FontIcon.of(FontAwesomeSolid.SAVE, 12, AppColors.PARCHMENT)
        );
        btnSalvar.addActionListener(_ -> monstroEditController.btnSalvarMonstro());

        this.setLayout(new GridBagLayout());
        this.setBackground(AppColors.DARK);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 2),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblNome, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 5, 0);
        this.add(txtNome, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(5, 0, 0, 5);
        this.add(lblNivel, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(5, 5, 0, 0);
        this.add(lblVida, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 5, 5);
        this.add(txtNivel, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 5, 5, 0);
        this.add(txtVida, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 0, 5);
        this.add(lblDefesa, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(5, 5, 0, 0);
        this.add(lblAtaque, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 5, 5);
        this.add(txtDefesa, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 5, 5, 0);
        this.add(txtAtaque, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 0, 0, 5);
        this.add(lblDt, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(5, 5, 0, 0);
        this.add(lblRaca, gbc);

        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 5, 5);
        this.add(txtDt, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 5, 5, 0);
        this.add(cbxRaca, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 0, 0, 0);
        this.add(lblArma, gbc);

        gbc.gridy = 9;
        gbc.insets = new Insets(0, 0, 5, 0);
        this.add(cbxArma, gbc);

        gbc.gridy = 10;
        gbc.insets = new Insets(5, 0, 0, 0);
        this.add(btnSalvar, gbc);
    }

    private void configurarLabelEField(JLabel lbl, JTextField txt, boolean ehNumerico, String texto) {
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lbl.setText(texto);
        lbl.setForeground(AppColors.PARCHMENT);

        txt.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txt.setOpaque(false);
        txt.setForeground(Color.WHITE);
        txt.setCaretColor(AppColors.PARCHMENT);
        txt.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txt.setMinimumSize(new Dimension(0, 28));

        if (ehNumerico) {
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
    }

    public JLabel getLblIdMonstro() { return lblIdMonstro; }
    public JTextField getTxtNome() { return txtNome; }
    public JTextField getTxtNivel() { return txtNivel; }
    public JTextField getTxtVida() { return txtVida; }
    public JTextField getTxtDefesa() { return txtDefesa; }
    public JTextField getTxtAtaque() { return txtAtaque; }
    public JTextField getTxtDt() { return txtDt; }
    public JComboBox<RacaModel> getCbxRaca() { return cbxRaca; }
    public JComboBox<ArmaModel> getCbxArma() { return cbxArma; }
    public JButton getBtnSalvar() { return btnSalvar; }
}
package com.rpgpoo.Login.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Login.controller.LoginController;
import com.rpgpoo.utils.AppColors;
import com.rpgpoo.utils.JButtonCustom;
import org.kordamp.ikonli.swing.FontIcon;
import org.kordamp.ikonli.fontawesome6.FontAwesomeSolid;

public class LoginView extends JPanel {
    JLabel lblTitulo = new JLabel();
    JLabel lblUsuario = new JLabel();
    JTextField txtUsuario = new JTextField();
    JLabel lblSenha = new JLabel();
    JPasswordField txtSenha = new JPasswordField();
    JButton btnEntrar;
    JLabel lblRegistrar = new JLabel();

    public LoginView(Gerenciador gerenciador) {
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 18));
        lblTitulo.setIcon(FontIcon.of(FontAwesomeSolid.USER_SHIELD, AppColors.ICON_LG, AppColors.GOLD));
        lblTitulo.setText(" ACESSO AO SISTEMA");
        lblTitulo.setForeground(AppColors.GOLD);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JSeparator sepTopo = new JSeparator();
        sepTopo.setForeground(AppColors.GOLD);
        sepTopo.setBackground(AppColors.GOLD);

        lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblUsuario.setText("Usuário:");
        lblUsuario.setForeground(AppColors.PARCHMENT);

        txtUsuario.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtUsuario.setOpaque(false);
        txtUsuario.setForeground(Color.WHITE);
        txtUsuario.setCaretColor(AppColors.PARCHMENT);
        txtUsuario.setBorder(BorderFactory.createLineBorder(AppColors.PARCHMENT));
        txtUsuario.setPreferredSize(new Dimension(220, 28));

        lblSenha.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSenha.setText("Senha:");
        lblSenha.setForeground(AppColors.PARCHMENT);

        txtSenha.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtSenha.setOpaque(false);
        txtSenha.setForeground(Color.WHITE);
        txtSenha.setCaretColor(AppColors.PARCHMENT);
        txtSenha.setBorder(BorderFactory.createLineBorder(AppColors.PARCHMENT));
        txtSenha.setPreferredSize(new Dimension(220, 28));

        JSeparator sepBaixo = new JSeparator();
        sepBaixo.setForeground(AppColors.GOLD);
        sepBaixo.setBackground(AppColors.GOLD);

        lblRegistrar.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblRegistrar.setText("Registrar-se");
        lblRegistrar.setForeground(AppColors.PARCHMENT);

        btnEntrar = new JButtonCustom(
                "Entrar",
                FontIcon.of(FontAwesomeSolid.SIGN_IN_ALT, AppColors.ICON_SM, AppColors.PARCHMENT)
        );

        this.setLayout(new GridBagLayout());
        this.setBackground(AppColors.DARK);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 2),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(lblTitulo, gbc);

        gbc.gridy = 1;
        this.add(sepTopo, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(lblUsuario, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        this.add(txtUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        this.add(lblSenha, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        this.add(txtSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        this.add(sepBaixo, gbc);

        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(btnEntrar, gbc);

        gbc.gridy = 6;
        this.add(lblRegistrar, gbc);

        LoginController loginController = new LoginController(this, gerenciador);
        btnEntrar.addActionListener(_ -> loginController.btnEntrarClick());
        lblRegistrar.addMouseListener(new MouseAdapter() {
            final Font original = lblRegistrar.getFont();
            final Map<TextAttribute, Object> attributes = new HashMap<>(original.getAttributes());

            @Override
            public void mouseEntered(MouseEvent e) {
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                lblRegistrar.setFont(original.deriveFont(attributes));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                attributes.put(TextAttribute.UNDERLINE, -1);
                lblRegistrar.setFont(original.deriveFont(attributes));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                loginController.lblRegistrarClick();
            }
        });
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JPasswordField getTxtSenha() {
        return txtSenha;
    }
}

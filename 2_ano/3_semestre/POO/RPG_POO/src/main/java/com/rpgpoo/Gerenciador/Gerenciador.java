package com.rpgpoo.Gerenciador;

import com.rpgpoo.Login.view.LoginView;

import javax.swing.*;
import java.awt.*;

public class Gerenciador extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);

    public Gerenciador() {
        cardPanel.add(new LoginView(this), "login");
        this.setContentPane(cardPanel);
        this.setTitle("Narratus RPG - Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void addPainel(Component painel, String nomeTela) {
        cardPanel.add(painel, nomeTela);
    }

    public void navegarPara(String tela, boolean modal, String titulo, boolean redimensionavel) {
        if (modal) {
            this.pack();
            this.setLocationRelativeTo(this);
        } else {
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        titulo = titulo.trim().isBlank() ?
                this.getTitle().split("-")[0].trim() :
                this.getTitle().split("-")[0].trim() + " - " + titulo;
        this.setTitle(titulo);
        this.setResizable(redimensionavel);
        cardLayout.show(cardPanel, tela);
    }
}

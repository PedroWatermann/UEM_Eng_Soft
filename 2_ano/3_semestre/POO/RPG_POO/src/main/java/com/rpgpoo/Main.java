package com.rpgpoo;

import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.utils.JpaUtil;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Main {

    static void main() {
        SwingUtilities.invokeLater(Main::iniciar);
    }

    private static void iniciar() {
        JFrame splash = criarSplash();
        splash.setVisible(true);

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try (EntityManager _ = JpaUtil.getEntityManager()) {
                    System.out.println("Conexão estabelecida com sucesso!");
                } catch (Exception e) {
                    System.err.println("Erro ao conectar: " + e.getMessage());
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void done() {
                splash.dispose();
                new Gerenciador();
            }
        };

        worker.execute();
    }

    private static JFrame criarSplash() {
        JFrame splash = new JFrame();
        splash.setUndecorated(true);
        splash.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        URL url = Main.class.getResource("/logo.png");
        assert url != null;
        ImageIcon iconOriginal = new ImageIcon(url);

        int novoTamanho = 350;
        Image imagemEscalada = iconOriginal.getImage().getScaledInstance(
                novoTamanho, novoTamanho, Image.SCALE_SMOOTH
        );

        JLabel lblImagem = new JLabel(new ImageIcon(imagemEscalada));

        splash.getContentPane().add(lblImagem);
        splash.pack();
        splash.setLocationRelativeTo(null);
        splash.setResizable(false);

        return splash;
    }
}
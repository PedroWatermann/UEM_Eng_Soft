package AulasSwing;

import javax.swing.*;
import java.awt.*;

public class ExemploDialogo {
    public ExemploDialogo() {
        JFrame janela = new JFrame();
        janela.setSize(400,400);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);

        JDialog dialog = new JDialog(janela);
        dialog.setSize(200,200);
        dialog.setLocationRelativeTo(janela);
        dialog.setLayout(new FlowLayout());

        JButton botao = new JButton("Fechar modal");
        botao.addActionListener(e -> dialog.dispose());

        JButton botaoApp = new JButton("Fechar aplicação");
        botaoApp.addActionListener(e -> janela.dispose());

        dialog.add(new JLabel("Janela de diálogo modal"));
        dialog.add(botao);
        dialog.add(botaoApp);
        dialog.setVisible(true);
    }
}

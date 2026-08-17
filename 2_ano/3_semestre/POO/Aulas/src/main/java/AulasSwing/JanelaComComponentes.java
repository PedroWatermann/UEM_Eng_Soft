package AulasSwing;

import javax.swing.*;
import java.awt.*;

public class JanelaComComponentes extends JFrame {
    public JanelaComComponentes() {
        setTitle("Janela de Componentes");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel rotulo = new JLabel("Nome: ");
        JTextField campo = new JTextField(20);
        JButton botao = new JButton("OK");

        add(rotulo);
        add(campo);
        add(botao);
    }
}

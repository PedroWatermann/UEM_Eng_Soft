package AulasSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClasseInterna extends JFrame {
    private JButton btn = new JButton("Clique");
    private JLabel lbl = new JLabel("...");

    public ClasseInterna() {
        setLayout(new FlowLayout());
        add(btn);
        add(lbl);
        btn.addActionListener(new BotaoClick());
    }

    private class BotaoClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            lbl.setText("Clicado!");
        }
    }
}

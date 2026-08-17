package AulasSwing;

import javax.swing.*;
import java.awt.*;

public class ClasseAnonima extends JFrame {
    private JButton btn = new JButton("Clique");
    private JLabel lbl = new JLabel("...");

    public ClasseAnonima() {
        setLayout(new FlowLayout());
        add(btn);
        add(lbl);
        btn.addActionListener(e -> lbl.setText("Clicado!"));
    }
}

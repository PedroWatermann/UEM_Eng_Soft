package AulasSwing.MVC.View;

import javax.swing.*;
import java.awt.*;

public class PessoaView extends JFrame {
    public JTextField txtNome = new JTextField(12);
    public JTextField txtIdade = new JTextField(4);
    public JButton btnAdicionar = new JButton("Adicionar");
    public JTextArea txtLista = new JTextArea(5, 20);

    public PessoaView() {
        setTitle("Cadastro de Pessoa");
        setLayout(new BorderLayout(5, 5));
        JPanel topo = new JPanel(new FlowLayout());
        topo.add(new JLabel("Nome:"));
        topo.add(txtNome);
        topo.add(new JLabel("Idade:"));
        topo.add(txtIdade);
        topo.add(btnAdicionar);
        add(topo, BorderLayout.NORTH);

        txtLista.setEditable(false);
        add(new JScrollPane(txtLista), BorderLayout.CENTER);setSize(420, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

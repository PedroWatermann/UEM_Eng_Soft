package AulasSwing;

import javax.swing.*;
import java.awt.*;

public class JanelaComponentes {

    public JanelaComponentes(){
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());

        //region Os componentes (miolo da aplicação) vêm aqui
        jLabelJTextField(frame);
        jButtonJTextBox(frame);
        jRadioButtonButtonGroup(frame);
        jComboBoxJList(frame);
        jPanel(frame);
        //endregion

        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void jLabelJTextField(JFrame frame) {
        JLabel lblName = new JLabel("Nome: ");
        JTextField txtName = new JTextField(15);

        JLabel lblSenha = new JLabel("Senha: ");
        JPasswordField pwdSenha = new JPasswordField(10);

        JButton botao = new JButton("OK");
        botao.addActionListener(e -> {
            System.out.println("Nome: " + txtName.getText());
            System.out.println("Senha: " + new String(pwdSenha.getPassword()));
        });

        frame.add(lblName);
        frame.add(txtName);
        frame.add(lblSenha);
        frame.add(pwdSenha);
        frame.add(botao);
    }

    private void jButtonJTextBox(JFrame frame) {
        JCheckBox chk1 = new JCheckBox("Java");
        JCheckBox chk2 = new JCheckBox("Python");
        JCheckBox chk3 = new JCheckBox("C++", true);
        JButton btn = new JButton("Verificar");

        btn.addActionListener(e -> {
            System.out.println("Java=" + chk1.isSelected());
            System.out.println("Python=" + chk2.isSelected());
            System.out.println("C++=" + chk3.isSelected());
        });

        frame.add(chk1);
        frame.add(chk2);
        frame.add(chk3);
        frame.add(btn);
    }

    private void jRadioButtonButtonGroup(JFrame frame) {
        JRadioButton rb1 = new JRadioButton("Masculino");
        JRadioButton rb2 = new JRadioButton("Feminino");

        // ButtonGroup garante seleção exclusiva
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rb1);
        grupo.add(rb2);

        JButton btn = new JButton("Confirmar");
        btn.addActionListener(e -> {
            if (rb1.isSelected()) System.out.println("Masculino");
            if (rb2.isSelected()) System.out.println("Feminino");
        });

        frame.add(rb1);
        frame.add(rb2);
        frame.add(btn);
    }

    private void jComboBoxJList(JFrame frame) {
        String[] cidades = {"Maringá", "Londrina", "Curitiba"};
        JComboBox<String> cb = new JComboBox<>(cidades);

        String[] idiomas = {"Português", "Inglês", "Espanhol"};
        JList<String> lista = new JList<>(idiomas);

        JButton btn = new JButton("Mostrar");
        btn.addActionListener(e -> {
            System.out.println("Cidade: " + cb.getSelectedItem());
            System.out.println("Idiomas: " + lista.getSelectedValuesList());
        });

        frame.add(cb);
        frame.add(new JScrollPane(lista));
        frame.add(btn);
    }

    private void jPanel(JFrame frame) {
        JPanel painelDados = new JPanel();
        painelDados.setLayout(new GridLayout(2, 2));
        painelDados.add(new JLabel("Nome:"));
        painelDados.add(new JTextField(15));
        painelDados.add(new JLabel("E-mail:"));
        painelDados.add(new JTextField(15));

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(new JButton("Salvar"));
        painelBotoes.add(new JButton("Cancelar"));

        // Adicionando painéis ao JFrame
        frame.setLayout(new BorderLayout());
        frame.add(painelDados, BorderLayout.CENTER);
        frame.add(painelBotoes, BorderLayout.SOUTH);
    }
}

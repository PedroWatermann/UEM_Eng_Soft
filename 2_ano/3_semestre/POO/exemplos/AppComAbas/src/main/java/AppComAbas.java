import javax.swing.*;
import java.awt.*;

public class AppComAbas extends JFrame {

    public AppComAbas() {
        setTitle("Exemplo com JTabbedPane");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JFrame frame = new JFrame();

        // Cria o painel de abas
        JTabbedPane abas = new JTabbedPane();

        // Aba 1 - formulário simples
        JPanel aba1 = new JPanel(new GridLayout(3, 2, 10, 10));
        aba1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        aba1.add(new JLabel("Nome:"));
        aba1.add(new JTextField());
        aba1.add(new JLabel("Email:"));
        aba1.add(new JTextField());
        aba1.add(new JLabel(""));
        aba1.add(new JButton("Salvar"));

        // Aba 2 - lista
        JPanel aba2 = new JPanel(new BorderLayout());
        String[] itens = {"Item A", "Item B", "Item C"};
        aba2.add(new JScrollPane(new JList<>(itens)), BorderLayout.CENTER);

        // Aba 3 - texto
        JPanel aba3 = new JPanel(new BorderLayout());
        aba3.add(new JScrollPane(new JTextArea("Digite algo aqui...")), BorderLayout.CENTER);

        // Adiciona as abas (título, ícone opcional, componente, tooltip)
        abas.addTab("Cadastro", null, aba1, "Formulário de cadastro");
        abas.addTab("Lista",    null, aba2, "Lista de itens");
        abas.addTab("Notas",    null, aba3, "Bloco de notas");

        add(abas);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppComAbas::new);
    }
}
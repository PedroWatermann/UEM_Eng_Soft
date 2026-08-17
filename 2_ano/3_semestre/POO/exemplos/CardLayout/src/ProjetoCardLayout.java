import javax.swing.*;
import java.awt.*;

public class ProjetoCardLayout {
    private JFrame frame;
    private JPanel container;
    private CardLayout cardLayout;

    // componentes do painel principal
    private JPanel panelMain;
    private JTextField txtNameMain;
    private JButton btnGoRegister;

    // componentes do painel de cadastro
    private JPanel panelRegister;
    private JTextField txtNameRegister;
    private JTextField txtEmailRegister;
    private JButton btnBack;

    public ProjetoCardLayout() {
        frame = new JFrame("Projeto CardLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        // --- PanelMain ---
        panelMain = new JPanel(new FlowLayout());
        txtNameMain = new JTextField(15);
        btnGoRegister = new JButton("Ir para cadastro");

        panelMain.add(new JLabel("Nome:"));
        panelMain.add(txtNameMain);
        panelMain.add(btnGoRegister);

        // --- PanelRegister ---
        panelRegister = new JPanel(new GridLayout(3, 2, 5, 5));
        txtNameRegister = new JTextField(15);
        txtEmailRegister = new JTextField(15);
        btnBack = new JButton("Voltar");

        panelRegister.add(new JLabel("Nome:"));
        panelRegister.add(txtNameRegister);
        panelRegister.add(new JLabel("Email:"));
        panelRegister.add(txtEmailRegister);
        panelRegister.add(btnBack);

        // adiciona os painéis ao container
        container.add(panelMain, "main");
        container.add(panelRegister, "register");

        // --- Ações ---
        btnGoRegister.addActionListener(e -> {
            // passa o nome digitado no Main para o Register
            String nome = txtNameMain.getText();
            txtNameRegister.setText(nome);

            cardLayout.show(container, "register");
            frame.pack();
            frame.setLocationRelativeTo(null);
        });

        btnBack.addActionListener(e -> {
            cardLayout.show(container, "main");
            frame.pack();
            frame.setLocationRelativeTo(null);
        });

        frame.setContentPane(container);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProjetoCardLayout::new);
    }
}

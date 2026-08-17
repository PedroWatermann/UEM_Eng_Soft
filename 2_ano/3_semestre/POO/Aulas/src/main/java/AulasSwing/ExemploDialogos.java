package AulasSwing;

import javax.swing.JOptionPane;

public class ExemploDialogos {
    public ExemploDialogos() {
        JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        String nome = JOptionPane.showInputDialog(null, "Digite seu nome:");

        int resp = JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (resp == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Olá, " + nome);
        }
    }
}

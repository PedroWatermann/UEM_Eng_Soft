package AulasSwing.MVC.Controller;

import AulasSwing.MVC.Class.Pessoa;
import AulasSwing.MVC.Model.PessoaModel;
import AulasSwing.MVC.View.PessoaView;

import javax.swing.*;

public class PessoaController {
    public PessoaController(PessoaModel model, PessoaView view) {
        view.btnAdicionar.addActionListener(e -> {
            try {
                String nome = view.txtNome.getText().trim();
                int idade = Integer.parseInt(view.txtIdade.getText().trim());

                model.adicionar(nome, idade);

                view.txtNome.setText("");
                view.txtIdade.setText("");

                StringBuilder sb = new StringBuilder();
                for (Pessoa p : model.listar())
                    sb.append(p).append("\n");

                view.txtLista.setText(sb.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

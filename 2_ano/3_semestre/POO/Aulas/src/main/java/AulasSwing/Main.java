package AulasSwing;

import AulasSwing.MVC.Controller.PessoaController;
import AulasSwing.MVC.Model.PessoaModel;
import AulasSwing.MVC.View.PessoaView;

public class Main {
    public static void main(String[] args) {
        //new ExemploDialogo();
        //new ExemploDialogos();

        //new JanelaComponentes();

        /*
        ClasseInterna janela = new ClasseInterna();
        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        janela.setSize(800,600);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        */

        /*
        ClasseAnonima janela = new ClasseAnonima();
        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        janela.setSize(800,600);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        */

        PessoaModel model = new PessoaModel();
        PessoaView view = new PessoaView();
        new PessoaController(model, view);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}

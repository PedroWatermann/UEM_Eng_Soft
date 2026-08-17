package AulasSwing.MVC.Model;

import AulasSwing.MVC.Class.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaModel {
    private List<Pessoa> pessoas = new ArrayList<>();

    public void adicionar(String nome, int idade) {
         if (nome.isEmpty())
             throw new IllegalArgumentException("Nome vazio");
         if (idade <= 0 || idade > 150)
             throw new IllegalArgumentException("Idade inválida");
         pessoas.add(new Pessoa(nome, idade));
    }

    public List<Pessoa> listar() {
        return pessoas;
    }
}

package com.rpgpoo.Personagem.model;

import com.rpgpoo.Arma.model.ArmaModel;
import com.rpgpoo.Atributo.model.AtributoModel;
import com.rpgpoo.Classe.model.ClasseModel;
import com.rpgpoo.Entidade.model.EntidadeModel;
import com.rpgpoo.Item.model.ItemModel;
import com.rpgpoo.Jogador.model.JogadorModel;
import com.rpgpoo.Raca.model.RacaModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "personagem")
public class PersonagemModel extends EntidadeModel {
    @ManyToOne
    @JoinColumn(name = "classe_id")
    private ClasseModel classe;

    @ManyToMany
    @JoinTable(
            name = "personagem_item",
            joinColumns = @JoinColumn(name = "personagem_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<ItemModel> itens;

    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private JogadorModel jogador;

    @ManyToOne
    @JoinColumn(name = "raca_id")
    private RacaModel raca;

    @Column(nullable = false)
    private double dinheiro;

    @ManyToOne
    @JoinColumn(name = "atributo_id")
    private AtributoModel atributo;

    //region Getters e Setters
    public ClasseModel getClasse() {
        return classe;
    }

    public void setClasse(ClasseModel classe) {
        this.classe = classe;
    }

    public List<ItemModel> getItens() {
        return itens;
    }

    public void setItens(List<ItemModel> itens) {
        this.itens = itens;
    }

    public JogadorModel getJogador() {
        return jogador;
    }

    public void setJogador(JogadorModel jogador) {
        this.jogador = jogador;
    }

    public RacaModel getRaca() {
        return raca;
    }

    public void setRaca(RacaModel raca) {
        this.raca = raca;
    }

    public double getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(double dinheiro) {
        this.dinheiro = dinheiro;
    }

    public AtributoModel getAtributo() {
        return atributo;
    }

    public void setAtributo(AtributoModel atributo) {
        this.atributo = atributo;
    }
    //endregion

    //Construtor
    protected PersonagemModel() {}

    public PersonagemModel(String nome, int nivel, int ataque, int vida, int defesa, ArmaModel arma, ClasseModel classe, List<ItemModel> itens, JogadorModel jogador, RacaModel raca, double dinheiro, AtributoModel atributo, int dt) {
        super(nome, nivel, ataque, vida, defesa, arma, dt);
        this.setClasse(classe);
        this.setItens(itens);
        this.setJogador(jogador);
        this.setRaca(raca);
        this.setDinheiro(dinheiro);
        this.setAtributo(atributo);
    }

    @Override
    public int atacar(int dtAlvo) {
        int sorte = this.getCampanhaAtual() != null ? this.getCampanhaAtual().getDado().rolarDado() : -1;
        if (sorte > dtAlvo)
            return this.getAtaque() * this.getClasse().getMultiplicadorDano();
        return 0;
    }

    @Override
    public void defender(int danoRecebido) {
        int reducao = this.getDefesa() * this.getClasse().getMultiplicadorDefesa();
        int danoFinal = Math.max(danoRecebido - reducao, 0);
        this.setVida(this.getVida() - danoFinal);
    }


}

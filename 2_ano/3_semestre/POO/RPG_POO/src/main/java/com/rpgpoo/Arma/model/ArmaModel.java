package com.rpgpoo.Arma.model;

import com.rpgpoo.Dado.model.DadoModel;
import com.rpgpoo.Enum.RaridadeEnum;
import com.rpgpoo.Enum.TipoItemEnum;
import com.rpgpoo.Item.model.ItemModel;
import jakarta.persistence.*;

@Entity
@Table(name = "arma")
public class ArmaModel extends ItemModel {

    @Column(nullable = false)
    private int dano;

    @Column(nullable = false)
    private int alcance;

    @Column(nullable = false)
    private int durabilidade;

    @ManyToOne
    @JoinColumn(name = "dado_id")
    private DadoModel dado;

    protected ArmaModel() {}

    public ArmaModel(String nome, TipoItemEnum tipoItem, double valorEfeito, RaridadeEnum raridade, double valor,
                     int dano, int alcance, int durabilidade, DadoModel dado) {
        super(nome, tipoItem, valorEfeito, raridade, valor);
        this.setDano(dano);
        this.setAlcance(alcance);
        this.setDurabilidade(durabilidade);
        this.setDado(dado);
    }

    //region Getters e Setters
    public int getDano() { return this.dano; }
    public void setDano(int dano) { this.dano = dano; }

    public int getAlcance() { return this.alcance; }
    public void setAlcance(int alcance) { this.alcance = alcance; }

    public int getDurabilidade() { return this.durabilidade; }
    public void setDurabilidade(int durabilidade) { this.durabilidade = durabilidade; }

    public DadoModel getDado() { return this.dado; }
    public void setDado(DadoModel dado) { this.dado = dado; }
    //endregion

    @Override
    public String toString() {
        return this.getNome().substring(0, 1).toUpperCase() + this.getNome().substring(1);
    }
}
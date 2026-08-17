package com.rpgpoo.Item.model;

import com.rpgpoo.Enum.RaridadeEnum;
import com.rpgpoo.Enum.TipoItemEnum;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "item")
public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoItemEnum tipoItem;

    @Column(nullable = false)
    private double valorEfeito;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RaridadeEnum raridade;

    @Column(nullable = false)
    private double valor;

    protected ItemModel() {}

    public ItemModel(String nome, TipoItemEnum tipoItem, double valorEfeito, RaridadeEnum raridade, double valor) {
        this.nome = nome;
        this.tipoItem = tipoItem;
        this.valorEfeito = valorEfeito;
        this.raridade = raridade;
        this.valor = valor;
    }

    //region Getters e Setters
    public int getId() { return id; }

    public TipoItemEnum getTipoItem() { return tipoItem; }
    public void setTipoItem(TipoItemEnum tipoItem) { this.tipoItem = tipoItem; }

    public RaridadeEnum getRaridade() { return raridade; }
    public void setRaridade(RaridadeEnum raridade) { this.raridade = raridade; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getValorEfeito() { return valorEfeito; }
    public void setValorEfeito(double valorEfeito) { this.valorEfeito = valorEfeito; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemModel)) return false;
        ItemModel itemModel = (ItemModel) o;
        return getId() == itemModel.getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }

    @Override
    public String toString() {
        return this.nome != null ? this.nome : "Item Desconhecido";
    }
}
package com.rpgpoo.Raca.model;

import com.rpgpoo.Atributo.model.AtributoModel;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "raca")
public class RacaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "atributo_id")
    private AtributoModel atributo;

    protected RacaModel() {}

    public RacaModel(String nome, AtributoModel atributo) {
        this.setNome(nome);
        this.setAtributo(atributo);
    }

    //region Getters e Setters
    public int getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public AtributoModel getAtributo() { return atributo; }
    public void setAtributo(AtributoModel atributo) { this.atributo = atributo; }
    //endregion

    @Override
    public String toString() {
        return this.getNome().substring(0, 1).toUpperCase() + this.getNome().substring(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RacaModel)) return false;
        RacaModel racaModel = (RacaModel) o;
        return id == racaModel.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
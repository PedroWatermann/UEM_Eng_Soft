package com.rpgpoo.Dado.model;

import com.rpgpoo.Atributo.model.AtributoModel;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "dado")
public class DadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int lados;

    @ManyToOne
    @JoinColumn(name = "atributo_id")
    private AtributoModel atributo;

    protected DadoModel() {}

    public DadoModel(int lados, AtributoModel atributo) {
        this.setLados(lados);
        this.setAtributo(atributo);
    }

    //region Getters e Setters
    public int getId() { return id; }

    public int getLados() { return lados; }
    public void setLados(int lados) {
        if (lados > 0) {
            this.lados = lados;
        }
    }

    public AtributoModel getAtributo() { return atributo; }
    public void setAtributo(AtributoModel atributo) { this.atributo = atributo; }
    //endregion

    public int rolarDado() {
        return (int) (Math.random() * this.getLados()) + 1;
    }

    @Override
    public String toString() {
        return "D" + this.getLados();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DadoModel)) return false;
        DadoModel that = (DadoModel) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
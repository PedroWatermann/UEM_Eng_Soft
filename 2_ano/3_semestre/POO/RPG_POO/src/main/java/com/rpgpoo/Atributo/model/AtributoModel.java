package com.rpgpoo.Atributo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "atributo")
public class AtributoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int modificador;

    //region Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getModificador() {
        return modificador;
    }

    public void setModificador(int modificador) {
        this.modificador = modificador;
    }
    //endregion

    protected AtributoModel() {}

    public AtributoModel(String nome, int modificador) {
        this.setNome(nome);
        this.setModificador(modificador);
    }
}

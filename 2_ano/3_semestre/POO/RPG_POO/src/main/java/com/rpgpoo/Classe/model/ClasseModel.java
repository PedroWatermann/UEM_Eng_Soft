package com.rpgpoo.Classe.model;

import com.rpgpoo.Enum.TipoArmaEnum;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "classe")
public class ClasseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoArmaEnum tipoArma;

    @Column(nullable = false)
    private int multiplicadorDano;

    @Column(nullable = false)
    private int multiplicadorDefesa;

    protected ClasseModel() {}

    public ClasseModel(String nome, TipoArmaEnum tipoArma, int multiplicadorDano, int multiplicadorDefesa) {
        this.setNome(nome);
        this.setTipoArma(tipoArma);
        this.setMultiplicadorDano(multiplicadorDano);
        this.setMultiplicadorDefesa(multiplicadorDefesa);
    }

    //region Getters e Setters
    public int getId() { return this.id; }

    public String getNome() { return this.nome; }
    public void setNome(String nome) { this.nome = nome; }

    public TipoArmaEnum getTipoArma() { return this.tipoArma; }
    public void setTipoArma(TipoArmaEnum tipoArma) { this.tipoArma = tipoArma; }

    public int getMultiplicadorDano() { return this.multiplicadorDano; }
    public void setMultiplicadorDano(int multiplicador) { this.multiplicadorDano = multiplicador; }

    public int getMultiplicadorDefesa() { return this.multiplicadorDefesa; }
    public void setMultiplicadorDefesa(int multiplicador) { this.multiplicadorDefesa = multiplicador; }
    //endregion


    @Override
    public String toString() {
        return this.getNome().substring(0, 1).toUpperCase() + this.getNome().substring(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClasseModel)) return false;
        ClasseModel that = (ClasseModel) o;
        return id == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
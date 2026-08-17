package com.rpgpoo.Campanha.model;

import com.rpgpoo.Dado.model.DadoModel;
import com.rpgpoo.Jogador.model.JogadorModel;
import com.rpgpoo.Personagem.model.PersonagemModel;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(
        name = "Campanha.porIdCampanha",
        query = "SELECT c FROM CampanhaModel c " +
                "JOIN FETCH c.dado " +
                "JOIN FETCH c.mestre " +
                "WHERE c.id = :id"
)
@Table(name = "campanha")
public class CampanhaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "campanha_personagem",
            joinColumns = @JoinColumn(name = "campanha_id"),
            inverseJoinColumns = @JoinColumn(name = "personagem_id")
    )
    private List<PersonagemModel> personagens = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "campanha_jogador",
            joinColumns = @JoinColumn(name = "campanha_id"),
            inverseJoinColumns = @JoinColumn(name = "jogador_id")
    )
    private List<JogadorModel> jogadores = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dado_id")
    private DadoModel dado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mestre_id")
    private JogadorModel mestre;

    protected CampanhaModel() {}

    public CampanhaModel(String nome, String descricao, List<PersonagemModel> personagens,
                         List<JogadorModel> jogadores, DadoModel dado, JogadorModel mestre) {
        this.setNome(nome);
        this.setDescricao(descricao);
        this.setPersonagens(personagens);
        this.setJogadores(jogadores);
        this.setDado(dado);
        this.setMestre(mestre);
    }

    //region Getters e Setters
    public int getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<PersonagemModel> getPersonagens() { return personagens; }
    public void setPersonagens(List<PersonagemModel> personagens) {
        if (personagens != null && !personagens.isEmpty())
            this.personagens = personagens;
    }
    public void adicionarPersonagem(PersonagemModel personagem) {
        if (personagem != null && !this.personagens.contains(personagem))
            this.personagens.add(personagem);
    }

    public List<JogadorModel> getJogadores() { return jogadores; }
    public void setJogadores(List<JogadorModel> jogadores) {
        if (jogadores != null && !jogadores.isEmpty())
            this.jogadores = jogadores;
    }
    public void adicionarJogador(JogadorModel jogador) {
        if (jogador != null && !this.jogadores.contains(jogador))
            this.jogadores.add(jogador);
    }

    public DadoModel getDado() { return this.dado; }
    public void setDado(DadoModel dado) { this.dado = dado; }

    public JogadorModel getMestre() { return mestre; }
    public void setMestre(JogadorModel mestre) { this.mestre = mestre; }
    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CampanhaModel that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}
package com.rpgpoo.Combate.model;

import com.rpgpoo.Campanha.model.CampanhaModel;
import com.rpgpoo.Entidade.model.EntidadeModel;
import com.rpgpoo.Monstro.model.MonstroModel;
import com.rpgpoo.Personagem.model.PersonagemModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CombateModel {
    private CampanhaModel campanha;
    private List<EntidadeModel> participantes;
    private List<PersonagemModel> personagens;
    private MonstroModel monstro;
    private int rodadaAtual = 1;

    //region Getters e Setters
    public CampanhaModel getCampanha() {
        return campanha;
    }

    public void setCampanha(CampanhaModel campanha) {
        this.campanha = campanha;
    }

    public List<EntidadeModel> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<EntidadeModel> participantes) {
        this.participantes = participantes;
    }

    public List<PersonagemModel> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(List<PersonagemModel> personagens) {
        this.personagens = personagens;
    }

    public MonstroModel getMonstro() {
        return monstro;
    }

    public void setMonstro(MonstroModel monstro) {
        this.monstro = monstro;
    }
    //endregion

    //Construtor
    public CombateModel(CampanhaModel campanha, List<EntidadeModel> participantes, List<PersonagemModel> personagens, MonstroModel monstro) {
        this.campanha = campanha;
        this.participantes = new ArrayList<>(participantes);
        this.personagens = new ArrayList<>(personagens);
        this.monstro = monstro;
    }

    private void sortearOrdemDeAcao() {
        Collections.shuffle(this.participantes);
    }

    private void atualizaParticipantes() {
        List<PersonagemModel> personagensVivos = this.personagens.stream()
            .filter(personagem -> personagem.getVida() > 0)
            .toList();

        this.setPersonagens(personagensVivos);

        List<EntidadeModel> participantesVivos = new ArrayList<>();
        if (this.monstro.getVida() > 0) {
            participantesVivos.add(this.monstro);
        }
        participantesVivos.addAll(personagensVivos);

        this.setParticipantes(participantesVivos);

        System.out.println("\n--- 📊 STATUS DO COMBATE ---");
        for (PersonagemModel personagem : personagensVivos) {
            System.out.printf("❤️ %-25s | HP: %d\n", personagem.getNome(), personagem.getVida());
        }
        if (monstro.getVida() > 0) {
            System.out.printf("👹 %-25s | HP: %d\n", monstro.getNome(), monstro.getVida());
        }
        System.out.println("----------------------------\n");
    }

    private void iniciarTurno() {
        System.out.println("==================================================");
        System.out.println("⚔️  RODADA " + rodadaAtual + " INICIADA! ⚔️");
        System.out.println("==================================================");

        for (EntidadeModel participante : participantes) {
            if (participante.getVida() <= 0) continue;

            if (participante instanceof PersonagemModel) {
                if (monstro.getVida() > 0) {
                    int vidaMonstroAntes = monstro.getVida();
                    int valorAtaque = participante.atacar(monstro.getDt());
                    monstro.defender(valorAtaque);
                    int danoReal = vidaMonstroAntes - monstro.getVida();

                    System.out.printf("🗡️  [%s] ataca [%s]!\n", participante.getNome(), monstro.getNome());
                    System.out.printf("   💥 Dano efetivo: %d\n", danoReal);

                    if (monstro.getVida() <= 0) {
                        System.out.printf("   💀 [%s] FOI DERROTADO!\n", monstro.getNome());
                        break;
                    }
                }
            } else {
                if (!personagens.isEmpty()) {
                    int indiceAlvo = (int) (Math.random() * personagens.size());
                    PersonagemModel personagemAlvo = personagens.get(indiceAlvo);

                    int vidaPersonagemAntes = personagemAlvo.getVida();
                    int valorAtaque = monstro.atacar(personagemAlvo.getDt());
                    personagemAlvo.defender(valorAtaque);
                    int danoReal = vidaPersonagemAntes - personagemAlvo.getVida();

                    System.out.printf("👹 [%s] ataca ferozmente [%s]!\n", monstro.getNome(), personagemAlvo.getNome());
                    System.out.printf("   🩸 Dano sofrido: %d\n", danoReal);

                    if (personagemAlvo.getVida() <= 0) {
                        System.out.printf("   🪦 [%s] CAIU EM BATALHA!\n", personagemAlvo.getNome());
                    }
                }
            }
        }

        rodadaAtual++;
        this.atualizaParticipantes();
    }

    public void iniciarCombate() {
        System.out.println("\n==================================================");
        System.out.println("🔥 O COMBATE COMEÇOU! 🔥");
        System.out.printf("Ameaça: %s (HP: %d)\n", monstro.getNome(), monstro.getVida());
        System.out.println("==================================================");

        this.sortearOrdemDeAcao();

        while (!personagens.isEmpty() && monstro.getVida() > 0) {
            this.iniciarTurno();
        }

        System.out.println("==================================================");
        if (monstro.getVida() <= 0) {
            System.out.println("🏆 VITÓRIA! O grupo superou a ameaça! 🎉");
        } else {
            System.out.println("☠️ DERROTA! O grupo foi aniquilado... 🪦");
        }
        System.out.println("==================================================");
    }
}
package com.rpgpoo.Monstro.controller;

import com.rpgpoo.Arma.model.ArmaModel;
import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Monstro.model.MonstroModel;
import com.rpgpoo.Monstro.view.MonstroEditView;
import com.rpgpoo.Raca.model.RacaModel;
import com.rpgpoo.utils.JpaUtil;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.util.List;

public class MonstroEditController {
    private final Gerenciador gerenciador;
    private final MonstroEditView view;
    private final boolean ehNovo;
    private final Integer idMonstro;
    private final Runnable sucesso;

    public MonstroEditController(Gerenciador gerenciador, MonstroEditView view, boolean ehNovo, Integer idMonstro, Runnable sucesso) {
        this.gerenciador = gerenciador;
        this.view = view;
        this.ehNovo = ehNovo;
        this.idMonstro = idMonstro;
        this.sucesso = sucesso;

        carregarCombos();

        if (!ehNovo && idMonstro != null) {
            carregarMonstroParaEdicao();
        }
    }

    private void carregarCombos() {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            List<RacaModel> racas = em
                    .createQuery("SELECT r FROM RacaModel r ORDER BY r.nome", RacaModel.class)
                    .getResultList();
            List<ArmaModel> armas = em
                    .createQuery("SELECT a FROM ArmaModel a ORDER BY a.nome", ArmaModel.class)
                    .getResultList();

            for (RacaModel r : racas) view.getCbxRaca().addItem(r);
            for (ArmaModel a : armas) view.getCbxArma().addItem(a);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao carregar raças e armas.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarMonstroParaEdicao() {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            MonstroModel monstro = em
                    .createNamedQuery("Monstro.porId", MonstroModel.class)
                    .setParameter("id", idMonstro)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (monstro == null) {
                JOptionPane.showMessageDialog(view, "Monstro não encontrado no banco de dados.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            view.getLblIdMonstro().setText(String.valueOf(monstro.getId()));
            view.getTxtNome().setText(monstro.getNome());
            view.getTxtNivel().setText(String.valueOf(monstro.getNivel()));
            view.getTxtVida().setText(String.valueOf(monstro.getVida()));
            view.getTxtDefesa().setText(String.valueOf(monstro.getDefesa()));
            view.getTxtAtaque().setText(String.valueOf(monstro.getAtaque()));
            view.getTxtDt().setText(String.valueOf(monstro.getDt()));

            selecionarItemPorId(view.getCbxRaca(), monstro.getRaca() != null ? monstro.getRaca().getId() : null);
            selecionarItemPorId(view.getCbxArma(), monstro.getArma() != null ? monstro.getArma().getId() : null);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao carregar dados do monstro.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void selecionarItemPorId(JComboBox<?> combo, Integer id) {
        if (id == null) return;
        for (int i = 0; i < combo.getItemCount(); i++) {
            Object item = combo.getItemAt(i);
            if (item instanceof RacaModel r && r.getId() == id) {
                combo.setSelectedItem(item);
                return;
            }
            if (item instanceof ArmaModel a && a.getId() == id) {
                combo.setSelectedItem(item);
                return;
            }
        }
    }

    public void btnSalvarMonstro() {
        String nome = view.getTxtNome().getText();
        String nivelStr = view.getTxtNivel().getText();
        String vidaStr = view.getTxtVida().getText();
        String defesaStr = view.getTxtDefesa().getText();
        String ataqueStr = view.getTxtAtaque().getText();
        String dtStr = view.getTxtDt().getText();
        RacaModel raca = (RacaModel) view.getCbxRaca().getSelectedItem();
        ArmaModel arma = (ArmaModel) view.getCbxArma().getSelectedItem();

        if (nome == null || nome.trim().isEmpty() ||
                nivelStr == null || !nivelStr.matches("\\d+") ||
                vidaStr == null || !vidaStr.matches("\\d+") ||
                defesaStr == null || !defesaStr.matches("\\d+") ||
                ataqueStr == null || !ataqueStr.matches("\\d+") ||
                dtStr == null || !dtStr.matches("\\d+") ||
                raca == null) {

            JOptionPane.showMessageDialog(view, "Preencha todos os campos corretamente!");
            return;
        }

        int nivel = Integer.parseInt(nivelStr);
        int vida = Integer.parseInt(vidaStr);
        int defesa = Integer.parseInt(defesaStr);
        int ataque = Integer.parseInt(ataqueStr);
        int dt = Integer.parseInt(dtStr);

        if (nivel < 0 || vida < 0 || defesa < 0 || ataque < 0 || dt < 0) {
            JOptionPane.showMessageDialog(view, "Nenhum valor pode ser negativo!");
            return;
        }

        try (EntityManager em = JpaUtil.getEntityManager()) {
            em.getTransaction().begin();
            MonstroModel monstro;

            if (!ehNovo) {
                monstro = em
                        .createNamedQuery("Monstro.porId", MonstroModel.class)
                        .setParameter("id", idMonstro)
                        .getResultStream()
                        .findFirst()
                        .orElse(null);
            } else {
                monstro = new MonstroModel();
            }

            if (monstro == null) {
                em.getTransaction().rollback();
                JOptionPane.showMessageDialog(view, "Monstro não encontrado para edição.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            monstro.setNome(nome);
            monstro.setNivel(nivel);
            monstro.setVida(vida);
            monstro.setDefesa(defesa);
            monstro.setAtaque(ataque);
            monstro.setDt(dt);
            monstro.setRaca(raca);
            monstro.setArma(arma);

            if (ehNovo) {
                em.persist(monstro);
            }

            em.getTransaction().commit();

            if (sucesso != null) sucesso.run();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao salvar monstro no banco de dados.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
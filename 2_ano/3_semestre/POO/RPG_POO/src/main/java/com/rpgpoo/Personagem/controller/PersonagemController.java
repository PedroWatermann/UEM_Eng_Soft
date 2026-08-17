package com.rpgpoo.Personagem.controller;

import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Personagem.model.PersonagemModel;
import com.rpgpoo.Personagem.view.PersonagemView;
import com.rpgpoo.utils.JpaUtil;
import com.rpgpoo.Raca.model.RacaModel;
import com.rpgpoo.Classe.model.ClasseModel;
import com.rpgpoo.Campanha.model.CampanhaModel;
import com.rpgpoo.Jogador.model.JogadorModel;
import com.rpgpoo.Arma.model.ArmaModel;
import com.rpgpoo.Atributo.model.AtributoModel;
import com.rpgpoo.Login.sessao.SessaoUsuario;

import com.rpgpoo.Item.model.ItemModel;
import javax.swing.table.DefaultTableModel;

import jakarta.persistence.EntityManager;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;

public class PersonagemController {
    private PersonagemView view;
    private Gerenciador gerenciador;
    private List<ItemModel> itensAtuais = new ArrayList<>();

    public PersonagemController(PersonagemView view, Gerenciador gerenciador) {
        this.view = view;
        this.gerenciador = gerenciador;
    }
    
    public void init() {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            em.getTransaction().begin();
            em.createNativeQuery("INSERT INTO arma (id, dano, alcance, durabilidade) SELECT id, 0, 0, 0 FROM item WHERE tipoitem = 'ARMA' AND id NOT IN (SELECT id FROM arma)").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        carregarDependencias();
        carregarPersonagens();
        initListeners();
    }

    private void carregarDependencias() {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            List<RacaModel> racas = em.createQuery("SELECT r FROM RacaModel r", RacaModel.class).getResultList();
            List<ClasseModel> classes = em.createQuery("SELECT c FROM ClasseModel c", ClasseModel.class).getResultList();
            List<CampanhaModel> campanhas = em.createQuery("SELECT c FROM CampanhaModel c", CampanhaModel.class).getResultList();
            
            view.getCbxRaca().removeAllItems();
            for (RacaModel r : racas) view.getCbxRaca().addItem(r);
            
            view.getCbxClasse().removeAllItems();
            for (ClasseModel c : classes) view.getCbxClasse().addItem(c);
            
            view.getCbxCampanha().removeAllItems();
            for (CampanhaModel c : campanhas) view.getCbxCampanha().addItem(c);
            List<JogadorModel> jogadores = em.createQuery("SELECT j FROM JogadorModel j", JogadorModel.class).getResultList();
            view.getCbxJogador().removeAllItems();
            for (JogadorModel j : jogadores) view.getCbxJogador().addItem(j);

            List<ArmaModel> armas = em.createQuery("SELECT a FROM ArmaModel a", ArmaModel.class).getResultList();
            view.getCbxArma().removeAllItems();
            for (ArmaModel a : armas) view.getCbxArma().addItem(a);
            view.getCbxArma().setSelectedIndex(-1);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar dependencias.");
        }
    }

    private void carregarPersonagens() {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            List<PersonagemModel> personagens = em
                    .createQuery("SELECT DISTINCT p FROM PersonagemModel p LEFT JOIN FETCH p.itens ORDER BY p.nome", PersonagemModel.class)
                    .getResultList();

            JComboBox<PersonagemModel> cbx = view.getCbxSelecionarPersonagem();
            if (cbx != null) {
                cbx.removeAllItems();
                for (PersonagemModel p : personagens) {
                    cbx.addItem(p);
                }
                cbx.setSelectedIndex(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao carregar personagens.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initListeners() {
        JComboBox<PersonagemModel> cbx = view.getCbxSelecionarPersonagem();
        if (cbx != null) {
            cbx.addActionListener((ActionEvent e) -> {
                PersonagemModel selecionado = (PersonagemModel) cbx.getSelectedItem();
                if (selecionado != null) {
                    atualizarViewComPersonagem(selecionado);
                }
            });
        }

        if (view.getBtnNovoPersonagem() != null) {
            view.getBtnNovoPersonagem().addActionListener(e -> limparCampos());
        }

        if (view.getBtnSalvar() != null) {
            view.getBtnSalvar().addActionListener(e -> salvarPersonagem());
        }

        if (view.getBtnExcluirPersonagem() != null) {
            view.getBtnExcluirPersonagem().addActionListener(e -> excluirPersonagem());
        }
        
        if (view.getCbxArma() != null) {
            view.getCbxArma().addActionListener(e -> {
                ArmaModel selecionada = (ArmaModel) view.getCbxArma().getSelectedItem();
                if (selecionada != null) {
                    view.getTxtDanoArma().setText(String.valueOf(selecionada.getDano()));
                    view.getTxtAlcanceArma().setText(String.valueOf(selecionada.getAlcance()));
                    view.getTxtDurabilidadeArma().setText(String.valueOf(selecionada.getDurabilidade()));
                    if (selecionada.getDado() != null) {
                        view.getCbxDadoArma().removeAllItems();
                        view.getCbxDadoArma().addItem(selecionada.getDado().toString());
                    }
                }
            });
        }
        
        if (view.getBtnAdicionarInventario() != null) {
            view.getBtnAdicionarInventario().addActionListener(e -> btnAdicionarInventarioClick());
        }
        
        if (view.getBtnRemoverInventario() != null) {
            view.getBtnRemoverInventario().addActionListener(e -> btnRemoverInventarioClick());
        }
    }

    private void excluirPersonagem() {
        PersonagemModel selecionado = (PersonagemModel) view.getCbxSelecionarPersonagem().getSelectedItem();
        if (selecionado == null) {
            JOptionPane.showMessageDialog(view, "Selecione um personagem para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nome = selecionado.getNome() != null && !selecionado.getNome().isEmpty() ? selecionado.getNome() : "Desconhecido";
        int confirm = JOptionPane.showConfirmDialog(view, "Deseja realmente excluir " + nome + "?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (EntityManager em = JpaUtil.getEntityManager()) {
                em.getTransaction().begin();
                PersonagemModel p = em.find(PersonagemModel.class, selecionado.getId());
                if (p != null) {
                    em.remove(p);
                }
                em.getTransaction().commit();
                
                JOptionPane.showMessageDialog(view, "Personagem excluído com sucesso!");
                limparCampos();
                carregarPersonagens();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "Erro ao excluir personagem: " + ex.getMessage());
            }
        }
    }

    private void limparCampos() {
        view.getCbxSelecionarPersonagem().setSelectedIndex(-1);
        view.getTxtNome().setText("");
        view.getTxtNivel().setText("");
        view.getTxtDinheiro().setText("");
        if (view.getCbxRaca().getItemCount() > 0) view.getCbxRaca().setSelectedIndex(0);
        if (view.getCbxClasse().getItemCount() > 0) view.getCbxClasse().setSelectedIndex(0);
        if (view.getCbxCampanha().getItemCount() > 0) view.getCbxCampanha().setSelectedIndex(0);
        if (view.getCbxJogador().getItemCount() > 0) view.getCbxJogador().setSelectedIndex(0);
        
        view.getTxtAtrFor().setText("10");
        view.getTxtAtrDes().setText("10");
        view.getTxtAtrCon().setText("10");
        view.getTxtAtrInt().setText("10");
        view.getTxtAtrSab().setText("10");
        view.getTxtAtrCar().setText("10");
        
        if (view.getCbxArma().getItemCount() > 0) view.getCbxArma().setSelectedIndex(-1);
        view.getTxtDanoArma().setText("");
        view.getTxtAlcanceArma().setText("");
        view.getTxtDurabilidadeArma().setText("");
        view.getCbxDadoArma().removeAllItems();
        
        itensAtuais.clear();
        atualizarTabelaInventario();
    }

    private void salvarPersonagem() {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            em.getTransaction().begin();

            String nome = view.getTxtNome().getText().trim();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(view, "O nome do personagem não pode estar vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int nivel = view.getTxtNivel().getText().isEmpty() ? 1 : Integer.parseInt(view.getTxtNivel().getText());
            double dinheiro = view.getTxtDinheiro().getText().isEmpty() ? 0 : Double.parseDouble(view.getTxtDinheiro().getText());
            
            int atrFor = view.getTxtAtrFor().getText().isEmpty() ? 10 : Integer.parseInt(view.getTxtAtrFor().getText());
            int atrDes = view.getTxtAtrDes().getText().isEmpty() ? 10 : Integer.parseInt(view.getTxtAtrDes().getText());
            int atrCon = view.getTxtAtrCon().getText().isEmpty() ? 10 : Integer.parseInt(view.getTxtAtrCon().getText());
            int atrInt = view.getTxtAtrInt().getText().isEmpty() ? 10 : Integer.parseInt(view.getTxtAtrInt().getText());
            int atrSab = view.getTxtAtrSab().getText().isEmpty() ? 10 : Integer.parseInt(view.getTxtAtrSab().getText());
            int atrCar = view.getTxtAtrCar().getText().isEmpty() ? 10 : Integer.parseInt(view.getTxtAtrCar().getText());

            RacaModel raca = (RacaModel) view.getCbxRaca().getSelectedItem();
            ClasseModel classe = (ClasseModel) view.getCbxClasse().getSelectedItem();
            ArmaModel arma = (ArmaModel) view.getCbxArma().getSelectedItem();
            
            PersonagemModel selecionado = (PersonagemModel) view.getCbxSelecionarPersonagem().getSelectedItem();

            if (selecionado == null) {
                // Fetch first Atributo as dummy for creation for now
                AtributoModel atributoDummy = em.createQuery("SELECT a FROM AtributoModel a", AtributoModel.class).getResultStream().findFirst().orElse(null);

                // Nome, nivel, ataque, vida, defesa, arma, classe, itens, jogador, raca, dinheiro, atributo, dt
                PersonagemModel novo = new PersonagemModel(
                        nome,
                        nivel,
                        10, // ataque base
                        100, // vida base
                        10, // defesa base
                        arma,
                        classe,
                        new ArrayList<>(itensAtuais),
                        (JogadorModel) view.getCbxJogador().getSelectedItem(), // Assign current logged-in player
                        raca,
                        dinheiro,
                        atributoDummy,
                        10 // DT base
                );
                
                novo.setAtrFor(atrFor);
                novo.setAtrDes(atrDes);
                novo.setAtrCon(atrCon);
                novo.setAtrInt(atrInt);
                novo.setAtrSab(atrSab);
                novo.setAtrCar(atrCar);
                
                em.persist(novo);
                JOptionPane.showMessageDialog(view, "Personagem criado com sucesso!");
            } else {
                PersonagemModel existente = em.find(PersonagemModel.class, selecionado.getId());
                if (existente != null) {
                    existente.setNome(nome);
                    existente.setNivel(nivel);
                    existente.setDinheiro(dinheiro);
                    existente.setRaca(raca);
                    existente.setClasse(classe);
                    existente.setArma(arma);
                    existente.setJogador((JogadorModel) view.getCbxJogador().getSelectedItem());
                    
                    if (existente.getItens() != null) {
                        existente.getItens().clear();
                        existente.getItens().addAll(itensAtuais);
                    } else {
                        existente.setItens(new ArrayList<>(itensAtuais));
                    }
                    
                    existente.setAtrFor(atrFor);
                    existente.setAtrDes(atrDes);
                    existente.setAtrCon(atrCon);
                    existente.setAtrInt(atrInt);
                    existente.setAtrSab(atrSab);
                    existente.setAtrCar(atrCar);

                    em.merge(existente);
                    JOptionPane.showMessageDialog(view, "Personagem atualizado com sucesso!");
                }
            }

            em.getTransaction().commit();
            
            carregarPersonagens(); // Reload the list
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao salvar personagem: " + ex.getMessage());
        }
    }

    private void atualizarViewComPersonagem(PersonagemModel personagem) {
        view.getTxtNome().setText(personagem.getNome());
        view.getTxtNivel().setText(String.valueOf(personagem.getNivel()));
        view.getTxtDinheiro().setText(String.valueOf(personagem.getDinheiro()));
        view.getCbxRaca().setSelectedItem(personagem.getRaca());
        view.getCbxClasse().setSelectedItem(personagem.getClasse());
        
        if (personagem.getJogador() != null) {
            view.getCbxJogador().setSelectedItem(personagem.getJogador());
        } else {
            view.getCbxJogador().setSelectedIndex(-1);
        }
        
        view.getTxtAtrFor().setText(String.valueOf(personagem.getAtrFor()));
        view.getTxtAtrDes().setText(String.valueOf(personagem.getAtrDes()));
        view.getTxtAtrCon().setText(String.valueOf(personagem.getAtrCon()));
        view.getTxtAtrInt().setText(String.valueOf(personagem.getAtrInt()));
        view.getTxtAtrSab().setText(String.valueOf(personagem.getAtrSab()));
        view.getTxtAtrCar().setText(String.valueOf(personagem.getAtrCar()));
        
        if (personagem.getArma() != null) {
            view.getCbxArma().setSelectedItem(personagem.getArma());
        } else {
            view.getCbxArma().setSelectedIndex(-1);
        }
        
        itensAtuais = personagem.getItens() != null ? new ArrayList<>(personagem.getItens()) : new ArrayList<>();
        atualizarTabelaInventario();
        
        System.out.println("Atualizando view para: " + personagem.getNome());
    }

    public void btnAdicionarInventarioClick() {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            List<ItemModel> todosItens = em.createQuery("SELECT i FROM ItemModel i", ItemModel.class).getResultList();
            JComboBox<ItemModel> cbxItems = new JComboBox<>(todosItens.toArray(new ItemModel[0]));
            int result = JOptionPane.showConfirmDialog(view, cbxItems, "Selecione o Item", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                ItemModel selecionado = (ItemModel) cbxItems.getSelectedItem();
                if (selecionado != null) {
                    itensAtuais.add(selecionado);
                    atualizarTabelaInventario();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao carregar itens: " + ex.getMessage());
        }
    }

    private void btnRemoverInventarioClick() {
        int selectedRow = view.getTblInventario().getSelectedRow();
        if (selectedRow >= 0 && selectedRow < itensAtuais.size()) {
            itensAtuais.remove(selectedRow);
            atualizarTabelaInventario();
        } else {
            JOptionPane.showMessageDialog(view, "Selecione um item para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void atualizarTabelaInventario() {
        DefaultTableModel model = (DefaultTableModel) view.getTblInventario().getModel();
        model.setRowCount(0);
        for (ItemModel item : itensAtuais) {
            String tipo = item.getTipoItem() != null ? item.getTipoItem().toString() : "";
            String raridade = item.getRaridade() != null ? item.getRaridade().toString() : "";
            model.addRow(new Object[]{"", item.getNome(), tipo, raridade, item.getValor()});
        }
    }
}

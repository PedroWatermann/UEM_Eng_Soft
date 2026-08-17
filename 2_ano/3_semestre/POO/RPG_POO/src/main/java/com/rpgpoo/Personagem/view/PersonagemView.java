package com.rpgpoo.Personagem.view;

import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.Personagem.controller.PersonagemController;
import com.rpgpoo.Personagem.model.PersonagemModel;
import com.rpgpoo.Raca.model.RacaModel;
import com.rpgpoo.Classe.model.ClasseModel;
import com.rpgpoo.Campanha.model.CampanhaModel;
import com.rpgpoo.Jogador.model.JogadorModel;
import com.rpgpoo.utils.*;
import org.kordamp.ikonli.fontawesome6.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;
import com.rpgpoo.Arma.model.ArmaModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;


public class PersonagemView extends JPanel {

    JPanel pnlFichaPersonagem = new JPanel();
    JPanel pnlArmaInventario = new JPanel();
    JPanel pnlArmaPersonagem = new JPanel();
    JPanel pnlInventarioPersonagem = new JPanel();

    // ficha do personagem
    JLabel lblTitulo = new JLabel();

    JLabel lblSelecionarPersonagem = new JLabel();
    JComboBox<PersonagemModel> cbxSelecionarPersonagem = new JComboBox<>();

    JLabel lblNome = new JLabel();
    JTextField txtNome = new JTextField();
    JLabel lblNivel = new JLabel();
    JTextField txtNivel = new JTextField();

    JLabel lblRaca = new JLabel();
    JComboBox<RacaModel> cbxRaca = new JComboBox<>();
    JLabel lblClasse = new JLabel();
    JComboBox<ClasseModel> cbxClasse = new JComboBox<>();

    JLabel lblDinheiro = new JLabel();
    JTextField txtDinheiro = new JTextField();
    JLabel lblJogador = new JLabel();
    JComboBox<JogadorModel> cbxJogador = new JComboBox<>();
    JLabel lblCampanha = new JLabel();
    JComboBox<CampanhaModel> cbxCampanha = new JComboBox<>();

    // Atributo base
    JLabel lblTituloAtributo = new JLabel();

    JLabel lblFor = new JLabel();
    JLabel lblDes = new JLabel();
    JLabel lblCon = new JLabel();
    JLabel lblInt = new JLabel();
    JLabel lblSab = new JLabel();
    JLabel lblCar = new JLabel();

    JTextField txtAtrFor = new JTextField();
    JTextField txtAtrDes = new JTextField();
    JTextField txtAtrCon = new JTextField();
    JTextField txtAtrInt = new JTextField();
    JTextField txtAtrSab = new JTextField();
    JTextField txtAtrCar = new JTextField();

    // Status de combate
    JLabel lblTituloStatus =  new JLabel();

    JLabel lblVida =  new JLabel();
    JLabel lblAtaque =  new JLabel();
    JLabel lblDefesa = new JLabel();
    JLabel lblHpAtual = new JLabel();

    JLabel lblStatusVida = new JLabel();
    JLabel lblStatusAtaque = new JLabel();
    JLabel lblStatusDefesa = new JLabel();
    JProgressBar pbrHpAtual = new JProgressBar(0, 500);

    // Arma equipada
    JLabel lblTituloArma = new JLabel();

    JLabel lblNomeArma = new JLabel();
    JLabel lblDanoArma = new JLabel();
    JLabel lblAlcanceArma = new JLabel();
    JLabel lblDurabilidadeArma = new JLabel();
    JLabel lblDadoArma = new JLabel();

    JComboBox<String> cbxDadoArma = new JComboBox<>();
    JComboBox<ArmaModel> cbxArma = new JComboBox<>();
    JTextField txtDanoArma = new JTextField();
    JTextField txtAlcanceArma = new JTextField();
    JTextField txtDurabilidadeArma = new JTextField();

    // Inventario
    JLabel lblTituloInventario = new JLabel();
    JTable tblInventario = new JTable();
    JScrollPane scrInventario = new JScrollPane();

    JButton btnAdicionarInventario;
    JButton btnRemoverInventario;

    JButton btnNovoPersonagem;
    JButton btnExcluirPersonagem;
    JButton btnSalvar;

    
    public JButton getBtnNovoPersonagem() { return btnNovoPersonagem; }
    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnExcluirPersonagem() { return btnExcluirPersonagem; }
    public JTextField getTxtNome() { return txtNome; }
    public JTextField getTxtNivel() { return txtNivel; }
    public JTextField getTxtDinheiro() { return txtDinheiro; }
    public JComboBox<RacaModel> getCbxRaca() { return cbxRaca; }
    public JComboBox<ClasseModel> getCbxClasse() { return cbxClasse; }
    public JComboBox<CampanhaModel> getCbxCampanha() { return cbxCampanha; }
    public JComboBox<JogadorModel> getCbxJogador() { return cbxJogador; }
    public JTextField getTxtAtrFor() { return txtAtrFor; }
    public JTextField getTxtAtrDes() { return txtAtrDes; }
    public JTextField getTxtAtrCon() { return txtAtrCon; }
    public JTextField getTxtAtrInt() { return txtAtrInt; }
    public JTextField getTxtAtrSab() { return txtAtrSab; }
    public JTextField getTxtAtrCar() { return txtAtrCar; }
    public JComboBox<PersonagemModel> getCbxSelecionarPersonagem() {
        return cbxSelecionarPersonagem;
    }
    public JComboBox<ArmaModel> getCbxArma() { return cbxArma; }
    public JTable getTblInventario() { return tblInventario; }
    public JButton getBtnAdicionarInventario() { return btnAdicionarInventario; }
    public JButton getBtnRemoverInventario() { return btnRemoverInventario; }
    public JTextField getTxtDanoArma() { return txtDanoArma; }
    public JTextField getTxtAlcanceArma() { return txtAlcanceArma; }
    public JTextField getTxtDurabilidadeArma() { return txtDurabilidadeArma; }
    public JComboBox<String> getCbxDadoArma() { return cbxDadoArma; }

    public PersonagemView(Gerenciador gerenciador) {
        PersonagemController controller = new PersonagemController(this, gerenciador);

        UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(AppColors.GOLD, 1));
        UIManager.put("List.background", AppColors.DARK);
        UIManager.put("List.foreground", AppColors.PARCHMENT);
        UIManager.put("List.selectionBackground", AppColors.CRIMSON);
        UIManager.put("List.selectionForeground", AppColors.PARCHMENT);

        //Ficha do personagem
        lblTitulo.setIcon(FontIcon.of(FontAwesomeSolid.MAP, AppColors.ICON_LG, AppColors.GOLD));
        lblTitulo.setText(" FICHA DO PERSONAGEM");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 18));
        lblTitulo.setForeground(AppColors.GOLD);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JSeparator sepTituloFicha = new JSeparator();
        sepTituloFicha.setForeground(AppColors.GOLD);
        sepTituloFicha.setBackground(AppColors.GOLD);

        lblSelecionarPersonagem.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSelecionarPersonagem.setText("Selecionar Personagem");
        lblSelecionarPersonagem.setHorizontalAlignment(SwingConstants.LEFT);
        lblSelecionarPersonagem.setForeground(AppColors.PARCHMENT);

        cbxSelecionarPersonagem.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cbxSelecionarPersonagem.setForeground(Color.WHITE);
        cbxSelecionarPersonagem.setBackground(AppColors.DARK);
        GenericUtils.estilizarComboBox(cbxSelecionarPersonagem);

        lblNome.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblNome.setText("Nome");
        lblNome.setHorizontalAlignment(SwingConstants.LEFT);
        lblNome.setForeground(AppColors.PARCHMENT);

        txtNome.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtNome.setOpaque(false);
        txtNome.setForeground(Color.WHITE);
        txtNome.setCaretColor(AppColors.PARCHMENT);
        txtNome.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txtNome.setPreferredSize(new Dimension(0, 28));

        lblNivel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblNivel.setText("Nível");
        lblNivel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNivel.setForeground(AppColors.PARCHMENT);

        txtNivel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtNivel.setOpaque(false);
        txtNivel.setForeground(Color.WHITE);
        txtNivel.setCaretColor(AppColors.PARCHMENT);
        txtNivel.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txtNivel.setPreferredSize(new Dimension(0, 28));

        lblRaca.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblRaca.setText("Raca");
        lblRaca.setHorizontalAlignment(SwingConstants.LEFT);
        lblRaca.setForeground(AppColors.PARCHMENT);

        cbxRaca.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cbxRaca.setForeground(Color.WHITE);
        cbxRaca.setBackground(AppColors.DARK);
        GenericUtils.estilizarComboBox(cbxRaca);

        lblClasse.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblClasse.setText("Classe");
        lblClasse.setHorizontalAlignment(SwingConstants.LEFT);
        lblClasse.setForeground(AppColors.PARCHMENT);

        cbxClasse.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cbxClasse.setForeground(Color.WHITE);
        cbxClasse.setBackground(AppColors.DARK);
        GenericUtils.estilizarComboBox(cbxClasse);

        lblDinheiro.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblDinheiro.setText("Dinheiro");
        lblDinheiro.setHorizontalAlignment(SwingConstants.LEFT);
        lblDinheiro.setForeground(AppColors.PARCHMENT);

        txtDinheiro.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtDinheiro.setOpaque(false);
        txtDinheiro.setForeground(Color.WHITE);
        txtDinheiro.setCaretColor(AppColors.PARCHMENT);
        txtDinheiro.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txtDinheiro.setPreferredSize(new Dimension(0, 28));

        lblCampanha.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblCampanha.setText("Campanha");
        lblCampanha.setHorizontalAlignment(SwingConstants.LEFT);
        lblCampanha.setForeground(AppColors.PARCHMENT);

        cbxCampanha.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cbxCampanha.setForeground(Color.WHITE);
        cbxCampanha.setBackground(AppColors.DARK);
        GenericUtils.estilizarComboBox(cbxCampanha);
        lblJogador.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblJogador.setText("Jogador");
        lblJogador.setForeground(AppColors.PARCHMENT);
        lblJogador.setHorizontalAlignment(SwingConstants.LEFT);

        GenericUtils.estilizarComboBox(cbxJogador);
        cbxJogador.setPreferredSize(new Dimension(0, 28));


        // Definições para Atributo base
        lblTituloAtributo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        lblTituloAtributo.setText("Atributo Base");
        lblTituloAtributo.setHorizontalAlignment(SwingConstants.LEFT);
        lblTituloAtributo.setForeground(AppColors.PARCHMENT);

        JSeparator sepEsquerda = new JSeparator();
        sepEsquerda.setForeground(AppColors.GOLD);
        sepEsquerda.setBackground(AppColors.GOLD);

        JSeparator sepDireita = new JSeparator();
        sepDireita.setForeground(AppColors.GOLD);
        sepDireita.setBackground(AppColors.GOLD);

        lblFor.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblFor.setText("For");
        lblFor.setForeground(AppColors.PARCHMENT);
        lblFor.setHorizontalAlignment(SwingConstants.LEFT);

        
        txtAtrFor.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtAtrFor.setText("18");
        txtAtrFor.setOpaque(false);
        txtAtrFor.setForeground(Color.WHITE);
        txtAtrFor.setCaretColor(AppColors.PARCHMENT);
        txtAtrFor.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txtAtrFor.setPreferredSize(new Dimension(30, 28));
        txtAtrFor.setHorizontalAlignment(SwingConstants.CENTER);


        lblDes.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblDes.setText("Des");
        lblDes.setForeground(AppColors.PARCHMENT);
        lblDes.setHorizontalAlignment(SwingConstants.LEFT);

        
        txtAtrDes.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtAtrDes.setText("12");
        txtAtrDes.setOpaque(false);
        txtAtrDes.setForeground(Color.WHITE);
        txtAtrDes.setCaretColor(AppColors.PARCHMENT);
        txtAtrDes.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txtAtrDes.setPreferredSize(new Dimension(30, 28));
        txtAtrDes.setHorizontalAlignment(SwingConstants.CENTER);


        lblCon.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblCon.setText("Con");
        lblCon.setForeground(AppColors.PARCHMENT);
        lblCon.setHorizontalAlignment(SwingConstants.LEFT);

        
        txtAtrCon.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtAtrCon.setText("16");
        txtAtrCon.setOpaque(false);
        txtAtrCon.setForeground(Color.WHITE);
        txtAtrCon.setCaretColor(AppColors.PARCHMENT);
        txtAtrCon.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txtAtrCon.setPreferredSize(new Dimension(30, 28));
        txtAtrCon.setHorizontalAlignment(SwingConstants.CENTER);


        lblInt.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblInt.setText("Int");
        lblInt.setForeground(AppColors.PARCHMENT);
        lblInt.setHorizontalAlignment(SwingConstants.LEFT);

        
        txtAtrInt.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtAtrInt.setText("8");
        txtAtrInt.setOpaque(false);
        txtAtrInt.setForeground(Color.WHITE);
        txtAtrInt.setCaretColor(AppColors.PARCHMENT);
        txtAtrInt.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txtAtrInt.setPreferredSize(new Dimension(30, 28));
        txtAtrInt.setHorizontalAlignment(SwingConstants.CENTER);


        lblSab.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSab.setText("Sab");
        lblSab.setForeground(AppColors.PARCHMENT);
        lblSab.setHorizontalAlignment(SwingConstants.LEFT);

        
        txtAtrSab.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtAtrSab.setText("10");
        txtAtrSab.setOpaque(false);
        txtAtrSab.setForeground(Color.WHITE);
        txtAtrSab.setCaretColor(AppColors.PARCHMENT);
        txtAtrSab.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txtAtrSab.setPreferredSize(new Dimension(30, 28));
        txtAtrSab.setHorizontalAlignment(SwingConstants.CENTER);


        lblCar.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblCar.setText("Car");
        lblCar.setForeground(AppColors.PARCHMENT);
        lblCar.setHorizontalAlignment(SwingConstants.LEFT);

        
        txtAtrCar.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtAtrCar.setText("9");
        txtAtrCar.setOpaque(false);
        txtAtrCar.setForeground(Color.WHITE);
        txtAtrCar.setCaretColor(AppColors.PARCHMENT);
        txtAtrCar.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txtAtrCar.setPreferredSize(new Dimension(30, 28));
        txtAtrCar.setHorizontalAlignment(SwingConstants.CENTER);


        // Definições para
        JSeparator sepEsquerda2 = new JSeparator();
        sepEsquerda2.setForeground(AppColors.GOLD);
        sepEsquerda2.setBackground(AppColors.GOLD);

        JSeparator sepDireita2 = new JSeparator();
        sepDireita2.setForeground(AppColors.GOLD);
        sepDireita2.setBackground(AppColors.GOLD);

        lblTituloStatus.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        lblTituloStatus.setText("Status de Combate");
        lblTituloStatus.setHorizontalAlignment(SwingConstants.LEFT);
        lblTituloStatus.setForeground(AppColors.PARCHMENT);


        lblVida.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblVida.setText("Vida");
        lblVida.setForeground(AppColors.PARCHMENT);
        lblVida.setHorizontalAlignment(SwingConstants.LEFT);

        lblStatusVida.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblStatusVida.setText("150");
        lblStatusVida.setForeground(AppColors.PARCHMENT);
        lblStatusVida.setHorizontalAlignment(SwingConstants.LEFT);

        lblAtaque.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblAtaque.setText("Ataque");
        lblAtaque.setForeground(AppColors.PARCHMENT);
        lblAtaque.setHorizontalAlignment(SwingConstants.LEFT);

        lblStatusAtaque.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblStatusAtaque.setText("200");
        lblStatusAtaque.setForeground(AppColors.PARCHMENT);
        lblStatusAtaque.setHorizontalAlignment(SwingConstants.LEFT);

        lblDefesa.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblDefesa.setText("Defesa");
        lblDefesa.setForeground(AppColors.PARCHMENT);
        lblDefesa.setHorizontalAlignment(SwingConstants.LEFT);

        lblStatusDefesa.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblStatusDefesa.setText("300");
        lblStatusDefesa.setForeground(AppColors.PARCHMENT);
        lblStatusDefesa.setHorizontalAlignment(SwingConstants.LEFT);

        lblHpAtual.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblHpAtual.setText("HP Atual");
        lblHpAtual.setSize(400, 150);
        lblHpAtual.setVisible(true);
        lblHpAtual.setForeground(AppColors.PARCHMENT);

        pbrHpAtual.setValue(400);
        pbrHpAtual.setString("400 / 500");
        pbrHpAtual.setStringPainted(true);
        pbrHpAtual.setForeground(new Color(34, 139, 34)); // verde
        pbrHpAtual.setBackground(AppColors.DARK);
        pbrHpAtual.setBorder(BorderFactory.createLineBorder(AppColors.GOLD, 1));
        pbrHpAtual.setFont(new Font("SansSerif", Font.BOLD, 12));
        pbrHpAtual.setPreferredSize(new Dimension(0, 24));

        // private void atualizarCor(int vida) {
        //     if (vida > 60) {
        //         barraVida.setForeground(Color.GREEN);
        //     } else if (vida > 30) {
        //         barraVida.setForeground(Color.ORANGE);
        //     } else {
        //         barraVida.setForeground(Color.RED);
        //     }

        //Arma equipada
        lblTituloArma.setIcon(FontIcon.of(FontAwesomeSolid.SHIELD_ALT, AppColors.ICON_LG, AppColors.GOLD));
        lblTituloArma.setText(" ARMA EQUIPADA");
        lblTituloArma.setFont(new Font("Serif", Font.BOLD, 18));
        lblTituloArma.setForeground(AppColors.GOLD);
        lblTituloArma.setHorizontalAlignment(SwingConstants.CENTER);

        JSeparator sepArma = new JSeparator();
        sepArma.setForeground(AppColors.GOLD);
        sepArma.setBackground(AppColors.GOLD);

        lblNomeArma.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblNomeArma.setText("Nome Arma");
        lblNomeArma.setForeground(AppColors.PARCHMENT);
        lblNomeArma.setHorizontalAlignment(SwingConstants.LEFT);

        cbxArma.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cbxArma.setForeground(Color.WHITE);
        cbxArma.setBackground(AppColors.DARK);
        GenericUtils.estilizarComboBox(cbxArma);

        lblDanoArma.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblDanoArma.setText("Dano");
        lblDanoArma.setForeground(AppColors.PARCHMENT);
        lblDanoArma.setHorizontalAlignment(SwingConstants.LEFT);

        txtDanoArma.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtDanoArma.setOpaque(false);
        txtDanoArma.setForeground(Color.WHITE);
        txtDanoArma.setCaretColor(AppColors.PARCHMENT);
        txtDanoArma.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txtDanoArma.setPreferredSize(new Dimension(0, 28));
        txtDanoArma.setEditable(false);
        txtDanoArma.setFocusable(false);

        lblAlcanceArma.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblAlcanceArma.setText("Alcance ");
        lblAlcanceArma.setForeground(AppColors.PARCHMENT);
        lblAlcanceArma.setHorizontalAlignment(SwingConstants.LEFT);

        txtAlcanceArma.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtAlcanceArma.setOpaque(false);
        txtAlcanceArma.setForeground(Color.WHITE);
        txtAlcanceArma.setCaretColor(AppColors.PARCHMENT);
        txtAlcanceArma.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txtAlcanceArma.setPreferredSize(new Dimension(0, 28));
        txtAlcanceArma.setEditable(false);
        txtAlcanceArma.setFocusable(false);

        lblDurabilidadeArma.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblDurabilidadeArma.setText("Durabilidade");
        lblDurabilidadeArma.setForeground(AppColors.PARCHMENT);
        lblDurabilidadeArma.setHorizontalAlignment(SwingConstants.LEFT);

        txtDurabilidadeArma.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtDurabilidadeArma.setOpaque(false);
        txtDurabilidadeArma.setForeground(Color.WHITE);
        txtDurabilidadeArma.setCaretColor(AppColors.PARCHMENT);
        txtDurabilidadeArma.setBorder(BorderFactory.createLineBorder(AppColors.GOLD));
        txtDurabilidadeArma.setPreferredSize(new Dimension(0, 28));
        txtDurabilidadeArma.setEditable(false);
        txtDurabilidadeArma.setFocusable(false);

        lblDadoArma.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblDadoArma.setText("Tipo de Dado");
        lblDadoArma.setForeground(AppColors.PARCHMENT);
        lblDadoArma.setHorizontalAlignment(SwingConstants.LEFT);
        lblDadoArma.setPreferredSize(new Dimension(0, 28));

        cbxDadoArma.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cbxDadoArma.setForeground(Color.WHITE);
        cbxDadoArma.setBackground(AppColors.DARK);
        GenericUtils.estilizarComboBox(cbxDadoArma);
        cbxDadoArma.setEnabled(false);

        // INVENTARIO
        lblTituloInventario.setIcon(FontIcon.of(FontAwesomeSolid.SHOPPING_BAG, AppColors.ICON_LG, AppColors.GOLD));
        lblTituloInventario.setText(" INVENTÁRIO");
        lblTituloInventario.setFont(new Font("Serif", Font.BOLD, 18));
        lblTituloInventario.setForeground(AppColors.GOLD);
        lblTituloInventario.setHorizontalAlignment(SwingConstants.CENTER);

        JSeparator sepInventario = new JSeparator();
        sepInventario.setForeground(AppColors.GOLD);
        sepInventario.setBackground(AppColors.GOLD);

        tblInventario.setModel(new DefaultTableModel(new Object[][]{}, new Object[]{"", "ITEM", "TIPO", "RARIDADE", "GP"}));
        tblInventario.setDefaultRenderer(Object.class, new IconTextCellRender());
        tblInventario.getColumnModel().getColumn(0).setMaxWidth(30);
        tblInventario.setShowVerticalLines(false);
        tblInventario.setShowHorizontalLines(false);
        tblInventario.setBackground(AppColors.DARK);
        tblInventario.setForeground(AppColors.PARCHMENT);
        tblInventario.setSelectionBackground(AppColors.CRIMSON);
        tblInventario.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tblInventario.setRowHeight(28);
        JTableHeader header = tblInventario.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                lbl.setOpaque(true);
                lbl.setBackground(AppColors.DARK);
                lbl.setForeground(AppColors.GOLD);
                lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
                lbl.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, AppColors.GOLD));

                return lbl;
            }
        });

        scrInventario.setViewportView(tblInventario);
        scrInventario.setPreferredSize(new Dimension(0, 150));
        scrInventario.getViewport().setBackground(AppColors.DARK);
        scrInventario.setBorder(BorderFactory.createLineBorder(AppColors.GOLD, 1));
        scrInventario.getVerticalScrollBar().setUI(new DarkScrollBarUI());
        scrInventario.getHorizontalScrollBar().setUI(new DarkScrollBarUI());
        scrInventario.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        btnAdicionarInventario = new JButtonCustom(
                " Adicionar ao Inventário",
                JButtonCustom.Style.SECONDARY,
                FontIcon.of(FontAwesomeSolid.USER_PLUS, AppColors.ICON_SM, AppColors.PARCHMENT)
        );
        btnAdicionarInventario.addActionListener(_ -> controller.btnAdicionarInventarioClick());

        btnRemoverInventario = new JButtonCustom(
                "Remover do Inventário",
                JButtonCustom.Style.SECONDARY,
                FontIcon.of(FontAwesomeSolid.USER_PLUS, AppColors.ICON_SM, AppColors.PARCHMENT)
        );

        btnNovoPersonagem = new JButtonCustom(
                "Novo",
                JButtonCustom.Style.PRIMARY,
                FontIcon.of(FontAwesomeSolid.USER_PLUS, AppColors.ICON_SM, AppColors.PARCHMENT)
        );

        btnExcluirPersonagem = new JButtonCustom(
                "Excluir",
                JButtonCustom.Style.DANGER,
                FontIcon.of(FontAwesomeSolid.USER_MINUS, AppColors.ICON_SM, AppColors.PARCHMENT)
        );

        
        btnSalvar = new JButtonCustom(
                "Salvar",
                JButtonCustom.Style.PRIMARY,
                FontIcon.of(FontAwesomeSolid.SAVE, AppColors.ICON_SM, AppColors.PARCHMENT)
        );
       this.setLayout(new GridBagLayout());
        this.setBackground(AppColors.DARK);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 0),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        pnlFichaPersonagem.setLayout(new GridBagLayout());
        pnlFichaPersonagem.setBackground(AppColors.DARK);
        pnlFichaPersonagem.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(AppColors.GOLD, 1, 20),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        pnlArmaInventario.setLayout(new GridBagLayout());
        pnlArmaInventario.setBackground(AppColors.DARK);
        pnlArmaInventario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 0),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        pnlArmaPersonagem.setLayout(new GridBagLayout());
        pnlArmaPersonagem.setBackground(AppColors.DARK);
        pnlArmaPersonagem.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(AppColors.GOLD, 1, 20),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        pnlInventarioPersonagem.setLayout(new GridBagLayout());
        pnlInventarioPersonagem.setBackground(AppColors.DARK);
        pnlInventarioPersonagem.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(AppColors.GOLD, 1, 20),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        // Definições para pnlFichaPersonagem
        GridBagConstraints gbcPnlFichaPersonagem = new GridBagConstraints();

        // Definições lblTitulo
        gbcPnlFichaPersonagem.gridx = 0;
        gbcPnlFichaPersonagem.gridy = 0;
        gbcPnlFichaPersonagem.gridwidth = 6;
        gbcPnlFichaPersonagem.weightx = 1.0; // Define o peso de esticamento horizontal do componente quando a janela for redimensionada.
        gbcPnlFichaPersonagem.fill = GridBagConstraints.HORIZONTAL;
        gbcPnlFichaPersonagem.insets = new Insets(0, 0, 5, 0);
        pnlFichaPersonagem.add(lblTitulo, gbcPnlFichaPersonagem);

        // Definições para sepTituloFicha
        gbcPnlFichaPersonagem.gridy = 1;
        pnlFichaPersonagem.add(sepTituloFicha, gbcPnlFichaPersonagem);

        // Definições lblSelecionarPersonagem
        gbcPnlFichaPersonagem.gridy = 2;
        gbcPnlFichaPersonagem.gridwidth = 6;
        gbcPnlFichaPersonagem.weightx = 1.0;
        gbcPnlFichaPersonagem.insets = new Insets(5, 0, 0, 0);
        pnlFichaPersonagem.add(lblSelecionarPersonagem, gbcPnlFichaPersonagem);

        // Definições cbxSelecionarPersonagem
        gbcPnlFichaPersonagem.gridy = 3;
        gbcPnlFichaPersonagem.gridwidth = 6;
        gbcPnlFichaPersonagem.insets = new Insets(0, 0, 10, 0);
        pnlFichaPersonagem.add(cbxSelecionarPersonagem, gbcPnlFichaPersonagem);

        // Definições lblNome
        gbcPnlFichaPersonagem.gridy = 4;
        gbcPnlFichaPersonagem.gridwidth = 3;
        gbcPnlFichaPersonagem.weightx = 0.5;
        gbcPnlFichaPersonagem.insets = new Insets(5, 0, 0, 5);
        pnlFichaPersonagem.add(lblNome, gbcPnlFichaPersonagem);

        // Definições para lblNivel
        gbcPnlFichaPersonagem.gridx = 3; // Define a coluna onde o componente vai começar.
        gbcPnlFichaPersonagem.insets = new Insets(5, 5, 0, 0); // Define as margens externas (espaçamento) ao redor do componente.
        pnlFichaPersonagem.add(lblNivel, gbcPnlFichaPersonagem);

        // Definições para txtNome
        gbcPnlFichaPersonagem.gridx = 0;
        gbcPnlFichaPersonagem.gridy = 5;
        gbcPnlFichaPersonagem.insets = new Insets(0, 0, 5, 5);
        pnlFichaPersonagem.add(txtNome, gbcPnlFichaPersonagem);

        // Definições para txtNivel
        gbcPnlFichaPersonagem.gridx = 3; // Define a coluna onde o componente vai começar.
        gbcPnlFichaPersonagem.insets = new Insets(0, 5, 5, 0); // Define as margens externas (espaçamento) ao redor do componente.
        pnlFichaPersonagem.add(txtNivel, gbcPnlFichaPersonagem);

        // Definições para lblRaca
        gbcPnlFichaPersonagem.gridx = 0;
        gbcPnlFichaPersonagem.gridy = 6;
        gbcPnlFichaPersonagem.insets = new Insets(5, 0, 0, 5);
        pnlFichaPersonagem.add(lblRaca, gbcPnlFichaPersonagem);

        // Definições para lblClasse
        gbcPnlFichaPersonagem.gridx = 3;
        gbcPnlFichaPersonagem.insets = new Insets(5, 5, 0, 0);
        pnlFichaPersonagem.add(lblClasse, gbcPnlFichaPersonagem);

        // Definições para cbxRaca
        gbcPnlFichaPersonagem.gridx = 0;
        gbcPnlFichaPersonagem.gridy = 7;
        gbcPnlFichaPersonagem.insets = new Insets(0, 0, 5, 5);
        pnlFichaPersonagem.add(cbxRaca, gbcPnlFichaPersonagem);

        // Definições para cbxClasse
        gbcPnlFichaPersonagem.gridx = 3;
        gbcPnlFichaPersonagem.insets = new Insets(0, 5, 5, 0);
        pnlFichaPersonagem.add(cbxClasse, gbcPnlFichaPersonagem);

        // Definições para lblDinheiro
        gbcPnlFichaPersonagem.gridx = 0;
        gbcPnlFichaPersonagem.gridy = 8;
        gbcPnlFichaPersonagem.insets = new Insets(5, 0, 0, 5);
        pnlFichaPersonagem.add(lblDinheiro, gbcPnlFichaPersonagem);

        //Definições de lblCampanha
        gbcPnlFichaPersonagem.gridx = 3;
        gbcPnlFichaPersonagem.gridy = 8;
        gbcPnlFichaPersonagem.insets = new Insets(5, 5, 0, 0);
        pnlFichaPersonagem.add(lblCampanha, gbcPnlFichaPersonagem);

        // Definições de txtDinheiro
        gbcPnlFichaPersonagem.gridy = 9;
        gbcPnlFichaPersonagem.gridx = 0;
        gbcPnlFichaPersonagem.insets = new Insets(0, 0, 5, 5);
        pnlFichaPersonagem.add(txtDinheiro, gbcPnlFichaPersonagem);

        //Definições de cbxCampanha
        gbcPnlFichaPersonagem.gridx = 3;
        gbcPnlFichaPersonagem.gridy = 9;
        gbcPnlFichaPersonagem.insets = new Insets(0, 5, 5, 0);
        pnlFichaPersonagem.add(cbxCampanha, gbcPnlFichaPersonagem);
        gbcPnlFichaPersonagem.gridx = 0;
        gbcPnlFichaPersonagem.gridy = 10;
        gbcPnlFichaPersonagem.gridwidth = 6;
        gbcPnlFichaPersonagem.insets = new Insets(5, 0, 0, 0);
        pnlFichaPersonagem.add(lblJogador, gbcPnlFichaPersonagem);

        gbcPnlFichaPersonagem.gridy = 11;
        gbcPnlFichaPersonagem.insets = new Insets(0, 0, 5, 0);
        pnlFichaPersonagem.add(cbxJogador, gbcPnlFichaPersonagem);


        JPanel pnlAtributo = new JPanel(new GridBagLayout());
        pnlAtributo.setBackground(AppColors.DARK);
        pnlAtributo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 0),
                BorderFactory.createEmptyBorder(10, 0, 5, 0)
        ));

        GridBagConstraints gbcPnlAtributo = new GridBagConstraints();
        gbcPnlAtributo.gridy = 0;
        gbcPnlAtributo.fill = GridBagConstraints.HORIZONTAL;

        // sepEsquerda — coluna 0, estica
        gbcPnlAtributo.gridx = 0;
        gbcPnlAtributo.gridwidth = 1;
        gbcPnlAtributo.weightx = 1.0;
        pnlAtributo.add(sepEsquerda, gbcPnlAtributo);

        // lblTituloAtributo — coluna 1, tamanho natural
        gbcPnlAtributo.gridx = 1;
        gbcPnlAtributo.weightx = 0.0;
        gbcPnlAtributo.fill = GridBagConstraints.NONE;
        gbcPnlAtributo.anchor = GridBagConstraints.CENTER;
        gbcPnlAtributo.insets = new Insets(0, 10, 0, 10);
        pnlAtributo.add(lblTituloAtributo, gbcPnlAtributo);

        // sepDireita — coluna 2, estica
        gbcPnlAtributo.gridx = 2;
        gbcPnlAtributo.weightx = 1.0;
        gbcPnlAtributo.fill = GridBagConstraints.HORIZONTAL;
        gbcPnlAtributo.insets = new Insets(0, 0, 0, 0);
        pnlAtributo.add(sepDireita, gbcPnlAtributo);

        // Adiciona o painel no layout principal
        gbcPnlFichaPersonagem.gridx = 0;
        gbcPnlFichaPersonagem.gridy = 12;
        gbcPnlFichaPersonagem.gridwidth = 6;
        gbcPnlFichaPersonagem.insets = new Insets(0, 0, 0, 0);
        pnlFichaPersonagem.add(pnlAtributo, gbcPnlFichaPersonagem);

        //region Atributos Base
        // Adiciona painel para colocar atributo base de FOR
        JPanel pnlAtrFor = new JPanel(new GridBagLayout());
        pnlAtrFor.setOpaque(false);
        pnlAtrFor.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        GridBagConstraints gbcPnlAtrFor = new GridBagConstraints();
        gbcPnlAtrFor.gridy = 0;
        gbcPnlAtrFor.gridx = 0;
        gbcPnlAtrFor.gridwidth = 1;
        gbcPnlAtrFor.gridheight = 1;
        gbcPnlAtrFor.weightx = 1.0;
        gbcPnlAtrFor.weighty = 0.5;
        pnlAtrFor.add(lblFor, gbcPnlAtrFor);
        gbcPnlAtrFor.gridy = 1;
        pnlAtrFor.add(txtAtrFor, gbcPnlAtrFor);

        // Adiciona painel para colocar atributo base de Des
        JPanel pnlAtrDes = new JPanel(new GridBagLayout());
        pnlAtrDes.setOpaque(false);
        pnlAtrDes.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        GridBagConstraints gbcPnlAtrDes = new GridBagConstraints();
        gbcPnlAtrDes.gridy = 0;
        gbcPnlAtrDes.gridx = 0;
        gbcPnlAtrDes.gridwidth = 1;
        gbcPnlAtrDes.gridheight = 1;
        gbcPnlAtrDes.weightx = 1.0;
        gbcPnlAtrDes.weighty = 0.5;
        pnlAtrDes.add(lblDes, gbcPnlAtrDes);
        gbcPnlAtrDes.gridy = 1;
        pnlAtrDes.add(txtAtrDes, gbcPnlAtrDes);

        // Adiciona painel para colocar atributo base de Con
        JPanel pnlAtrCon = new JPanel(new GridBagLayout());
        pnlAtrCon.setOpaque(false);
        pnlAtrCon.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        GridBagConstraints gbcPnlAtrCon = new GridBagConstraints();
        gbcPnlAtrCon.gridy = 0;
        gbcPnlAtrCon.gridx = 0;
        gbcPnlAtrCon.gridwidth = 1;
        gbcPnlAtrCon.gridheight = 1;
        gbcPnlAtrCon.weightx = 1.0;
        gbcPnlAtrCon.weighty = 0.5;
        pnlAtrCon.add(lblCon, gbcPnlAtrCon);
        gbcPnlAtrCon.gridy = 1;
        pnlAtrCon.add(txtAtrCon, gbcPnlAtrCon);

        // Adiciona painel para colocar atributo base de Int
        JPanel pnlAtrInt = new JPanel(new GridBagLayout());
        pnlAtrInt.setOpaque(false);
        pnlAtrInt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        GridBagConstraints gbcPnlAtrInt = new GridBagConstraints();
        gbcPnlAtrInt.gridy = 0;
        gbcPnlAtrInt.gridx = 0;
        gbcPnlAtrInt.gridwidth = 1;
        gbcPnlAtrInt.gridheight = 1;
        gbcPnlAtrInt.weightx = 1.0;
        gbcPnlAtrInt.weighty = 0.5;
        pnlAtrInt.add(lblInt, gbcPnlAtrInt);
        gbcPnlAtrInt.gridy = 1;
        pnlAtrInt.add(txtAtrInt, gbcPnlAtrInt);

        // Adiciona painel para colocar atributo base de Sab
        JPanel pnlAtrSab = new JPanel(new GridBagLayout());
        pnlAtrSab.setOpaque(false);
        pnlAtrSab.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        GridBagConstraints gbcPnlAtrSab = new GridBagConstraints();
        gbcPnlAtrSab.gridy = 0;
        gbcPnlAtrSab.gridx = 0;
        gbcPnlAtrSab.gridwidth = 1;
        gbcPnlAtrSab.gridheight = 1;
        gbcPnlAtrSab.weightx = 1.0;
        gbcPnlAtrSab.weighty = 0.5;
        pnlAtrSab.add(lblSab, gbcPnlAtrSab);
        gbcPnlAtrSab.gridy = 1;
        pnlAtrSab.add(txtAtrSab, gbcPnlAtrSab);

        // Adiciona painel para colocar atributo base de Car
        JPanel pnlAtrCar = new JPanel(new GridBagLayout());
        pnlAtrCar.setOpaque(false);
        pnlAtrCar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        GridBagConstraints gbcPnlAtrCar = new GridBagConstraints();
        gbcPnlAtrCar.gridy = 0;
        gbcPnlAtrCar.gridx = 0;
        gbcPnlAtrCar.gridwidth = 1;
        gbcPnlAtrCar.gridheight = 1;
        gbcPnlAtrCar.weightx = 1.0;
        gbcPnlAtrCar.weighty = 0.5;
        pnlAtrCar.add(lblCar, gbcPnlAtrCar);
        gbcPnlAtrCar.gridy = 1;
        pnlAtrCar.add(txtAtrCar, gbcPnlAtrCar);
        //endregion

        JPanel pnlAtrBase = new JPanel(new GridBagLayout());
        pnlAtrBase.setBackground(AppColors.DARK);
        pnlAtrBase.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 0),
                BorderFactory.createEmptyBorder(5, 0, 5, 0)
        ));
        GridBagConstraints gbcPnlAtrBase = new GridBagConstraints();
        gbcPnlAtrBase.gridx = 0;
        gbcPnlAtrBase.gridy = 9;
        gbcPnlAtrBase.gridwidth = 1; //2
        gbcPnlAtrBase.weightx = 0.17; // 0.33
        gbcPnlAtrBase.fill = GridBagConstraints.HORIZONTAL;
        gbcPnlAtrBase.insets = new Insets(0, 0, 0, 5);
        pnlAtrBase.add(pnlAtrFor, gbcPnlAtrBase);

        gbcPnlAtrBase.gridx = 1;
        gbcPnlAtrBase.insets = new Insets(0, 5, 0, 5);
        pnlAtrBase.add(pnlAtrDes, gbcPnlAtrBase);

        gbcPnlAtrBase.gridx = 2;
        pnlAtrBase.add(pnlAtrCon, gbcPnlAtrBase);

        gbcPnlAtrBase.gridx = 3;
        pnlAtrBase.add(pnlAtrInt, gbcPnlAtrBase);

        gbcPnlAtrBase.gridx = 4;
        pnlAtrBase.add(pnlAtrSab, gbcPnlAtrBase);

        gbcPnlAtrBase.gridx = 5;
        gbcPnlAtrBase.insets = new Insets(0, 5, 0, 0);
        pnlAtrBase.add(pnlAtrCar, gbcPnlAtrBase);

        gbcPnlFichaPersonagem.gridx = 0;
        gbcPnlFichaPersonagem.gridy = 13;
        gbcPnlFichaPersonagem.gridwidth = 6;
        gbcPnlFichaPersonagem.insets = new Insets(5, 0, 5, 0);
        pnlFichaPersonagem.add(pnlAtrBase, gbcPnlFichaPersonagem);

        // pnl de titulo do status combate
        JPanel pnlStatusCombate = new JPanel(new GridBagLayout());
        pnlStatusCombate.setBackground(AppColors.DARK);
        pnlStatusCombate.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 0),
                BorderFactory.createEmptyBorder(10, 0, 5, 0)
        ));

        GridBagConstraints PnlStatusCombate = new GridBagConstraints();
        PnlStatusCombate.gridy = 0;
        PnlStatusCombate.fill = GridBagConstraints.HORIZONTAL;

        //sepEsquerda2 — coluna 0, estica
        PnlStatusCombate.gridx = 0;
        PnlStatusCombate.weightx = 1.0;
        PnlStatusCombate.gridwidth = 1;
        pnlStatusCombate.add(sepEsquerda2, PnlStatusCombate);

        // lblTituloAtributo — coluna 1, tamanho natural
        PnlStatusCombate.gridx = 1;
        PnlStatusCombate.weightx = 0.0;
        PnlStatusCombate.fill = GridBagConstraints.NONE;
        PnlStatusCombate.anchor = GridBagConstraints.CENTER;
        PnlStatusCombate.insets = new Insets(0, 10, 0, 10);
        pnlStatusCombate.add(lblTituloStatus, PnlStatusCombate);

        // sepDireita — coluna 2, estica
        PnlStatusCombate.gridx = 2;
        PnlStatusCombate.weightx = 1.0;
        PnlStatusCombate.fill = GridBagConstraints.HORIZONTAL;
        PnlStatusCombate.insets = new Insets(0, 0, 0, 0);
        pnlStatusCombate.add(sepDireita2, PnlStatusCombate);

        gbcPnlFichaPersonagem.gridx = 0;
        gbcPnlFichaPersonagem.gridy = 15;
        gbcPnlFichaPersonagem.gridwidth = 6;
        gbcPnlFichaPersonagem.insets = new Insets(10, 0, 5, 0);
        pnlFichaPersonagem.add(pnlStatusCombate, gbcPnlFichaPersonagem);

        //Panel de vida
        JPanel pnlVidaStatus = new JPanel(new GridBagLayout());
        pnlVidaStatus.setOpaque(false);
        pnlVidaStatus.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        GridBagConstraints gbcPnlVidaStatus = new GridBagConstraints();
        gbcPnlVidaStatus.gridy = 0;
        gbcPnlVidaStatus.gridx = 0;
        gbcPnlVidaStatus.gridwidth = 1;
        gbcPnlVidaStatus.gridheight = 1;
        gbcPnlVidaStatus.weightx = 1.0;
        gbcPnlVidaStatus.weighty = 0.5;
        pnlVidaStatus.add(lblVida, gbcPnlVidaStatus);
        gbcPnlVidaStatus.gridy = 1;
        pnlVidaStatus.add(lblStatusVida, gbcPnlVidaStatus);

        //Panel de ataque
        JPanel pnlAtaqueStatus = new JPanel(new GridBagLayout());
        pnlAtaqueStatus.setOpaque(false);
        pnlAtaqueStatus.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        GridBagConstraints gbcPnlAtaqueStatus = new GridBagConstraints();
        gbcPnlAtaqueStatus.gridy = 0;
        gbcPnlAtaqueStatus.gridx = 0;
        gbcPnlAtaqueStatus.gridwidth = 1;
        gbcPnlAtaqueStatus.gridheight = 1;
        gbcPnlAtaqueStatus.weightx = 1.0;
        gbcPnlAtaqueStatus.weighty = 0.5;
        pnlAtaqueStatus.add(lblAtaque, gbcPnlAtaqueStatus);
        gbcPnlAtaqueStatus.gridy = 1;
        pnlAtaqueStatus.add(lblStatusAtaque, gbcPnlAtaqueStatus);

        //panel para defesa
        JPanel pnlDefesaStatus = new JPanel(new GridBagLayout());
        pnlDefesaStatus.setOpaque(false);
        pnlDefesaStatus.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        GridBagConstraints gbcPnlDefesaStatus = new GridBagConstraints();
        gbcPnlDefesaStatus.gridy = 0;
        gbcPnlDefesaStatus.gridx = 0;
        gbcPnlDefesaStatus.gridwidth = 1;
        gbcPnlDefesaStatus.gridheight = 1;
        gbcPnlDefesaStatus.weightx = 1.0;
        gbcPnlDefesaStatus.weighty = 0.5;
        pnlDefesaStatus.add(lblDefesa, gbcPnlDefesaStatus);
        gbcPnlDefesaStatus.gridy = 1;
        pnlDefesaStatus.add(lblStatusDefesa, gbcPnlDefesaStatus);

        JPanel pnlStatus = new JPanel(new GridBagLayout());
        pnlStatus.setOpaque(false);
        GridBagConstraints gbcPnlStatus = new GridBagConstraints();
        gbcPnlStatus.gridx = 0;
        gbcPnlStatus.gridy = 0;
        gbcPnlStatus.gridwidth = 1;
        gbcPnlStatus.weightx = 0.33;
        gbcPnlStatus.fill = GridBagConstraints.HORIZONTAL;
        gbcPnlStatus.insets = new Insets(0, 0, 0, 5);
        pnlStatus.add(pnlVidaStatus, gbcPnlStatus);

        gbcPnlStatus.gridx = 1;
        gbcPnlStatus.insets = new Insets(0, 5, 0, 5);
        pnlStatus.add(pnlAtaqueStatus, gbcPnlStatus);

        gbcPnlStatus.gridx = 2;
        gbcPnlStatus.insets = new Insets(0, 5, 0, 0);
        pnlStatus.add(pnlDefesaStatus, gbcPnlStatus);

        gbcPnlFichaPersonagem.gridy = 16;
        gbcPnlFichaPersonagem.insets = new Insets(5, 0, 5, 0);
        pnlFichaPersonagem.add(pnlStatus, gbcPnlFichaPersonagem);

        //Hp atual
        gbcPnlFichaPersonagem.gridy = 17;
        gbcPnlFichaPersonagem.gridx = 0;
        gbcPnlFichaPersonagem.gridwidth = 6;
        gbcPnlFichaPersonagem.weightx = 1.0;
        gbcPnlFichaPersonagem.insets = new Insets(5, 0, 0, 0);
        pnlFichaPersonagem.add(lblHpAtual, gbcPnlFichaPersonagem);

        gbcPnlFichaPersonagem.gridy = 18;
        gbcPnlFichaPersonagem.insets = new Insets(0, 0, 0, 0);
        pnlFichaPersonagem.add(pbrHpAtual, gbcPnlFichaPersonagem);

        // Definições para pnlArmaEquipada
        GridBagConstraints gbcPnlArmaPersonagem = new GridBagConstraints();
        gbcPnlArmaPersonagem.gridy = 0;
        gbcPnlArmaPersonagem.gridx = 0;
        gbcPnlArmaPersonagem.gridwidth = 3;
        gbcPnlArmaPersonagem.weightx = 1.0;
        gbcPnlArmaPersonagem.fill = GridBagConstraints.HORIZONTAL;
        gbcPnlArmaPersonagem.insets = new Insets(0, 0, 5, 0);
        pnlArmaPersonagem.add(lblTituloArma, gbcPnlArmaPersonagem);

        gbcPnlArmaPersonagem.gridy = 1;
        gbcPnlArmaPersonagem.insets = new Insets(0, 0, 5, 0);
        pnlArmaPersonagem.add(sepArma, gbcPnlArmaPersonagem);

        gbcPnlArmaPersonagem.gridy = 2;
        gbcPnlArmaPersonagem.insets = new Insets(5, 0, 0, 0);
        pnlArmaPersonagem.add(lblNomeArma, gbcPnlArmaPersonagem);

        gbcPnlArmaPersonagem.gridy = 3;
        gbcPnlArmaPersonagem.insets = new Insets(0, 0, 5, 0);
        pnlArmaPersonagem.add(cbxArma, gbcPnlArmaPersonagem);

        gbcPnlArmaPersonagem.gridy = 4;
        gbcPnlArmaPersonagem.weightx = 0.33;
        gbcPnlArmaPersonagem.gridwidth = 1;
        gbcPnlArmaPersonagem.insets = new Insets(5, 0, 0, 5);
        pnlArmaPersonagem.add(lblDanoArma, gbcPnlArmaPersonagem);

        gbcPnlArmaPersonagem.gridx = 1;
        gbcPnlArmaPersonagem.insets = new Insets(5, 5, 0, 5);
        pnlArmaPersonagem.add(lblAlcanceArma, gbcPnlArmaPersonagem);

        gbcPnlArmaPersonagem.gridx = 2;
        gbcPnlArmaPersonagem.insets = new Insets(5, 5, 0, 0);
        pnlArmaPersonagem.add(lblDurabilidadeArma, gbcPnlArmaPersonagem);

        gbcPnlArmaPersonagem.gridy = 5;
        gbcPnlArmaPersonagem.gridx = 0;
        gbcPnlArmaPersonagem.insets = new Insets(0, 0, 5, 5);
        pnlArmaPersonagem.add(txtDanoArma, gbcPnlArmaPersonagem);

        gbcPnlArmaPersonagem.gridx = 1;
        gbcPnlArmaPersonagem.insets = new Insets(0, 5, 5, 5);
        pnlArmaPersonagem.add(txtAlcanceArma, gbcPnlArmaPersonagem);

        gbcPnlArmaPersonagem.gridx = 2;
        gbcPnlArmaPersonagem.insets = new Insets(0, 5, 5, 0);
        pnlArmaPersonagem.add(txtDurabilidadeArma, gbcPnlArmaPersonagem);

        gbcPnlArmaPersonagem.gridy = 6;
        gbcPnlArmaPersonagem.gridx = 0;
        gbcPnlArmaPersonagem.gridwidth = 3;
        gbcPnlArmaPersonagem.insets = new Insets(5, 0, 0, 0);
        pnlArmaPersonagem.add(lblDadoArma, gbcPnlArmaPersonagem);

        gbcPnlArmaPersonagem.gridy = 7;
        gbcPnlArmaPersonagem.insets = new Insets(0, 0, 0, 0);
        pnlArmaPersonagem.add(cbxDadoArma, gbcPnlArmaPersonagem);

        // Definições para pnlInventarioPersonagem
        GridBagConstraints gbcPnlInventarioPersonagem = new GridBagConstraints();
        gbcPnlInventarioPersonagem.gridy = 0;
        gbcPnlInventarioPersonagem.gridx = 0;
        gbcPnlInventarioPersonagem.gridwidth = 2;
        gbcPnlInventarioPersonagem.weightx = 1.0;
        gbcPnlInventarioPersonagem.weighty = 0.0;
        gbcPnlInventarioPersonagem.fill = GridBagConstraints.HORIZONTAL;
        gbcPnlInventarioPersonagem.insets = new Insets(0, 0, 5, 0);
        pnlInventarioPersonagem.add(lblTituloInventario, gbcPnlInventarioPersonagem);

        gbcPnlInventarioPersonagem.gridy = 1;
        gbcPnlInventarioPersonagem.insets = new Insets(0, 0, 5, 0);
        pnlInventarioPersonagem.add(sepInventario, gbcPnlInventarioPersonagem);

        gbcPnlInventarioPersonagem.gridy = 2;
        gbcPnlInventarioPersonagem.weighty = 1.0;
        gbcPnlInventarioPersonagem.fill = GridBagConstraints.BOTH;
        gbcPnlInventarioPersonagem.insets = new Insets(5, 5, 5, 5);
        pnlInventarioPersonagem.add(scrInventario, gbcPnlInventarioPersonagem);

        gbcPnlInventarioPersonagem.gridy = 3;
        gbcPnlInventarioPersonagem.weighty = 0.0;
        gbcPnlInventarioPersonagem.fill = GridBagConstraints.HORIZONTAL;
        gbcPnlInventarioPersonagem.gridwidth = 1;
        gbcPnlInventarioPersonagem.weightx = 0.5;
        gbcPnlInventarioPersonagem.insets = new Insets(5, 0, 0, 5);
        pnlInventarioPersonagem.add(btnAdicionarInventario, gbcPnlInventarioPersonagem);

        gbcPnlInventarioPersonagem.gridx = 1;
        gbcPnlInventarioPersonagem.insets = new Insets(5, 5, 0, 0);
        pnlInventarioPersonagem.add(btnRemoverInventario, gbcPnlInventarioPersonagem);

        // Definições para pnlArmaInventario
        GridBagConstraints gbcPnlArmaInventario = new GridBagConstraints();
        gbcPnlArmaInventario.gridy = 0;
        gbcPnlArmaInventario.gridx = 0;
        gbcPnlArmaInventario.gridwidth = 1;
        gbcPnlArmaInventario.gridheight = 1;
        gbcPnlArmaInventario.weightx = 1.0;
        gbcPnlArmaInventario.weighty = 1.0;
        gbcPnlArmaInventario.fill = GridBagConstraints.BOTH;
        gbcPnlArmaInventario.insets = new Insets(0, 0, 5, 0);
        pnlArmaInventario.add(pnlArmaPersonagem, gbcPnlArmaInventario);

        gbcPnlArmaInventario.gridy = 1;
        gbcPnlArmaInventario.insets = new Insets(5, 0, 0, 0);
        pnlArmaInventario.add(pnlInventarioPersonagem, gbcPnlArmaInventario);

        // Definições para a tela de personagem
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weighty = 1.0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new  Insets(0, 0, 5, 5);
        this.add(pnlFichaPersonagem, gbc);

        // Definições para o lado esquerdo da tela
        gbc.gridx = 1;
        gbc.insets = new  Insets(0, 5, 5, 0);
        this.add(pnlArmaInventario, gbc);

        // Definições para os botões do rodapé
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new  Insets(5, 0, 0, 5);
        this.add(btnNovoPersonagem, gbc);


        JPanel pnlBotoesDireita = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlBotoesDireita.setOpaque(false);
        pnlBotoesDireita.add(btnSalvar);
        pnlBotoesDireita.add(btnExcluirPersonagem);

        gbc.gridx = 1;
        gbc.insets = new Insets(5, 5, 0, 0);
        this.add(pnlBotoesDireita, gbc);

        controller.init();
    }
}
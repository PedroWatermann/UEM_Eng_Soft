package com.rpgpoo.RpgLog.view;

import com.rpgpoo.Gerenciador.Gerenciador;
import com.rpgpoo.utils.AppColors;
import com.rpgpoo.utils.DarkScrollBarUI;
import org.kordamp.ikonli.fontawesome6.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class RpgLogView extends JPanel {
    JLabel lblTitulo = new JLabel();
    JSeparator separador = new JSeparator();

    JTextArea txtLog = new JTextArea();
    JScrollPane scrLog = new JScrollPane();

    public RpgLogView(Gerenciador gerenciador) {
        lblTitulo.setIcon(FontIcon.of(FontAwesomeSolid.TERMINAL, AppColors.ICON_LG, AppColors.GOLD));
        lblTitulo.setText(" LOG");
        lblTitulo.setFont(new Font(Font.SERIF, Font.BOLD, 18));
        lblTitulo.setForeground(AppColors.GOLD);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        separador.setForeground(AppColors.GOLD);
        separador.setBackground(AppColors.GOLD);

        txtLog.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        txtLog.setOpaque(false);
        txtLog.setForeground(Color.GREEN);
        txtLog.setCaretColor(Color.GREEN);
        txtLog.setBorder(BorderFactory.createLineBorder(AppColors.PARCHMENT));
        txtLog.setLineWrap(true);
        txtLog.setWrapStyleWord(true);
        txtLog.setBorder(BorderFactory.createLineBorder(AppColors.PARCHMENT, 0));

        preencherLog_RetirarDepois(txtLog);

        scrLog.setViewportView(txtLog);
        scrLog.setPreferredSize(new Dimension(0, 150));
        scrLog.setMinimumSize(new Dimension(28, 150));
        scrLog.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        scrLog.getViewport().setBackground(Color.BLACK);
        scrLog.setBorder(BorderFactory.createLineBorder(AppColors.GOLD, 1));
        scrLog.getVerticalScrollBar().setUI(new DarkScrollBarUI());
        scrLog.getHorizontalScrollBar().setUI(new DarkScrollBarUI());
        scrLog.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.setLayout(new GridBagLayout());
        this.setBackground(AppColors.DARK);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GOLD, 0),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();

        // Definições para lblTitulo
        gbc.gridwidth = 1;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(lblTitulo, gbc);

        // Definições para separador
        gbc.gridy = 1;
        this.add(separador, gbc);

        // Definições para txtLog/scrLog
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 5, 5, 5);
        this.add(scrLog, gbc);
    }

    private void preencherLog_RetirarDepois(JTextArea log) {
        int contador = 0;

        while (contador < 300) {
            log.setText(log.getText() + " > " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " - Ação do jogador " + contador + '\n');

            contador++;
        }
    }
}

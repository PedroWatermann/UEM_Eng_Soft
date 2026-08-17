package com.rpgpoo.utils;

import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JButtonCustom extends JButton {
    public enum Style {
        PRIMARY,
        SECONDARY,
        DANGER
    }

    private static final int RAIO_CANTO_ARREDONDADO = 20;
    private static final int LARGURA_PADRAO = 160;
    private static final int ALTURA_PADRAO = 36;

    private Style estilo;
    private Color backgroundAtual;
    private Color backgroundHover;
    private Color corDaBorda;

    public JButtonCustom(String text, FontIcon icone) {
        this(icone != null && !text.isBlank() ? " " + text : text, Style.PRIMARY, icone);
    }

    public JButtonCustom(String text, Style style, FontIcon icone) {
        super(icone != null && !text.isBlank() ? " " + text : text);
        this.estilo = style;
        if (icone != null) this.setIcon(icone);
        aplicarEstilo();
        adicionarEfeitoHover();
    }

    private void aplicarEstilo() {
        switch (estilo) {
            case PRIMARY -> {
                backgroundAtual = AppColors.BG_PRIMARY;
                backgroundHover = AppColors.BG_HOVER_PRI;
                corDaBorda = AppColors.GOLD;
                setForeground(AppColors.PARCHMENT);
            }
            case SECONDARY -> {
                backgroundAtual = AppColors.BG_SECONDARY;
                backgroundHover = AppColors.BG_HOVER_SEC;
                corDaBorda = AppColors.GOLD;
                setForeground(AppColors.PARCHMENT);
            }
            case DANGER -> {
                backgroundAtual = AppColors.BG_DANGER;
                backgroundHover = AppColors.BG_HOVER_DAN;
                corDaBorda = AppColors.BORDER_DANGER;
                setForeground(AppColors.PARCHMENT);
            }
        }

        setFont(new Font("SansSerif", Font.BOLD, 12));
        setBackground(backgroundAtual);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setPreferredSize(new Dimension(LARGURA_PADRAO, ALTURA_PADRAO));
        setBorder(BorderFactory.createEmptyBorder(6, 20, 6, 20));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void adicionarEfeitoHover() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isEnabled()) setBackground(backgroundHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(backgroundAtual);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (isEnabled()) setBackground(backgroundHover.darker());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(isEnabled() ? backgroundHover : backgroundAtual);
            }
        });
    }

    public void setEstilo(Style estilo) {
        this.estilo = estilo;
        aplicarEstilo();
        repaint();
    }

    public void setSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), RAIO_CANTO_ARREDONDADO, RAIO_CANTO_ARREDONDADO);
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(corDaBorda);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, RAIO_CANTO_ARREDONDADO, RAIO_CANTO_ARREDONDADO);
        g2.dispose();
    }
}

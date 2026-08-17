package com.rpgpoo.utils;

import javax.swing.border.AbstractBorder;
import java.awt.*;

public class RoundedBorder extends AbstractBorder {
    private final Color cor;
    private final int espessura;
    private final int raio;

    public RoundedBorder(Color cor, int espessura, int raio) {
        this.cor = cor;
        this.espessura = espessura;
        this.raio = raio;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(cor);
        g2.setStroke(new BasicStroke(espessura));
        g2.drawRoundRect(x + espessura / 2, y + espessura / 2,
                width - espessura, height - espessura, raio, raio);
        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(espessura, espessura, espessura, espessura);
    }
}
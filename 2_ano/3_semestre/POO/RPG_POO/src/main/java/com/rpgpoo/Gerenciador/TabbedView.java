package com.rpgpoo.Gerenciador;

import com.rpgpoo.utils.AppColors;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class TabbedView extends JTabbedPane {

    public TabbedView() {
        this.setBackground(AppColors.DARK);
        this.setForeground(AppColors.PARCHMENT);
        this.setFont(new Font("SansSerif", Font.PLAIN, 12));

        this.setUI(new BasicTabbedPaneUI() {
            @Override
            protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex,
                                              int x, int y, int w, int h, boolean isSelected) {
                g.setColor(isSelected ? AppColors.DARK : AppColors.DARK.darker());
                g.fillRect(x, y, w, h);
            }

            @Override
            protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
                                          int x, int y, int w, int h, boolean isSelected) {
                g.setColor(isSelected ? AppColors.GOLD : AppColors.PARCHMENT.darker());
                g.drawRect(x, y, w, h);
            }

            @Override
            protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
                Insets insets = tabPane.getInsets();
                int x = insets.left;
                int y = insets.top + calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight) + 2;
                int w = tabPane.getWidth() - insets.left - insets.right;
                int h = tabPane.getHeight() - insets.top - insets.bottom - y;

                g.setColor(AppColors.GOLD);
                g.drawRect(x, y, w - 1, h - 1);
            }

            @Override
            protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
                return 32;
            }

            @Override
            protected Insets getTabInsets(int tabPlacement, int tabIndex) {
                return new Insets(4, 12, 4, 12);
            }

            @Override
            protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics,
                                     int tabIndex, String title, Rectangle textRect, boolean isSelected) {
                g.setFont(font);
                g.setColor(isSelected ? AppColors.GOLD : AppColors.PARCHMENT);
                g.drawString(title, textRect.x, textRect.y + metrics.getAscent());
            }

            @Override
            protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
                g.setColor(AppColors.DARK);
                g.fillRect(0, 0, tabPane.getWidth(), calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight));
                super.paintTabArea(g, tabPlacement, selectedIndex);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(AppColors.DARK);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    public void addAba(String titulo, Icon icone, Component conteudo) {
        this.addTab(titulo, icone, conteudo);
        int index = this.getTabCount() - 1;
        this.setForegroundAt(index, AppColors.PARCHMENT);
    }
}
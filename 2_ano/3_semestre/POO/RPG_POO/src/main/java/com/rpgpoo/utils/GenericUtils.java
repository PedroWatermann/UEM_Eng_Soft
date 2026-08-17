package com.rpgpoo.utils;

import org.kordamp.ikonli.fontawesome6.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;

public class GenericUtils {
    public static void estilizarComboBox(JComboBox<?> cbx) {
        cbx.setBackground(AppColors.DARK3);
        cbx.setForeground(AppColors.PARCHMENT);
        cbx.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cbx.setBorder(BorderFactory.createLineBorder(AppColors.GOLD, 1));

        cbx.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton btn = new JButton(FontIcon.of(FontAwesomeSolid.ARROW_DOWN, AppColors.ICON_SM, AppColors.PARCHMENT));
                btn.setBackground(AppColors.DARK3);
                btn.setForeground(AppColors.PARCHMENT);
                btn.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 4));
                btn.setFocusPainted(false);
                btn.setContentAreaFilled(false);
                return btn;
            }

            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup popup = (BasicComboPopup) super.createPopup();
                popup.setBorder(BorderFactory.createLineBorder(AppColors.GOLD, 1));
                popup.getList().setBackground(AppColors.DARK3);
                popup.getList().setForeground(AppColors.PARCHMENT);
                popup.getList().setSelectionBackground(AppColors.CRIMSON);
                popup.getList().setSelectionForeground(AppColors.PARCHMENT);
                return popup;
            }
        });

        Dimension pref = cbx.getPreferredSize();
        Dimension fixo = new Dimension(pref.width, 28);
        cbx.setPreferredSize(fixo);
        cbx.setMinimumSize(fixo);
    }
}

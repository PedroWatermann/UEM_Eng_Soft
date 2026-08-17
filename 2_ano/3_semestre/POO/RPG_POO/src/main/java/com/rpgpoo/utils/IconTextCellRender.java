package com.rpgpoo.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class IconTextCellRender extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value instanceof Icon) {
            label.setIcon((Icon) value);
            label.setText("");
        } else {
            label.setIcon(null);
            label.setText(value != null ? value.toString() : "");
        }

        label.setForeground(isSelected ? AppColors.GOLD : AppColors.PARCHMENT);
        label.setBackground(isSelected ? AppColors.CRIMSON : AppColors.DARK);
        label.setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 6));
        label.setOpaque(true);

        return label;
    }
}

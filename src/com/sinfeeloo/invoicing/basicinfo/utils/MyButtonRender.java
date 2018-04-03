package com.sinfeeloo.invoicing.basicinfo.utils;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyButtonRender implements TableCellRenderer {

    private JButton button;

    public MyButtonRender() {
        button = new JButton("ÐÞ¸Ä");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }

}
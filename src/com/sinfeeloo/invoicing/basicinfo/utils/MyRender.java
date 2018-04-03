package com.sinfeeloo.invoicing.basicinfo.utils;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//äÖÈ¾ Æ÷  ±à¼­Æ÷
public class MyRender extends AbstractCellEditor implements TableCellRenderer, ActionListener, TableCellEditor {
    private JButton button = null;
    private static final long serialVersionUID = 1L;
    public MyRender() {
        button = new JButton("ÐÞ¸Ä");
        button.addActionListener(this);
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "äÖÈ¾Æ÷Ñ§ÆÚ", "ÏûÏ¢", JOptionPane.OK_OPTION);

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return button;
    }

}
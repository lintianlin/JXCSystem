package com.sinfeeloo.invoicing.basicinfo.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LimitNumKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        int keyChar = e.getKeyChar();
        if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) || keyChar == 46) {//数字或者小数点

        } else {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
package com.sinfeeloo.invoicing.basicinfo.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OnlyNumKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        int keyChar = e.getKeyChar();
        if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) ) {//Êý×Ö

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
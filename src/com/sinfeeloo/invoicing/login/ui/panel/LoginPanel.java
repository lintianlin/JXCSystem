package com.sinfeeloo.invoicing.login.ui.panel;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: mhj
 * @Desc:µÇÂ½
 * @Date: 2018/3/28 15:19
 */
public class LoginPanel extends JPanel {
    protected ImageIcon icon;
    public int width,height;
    public LoginPanel() {
        super();
        icon = new ImageIcon("res/login.jpg");
        width = icon.getIconWidth();
        height = icon.getIconHeight();
        setSize(width, height);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = icon.getImage();
        g.drawImage(img, 0, 0,getParent());
    }
}

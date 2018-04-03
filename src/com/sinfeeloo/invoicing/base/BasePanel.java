package com.sinfeeloo.invoicing.base;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/3/29 10:59
 */
public abstract class BasePanel extends JPanel {


    public BasePanel() {
        super();
        initView();
    }

    protected abstract void initView();


    // 设置组件位置并添加到容器中
    protected void setupComponent(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {
        GridBagConstraints gridBagConstrains = new GridBagConstraints();
        gridBagConstrains.gridx = gridx;
        gridBagConstrains.gridy = gridy;
        gridBagConstrains.insets = new Insets(5, 1, 3, 1);
        if (gridwidth > 1)
            gridBagConstrains.gridwidth = gridwidth;
        if (ipadx > 0)
            gridBagConstrains.ipadx = ipadx;
        if (fill)
            gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
        add(component, gridBagConstrains);
    }
}

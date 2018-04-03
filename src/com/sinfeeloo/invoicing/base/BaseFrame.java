package com.sinfeeloo.invoicing.base;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/3/28 15:15
 */
public abstract class BaseFrame extends JFrame {


    public BaseFrame() {
        initView();
    }

    protected abstract void initView();

    // 设置组件位置并添加到容器中
    protected void setupComponet(JComponent component, int gridx, int gridy,
                                 int gridwidth, int ipadx, boolean fill) {
        final GridBagConstraints gridBagConstrains = new GridBagConstraints();
        gridBagConstrains.gridx = gridx;
        gridBagConstrains.gridy = gridy;
        if (gridwidth > 1)
            gridBagConstrains.gridwidth = gridwidth;
        if (ipadx > 0)
            gridBagConstrains.ipadx = ipadx;
        gridBagConstrains.insets = new Insets(5, 1, 3, 1);
        if (fill)
            gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(component, gridBagConstrains);
    }
}

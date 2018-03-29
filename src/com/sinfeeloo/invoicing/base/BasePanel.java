package com.sinfeeloo.invoicing.base;

import javax.swing.*;

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
}

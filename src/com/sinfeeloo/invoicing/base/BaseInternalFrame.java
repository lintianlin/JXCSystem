package com.sinfeeloo.invoicing.base;

import javax.swing.*;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/3/29 10:47
 */
public abstract class BaseInternalFrame extends JInternalFrame {

    public BaseInternalFrame() {
        initView();
    }

    protected abstract void initView();
}

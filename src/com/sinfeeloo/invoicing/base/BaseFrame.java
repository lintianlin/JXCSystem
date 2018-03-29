package com.sinfeeloo.invoicing.base;

import javax.swing.*;

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
}

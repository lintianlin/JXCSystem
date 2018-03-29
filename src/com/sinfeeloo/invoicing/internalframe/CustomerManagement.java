package com.sinfeeloo.invoicing.internalframe;

import com.sinfeeloo.invoicing.base.BaseInternalFrame;
import com.sinfeeloo.invoicing.basicinfo.ui.panel.AddCustomerPanel;
import com.sinfeeloo.invoicing.basicinfo.ui.panel.ModifyCustomerPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @Author: mhj
 * @Desc:客户信息管理
 * @Date: 2018/3/29 10:50
 */
public class CustomerManagement extends BaseInternalFrame {
    @Override
    protected void initView() {
        setIconifiable(true);
        setClosable(true);
        setTitle("客户信息管理");
        JTabbedPane tabPane = new JTabbedPane();
         ModifyCustomerPanel modifyPanel = new ModifyCustomerPanel();
         AddCustomerPanel addPanel = new AddCustomerPanel();
        tabPane.addTab("客户信息添加", null, addPanel, "客户添加");
        tabPane.addTab("客户信息修改与删除", null, modifyPanel, "修改与删除");
        getContentPane().add(tabPane);
        tabPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                modifyPanel.initComboBox();
            }
        });
        pack();
        setVisible(true);
    }
}

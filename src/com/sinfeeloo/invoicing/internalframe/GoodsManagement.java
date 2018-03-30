package com.sinfeeloo.invoicing.internalframe;

import com.sinfeeloo.invoicing.base.BaseInternalFrame;
import com.sinfeeloo.invoicing.basicinfo.ui.panel.AddGoodPanel;
import com.sinfeeloo.invoicing.basicinfo.ui.panel.ModifyGoodPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 * @Author: mhj
 * @Desc: 商品管理
 * @Date: 2018/3/30 10:53
 */
public class GoodsManagement extends BaseInternalFrame {
    @Override
    protected void initView() {
        setIconifiable(true);
        setClosable(true);
        setTitle("商品管理");
        JTabbedPane tabPane = new JTabbedPane();
        final AddGoodPanel addGoodPanel = new AddGoodPanel();
        final ModifyGoodPanel modifyGoodPanel = new ModifyGoodPanel();
        tabPane.addTab("商品信息添加", null, addGoodPanel, "商品添加");
        tabPane.addTab("商品信息修改与删除", null, modifyGoodPanel, "修改与删除");
        getContentPane().add(tabPane);
        tabPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
//                addGoodPanel.initComboBox();
                addGoodPanel.initSupplierBox();
            }
        });
        //在商品管理窗口被激活时，初始化商品添加界面的供应商下拉选择框
        addInternalFrameListener(new InternalFrameAdapter() {
            public void internalFrameActivated(InternalFrameEvent e) {
                super.internalFrameActivated(e);
                addGoodPanel.initSupplierBox();
            }
        });
        pack();
        setVisible(true);
    }
}

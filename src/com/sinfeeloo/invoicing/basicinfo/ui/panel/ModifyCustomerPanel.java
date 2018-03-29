package com.sinfeeloo.invoicing.basicinfo.ui.panel;

import com.sinfeeloo.invoicing.base.BasePanel;
import com.sinfeeloo.invoicing.base.listener.InputKeyListener;
import com.sinfeeloo.invoicing.basicinfo.dao.CustomerDao;
import com.sinfeeloo.invoicing.basicinfo.pojo.CustomerBean;
import com.sinfeeloo.invoicing.basicinfo.pojo.SelectItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/3/29 10:52
 */
public class ModifyCustomerPanel extends BasePanel {
    private JTextField customerFullName;
    private JTextField bankCardNum;
    private JTextField openBank;
    private JTextField email;
    private JTextField telephone;
    private JTextField contact;
    private JTextField address;
    private JTextField customerShortName;
    private JButton modifyButton;
    private JButton delButton;
    private JComboBox kehu;

    @Override
    protected void initView() {
        setBounds(10, 10, 460, 300);
        setLayout(new GridBagLayout());
        setVisible(true);


        //客户全称
        setupComponet(new JLabel("客户全称："), 0, 0, 1, 0, false);
        customerFullName = new JTextField();
        customerFullName.setEditable(false);
        // 定位全称文本框
        setupComponet(customerFullName, 1, 0, 3, 350, true);

        //客户地址
        setupComponet(new JLabel("客户地址："), 0, 1, 1, 0, false);
        address = new JTextField();
        // 定位地址文本框
        setupComponet(address, 1, 1, 3, 0, true);

        //客户简称
        setupComponet(new JLabel("客户简称："), 0, 2, 1, 0, false);
        customerShortName = new JTextField();
        // 定位客户简称文本框
        setupComponet(customerShortName, 1, 2, 1, 130, true);


        setupComponet(new JLabel("邮政编码："), 2, 2, 1, 0, false);

        //联系人
        setupComponet(new JLabel("联系人："), 0, 4, 1, 0, false);
        contact = new JTextField();
        // 定位联系人文本框
        setupComponet(contact, 1, 4, 1, 100, true);

        //手机号
        setupComponet(new JLabel("手机号："), 2, 4, 1, 0, false);
        telephone = new JTextField();
        // 定位联系电话文本框
        setupComponet(telephone, 3, 4, 1, 100, true);
        telephone.addKeyListener(new InputKeyListener());

        //邮箱
        setupComponet(new JLabel("邮箱："), 0, 5, 1, 0, false);
        email = new JTextField();
        // 定位E-Mail文本框
        setupComponet(email, 1, 5, 3, 350, true);

        //开户银行
        setupComponet(new JLabel("开户银行："), 0, 6, 1, 0, false);
        openBank = new JTextField();
        // 定位开户银行文本框
        setupComponet(openBank, 1, 6, 1, 100, true);

        //银行账号
        setupComponet(new JLabel("银行账号："), 2, 6, 1, 0, false);
        bankCardNum = new JTextField();
        // 定位银行账号文本框
        setupComponet(bankCardNum, 3, 6, 1, 100, true);

        //选择客户
        setupComponet(new JLabel("选择客户"), 0, 7, 1, 0, false);
        kehu = new JComboBox();
        kehu.setPreferredSize(new Dimension(230, 21));
        initComboBox();// 初始化下拉选择框

        // 处理客户信息的下拉选择框的选择事件
        kehu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doSelect();
            }
        });

        // 定位客户信息的下拉选择框
        setupComponet(kehu, 1, 7, 2, 0, true);
        modifyButton = new JButton("修改");
        delButton = new JButton("删除");
        JPanel panel = new JPanel();
        panel.add(modifyButton);
        panel.add(delButton);
        // 定位按钮
        setupComponet(panel, 3, 7, 1, 0, false);

        // 处理删除按钮的单击事件
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectItem item = (SelectItem) kehu.getSelectedItem();
                if (item == null || !(item instanceof SelectItem))
                    return;
                int confirm = JOptionPane.showConfirmDialog(
                        ModifyCustomerPanel.this, "确认删除客户信息吗？");
                if (confirm == JOptionPane.YES_OPTION) {
                    if (CustomerDao.newInstance().deleteCustomer(item.getId()) > 0) {
                        JOptionPane.showMessageDialog(ModifyCustomerPanel.this,
                                "客户：" + item.getName() + "。删除成功");
                        kehu.removeItem(item);
                    }
                }
            }
        });
        // 处理修改按钮的单击事件
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectItem item = (SelectItem) kehu.getSelectedItem();
                CustomerBean customerBean = new CustomerBean();
                customerBean.setId(Integer.parseInt(item.getId()));
                customerBean.setAddress(address.getText().trim());
                customerBean.setName(customerFullName.getText().trim());
                customerBean.setShortName(customerShortName.getText().trim());
                customerBean.setEmail(email.getText().trim());
                customerBean.setContact(contact.getText().trim());
                customerBean.setTelephone(telephone.getText().trim());
                customerBean.setOpenbank(openBank.getText().trim());
                customerBean.setBankcardnum(bankCardNum.getText().trim());
                if (CustomerDao.newInstance().updateCustomer(customerBean) == 1)
                    JOptionPane.showMessageDialog(ModifyCustomerPanel.this, "修改完成");
                else
                    JOptionPane.showMessageDialog(ModifyCustomerPanel.this, "修改失败");
            }
        });
    }


    /**
     * 初始化客户下拉选择框
     */
    public void initComboBox() {
        List<List<String>> customerList = CustomerDao.newInstance().getCustomerList();
        List<SelectItem> items = new ArrayList<>();
        kehu.removeAllItems();
        for (int i = 0; i < customerList.size(); i++) {
            kehu.addItem(new SelectItem(customerList.get(i).get(0), customerList.get(i).get(1)));
        }
        doSelect();
    }

    /**
     * 选中操作
     */
    private void doSelect() {
        SelectItem selectedItem;
        if (!(kehu.getSelectedItem() instanceof SelectItem)) {
            return;
        }
        selectedItem = (SelectItem) kehu.getSelectedItem();
        CustomerBean customerBean = CustomerDao.newInstance().getCustomerInfo(selectedItem.getId());
        customerFullName.setText(customerBean.getName());
        customerShortName.setText(customerBean.getShortName());
        address.setText(customerBean.getAddress());
        contact.setText(customerBean.getContact());
        telephone.setText(customerBean.getTelephone());
        email.setText(customerBean.getEmail());
        openBank.setText(customerBean.getOpenbank());
        bankCardNum.setText(customerBean.getBankcardnum());
    }


    // 设置组件位置并添加到容器中
    private void setupComponet(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {
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
        add(component, gridBagConstrains);
    }
}

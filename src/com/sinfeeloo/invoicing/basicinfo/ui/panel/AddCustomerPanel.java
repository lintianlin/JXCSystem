package com.sinfeeloo.invoicing.basicinfo.ui.panel;

import com.sinfeeloo.invoicing.base.BasePanel;
import com.sinfeeloo.invoicing.base.listener.InputKeyListener;
import com.sinfeeloo.invoicing.basicinfo.dao.CustomerDao;
import com.sinfeeloo.invoicing.basicinfo.pojo.CustomerBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: mhj
 * @Desc:添加客户
 * @Date: 2018/3/29 10:52
 */
public class AddCustomerPanel extends BasePanel {
    private JTextField customerFullName;
    private JTextField bankCardNum;
    private JTextField openBank;
    private JTextField email;
    private JTextField telephone;
    private JTextField contact;
    private JTextField address;
    private JTextField customerShortName;
    private JButton resetButton;

    @Override
    protected void initView() {
        setBounds(10, 10, 460, 300);
        setLayout(new GridBagLayout());
        setVisible(true);
        //客户全称
        final JLabel fullNameLabel = new JLabel();
        fullNameLabel.setText("客户全称：");
        setupComponet(fullNameLabel, 0, 0, 1, 0, false);
        customerFullName = new JTextField();
        // 定位全称文本框
        setupComponet(customerFullName, 1, 0, 3, 350, true);

        //客户地址
        final JLabel addressLabel = new JLabel("客户地址：");
        setupComponet(addressLabel, 0, 1, 1, 0, false);
        address = new JTextField();
        // 定位地址文本框
        setupComponet(address, 1, 1, 3, 0, true);

        //客户简称
        setupComponet(new JLabel("客户简称："), 0, 2, 1, 0, false);
        customerShortName = new JTextField();
        // 定位客户简称文本框
        setupComponet(customerShortName, 1, 2, 1, 100, true);

        //联系人
        setupComponet(new JLabel("联系人："), 0, 4, 1, 0, false);
        contact = new JTextField();
        // 定位联系人文本框
        setupComponet(contact, 1, 4, 1, 100, true);

        //联系电话
        setupComponet(new JLabel("联系电话："), 2, 4, 1, 0, false);
        telephone = new JTextField();
        // 定位联系电话文本框
        setupComponet(telephone, 3, 4, 1, 100, true);
        telephone.addKeyListener(new InputKeyListener());

        //邮箱
        setupComponet(new JLabel("E-Mail："), 0, 5, 1, 0, false);
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

        //保存按钮
        JButton saveButton = new JButton("保存");
        // 定位保存按钮
        setupComponet(saveButton, 1, 7, 1, 0, false);
        //添加监听事件
        saveButton.addActionListener(new SaveButtonActionListener());

        //重置按钮
        JButton resetButton = new JButton("重置");
        // 定位重置按钮
        setupComponet(resetButton, 3, 7, 1, 0, false);
        //添加监听事件
        resetButton.addActionListener(new ResetButtonActionListener());
    }


    // 设置组件位置并添加到容器中
    private void setupComponet(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {
        final GridBagConstraints gridBagConstrains = new GridBagConstraints();
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


    // 保存按钮的事件监听类
    private final class SaveButtonActionListener implements ActionListener {
        public void actionPerformed(final ActionEvent e) {

            if(checkInfo()){
                CustomerBean customerBean = new CustomerBean();
                customerBean.setName(customerFullName.getText().trim());
                customerBean.setShortName(customerShortName.getText().trim());
                customerBean.setAddress(address.getText().trim());
                customerBean.setTelephone(telephone.getText().trim());
                customerBean.setContact(contact.getText().trim());
                customerBean.setEmail(email.getText().trim());
                customerBean.setOpenbank(openBank.getText().trim());
                customerBean.setBankcardnum(openBank.getText().trim());

                if (CustomerDao.newInstance().addCustomer(customerBean)) {
                    JOptionPane.showMessageDialog(AddCustomerPanel.this, "添加成功！",
                            "客户添加信息", JOptionPane.INFORMATION_MESSAGE);
                    //重置
                    reset();
                } else {
                    JOptionPane.showMessageDialog(AddCustomerPanel.this, "添加失败！",
                            "客户添加信息", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }


        /**
         * 检查数据
         */
        private boolean checkInfo() {
            if ("".equals(customerFullName.getText())) {
                JOptionPane.showMessageDialog(null, "请填写客户全称！");
                return false;
            }
            if ("".equals(address.getText())) {
                JOptionPane.showMessageDialog(null, "请填写客户地址！");
                return false;
            }
            if ("".equals(customerShortName.getText())) {
                JOptionPane.showMessageDialog(null, "请填写客户简称！");
                return false;
            }
            if ("".equals(telephone.getText())) {
                JOptionPane.showMessageDialog(null, "请填写手机号！");
                return false;
            }
            if (telephone.getText().length() != 11) {
                JOptionPane.showMessageDialog(null, "手机号格式不正确！");
                return false;
            }
            //通过手机号判断该客户是否存在
            if (CustomerDao.newInstance().isHaveThisCustomer(telephone.getText().trim())) {
                JOptionPane.showMessageDialog(AddCustomerPanel.this,
                        "手机号已存在！", "客户添加信息",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            if ("".equals(contact.getText())) {
                JOptionPane.showMessageDialog(null, "请填写联系人！");
                return false;
            }
            return true;

        }
    }

    // 重置按钮的事件监听类
    private final class ResetButtonActionListener implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            reset();
        }
    }

    /**
     * 重置
     */
    public void reset() {
        customerFullName.setText("");
        customerShortName.setText("");
        address.setText("");
        email.setText("");
        contact.setText("");
        telephone.setText("");
        openBank.setText("");
        bankCardNum.setText("");
    }
}

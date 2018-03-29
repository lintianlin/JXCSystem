package com.sinfeeloo.invoicing.login.ui;

import com.sinfeeloo.invoicing.Main;
import com.sinfeeloo.invoicing.base.BaseUi;
import com.sinfeeloo.invoicing.login.dao.LoginDao;
import com.sinfeeloo.invoicing.login.pojo.UserBean;
import com.sinfeeloo.invoicing.login.ui.panel.LoginPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @Author: mhj
 * @Desc: 登录界面窗体
 * @Date: 2018/3/28 15:14
 */
public class LoginUi extends BaseUi {
    private JLabel userLabel;
    private JLabel pwdLabel;
    private JButton exit;
    private JButton login;
    private static UserBean user;

    @Override
    protected void initView() {
        setTitle("登录企业进销存管理系统");
        //创建面板容器
        final JPanel panel = new LoginPanel();
        panel.setLayout(null);
        getContentPane().add(panel);//将面板容器添加到窗体中
        setBounds(300, 200, panel.getWidth(), panel.getHeight());

        //用户名标签
        userLabel = new JLabel();
        userLabel.setText("用户名：");
        userLabel.setBounds(100, 135, 200, 18);
        panel.add(userLabel);
        //用户名输入框
        final JTextField userName = new JTextField();
        userName.setBounds(150, 135, 200, 18);
        panel.add(userName);

        //密码标签
        pwdLabel = new JLabel();
        pwdLabel.setText("密  码：");
        pwdLabel.setBounds(100, 165, 200, 18);
        panel.add(pwdLabel);
        //密码输入框
        final JPasswordField userPassword = new JPasswordField();
        userPassword.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == 10)
                    login.doClick();
            }
        });
        userPassword.setBounds(150, 165, 200, 18);
        panel.add(userPassword);

        //登陆按钮监听
        login = new JButton();
        login.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                //操作数据库通过用户名查询该用户
                user = LoginDao.getUser(userName.getText(), userPassword.getText());
                if (user.getUsername() == null) {//如果用户或者密码错误，则将用户名和密码置为空
                    userName.setText(null);
                    userPassword.setText(null);
                    return;
                }
                setVisible(false);
                //如果用户存在密码正确，则打开系统主窗体
                new Main();
            }
        });
        login.setText("登录");
        login.setBounds(180, 195, 60, 18);
        panel.add(login);

        //退出按钮监听
        exit = new JButton();
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        exit.setText("退出");
        exit.setBounds(260, 195, 60, 18);
        panel.add(exit);
        setVisible(true);
        setResizable(false);//resizeable值为true时，表示在生成的窗体可以自由改变大小；resizeable值为false时，表示生成的窗体大小是由程序员决定的，用户不可以自由改变该窗体的大
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭
    }
}

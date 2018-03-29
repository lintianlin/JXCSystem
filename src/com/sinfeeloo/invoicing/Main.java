package com.sinfeeloo.invoicing;

import com.sinfeeloo.invoicing.login.ui.LoginUi;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private JPanel sysManagePanel;
    private JDesktopPane desktopPane;
    private JFrame frame;
    private JLabel backLabel;
    // 创建窗体的Map类型集合对象
    private Map<String, JInternalFrame> ifs = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginUi();
            }
        });
    }

    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Main() {
        //创建主窗体
        frame = new JFrame("企业进销存管理系统");
        frame.getContentPane().setBackground(new Color(170, 188, 120));
        frame.addComponentListener(new FrameListener());
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //创建背景标签
        backLabel = new JLabel();// 背景标签
        backLabel.setVerticalAlignment(SwingConstants.TOP);
        backLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateBackImage(); // 更新或初始化背景图片

        desktopPane = new JDesktopPane();
        desktopPane.add(backLabel, new Integer(Integer.MIN_VALUE));
        frame.getContentPane().add(desktopPane);

        //创建导航标签面板
        JTabbedPane navigationPanel = createNavigationPanel();
        frame.getContentPane().add(navigationPanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    /**
     * 更新背景图片的方法
     */
    private void updateBackImage() {
        if (backLabel != null) {
            int backWidth = this.frame.getWidth();
            int backHeight = frame.getHeight();
            backLabel.setSize(backWidth, backHeight);
            backLabel.setText("<html><body><image width='" + backWidth
                    + "' height='" + (backHeight - 110) + "' src="
                    + this.getClass().getResource("welcome.jpg")
                    + "'></img></body></html>");
        }
    }


    /**
     * 创建导航标签面板的方法
     *
     * @return
     */
    private JTabbedPane createNavigationPanel() {
        //创建导航标签面板
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFocusable(false);
        tabbedPane.setBackground(new Color(211, 230, 192));
        tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED));

        //基础信息管理面板
        JPanel baseInfoManagePanel = new JPanel();
        baseInfoManagePanel.setBackground(new Color(215, 223, 194));
        baseInfoManagePanel.setLayout(new BoxLayout(baseInfoManagePanel, BoxLayout.X_AXIS));
        baseInfoManagePanel.add(createFrameButton("客户信息管理", "CustomerManagement"));
        baseInfoManagePanel.add(createFrameButton("商品信息管理", "GoodsManagement"));
        baseInfoManagePanel.add(createFrameButton("供应商信息管理", "SupplierManagement"));

        //库存管理面板
        JPanel inventoryManagePanel = new JPanel();
        inventoryManagePanel.setBackground(new Color(215, 223, 194));
        inventoryManagePanel.setLayout(new BoxLayout(inventoryManagePanel, BoxLayout.X_AXIS));
        inventoryManagePanel.add(createFrameButton("库存盘点", "InventoryVerification"));
        inventoryManagePanel.add(createFrameButton("价格调整", "PriceAdjustment"));

        //销售管理面板
        JPanel sellManagePanel = new JPanel();
        sellManagePanel.setBackground(new Color(215, 223, 194));
        sellManagePanel.setLayout(new BoxLayout(sellManagePanel, BoxLayout.X_AXIS));
        sellManagePanel.add(createFrameButton("销售单", "SellOrder"));
        sellManagePanel.add(createFrameButton("销售退货", "RefundOrder"));

        //查询统计面板
        JPanel searchStatisticPanel = new JPanel();
        searchStatisticPanel.setBounds(0, 0, 600, 41);
        searchStatisticPanel.setName("searchStatisticPanel");
        searchStatisticPanel.setBackground(new Color(215, 223, 194));
        searchStatisticPanel.setLayout(new BoxLayout(searchStatisticPanel, BoxLayout.X_AXIS));
        searchStatisticPanel.add(createFrameButton("客户信息查询", "CustomerQuery"));
        searchStatisticPanel.add(createFrameButton("商品信息查询", "GoodsQuery"));
        searchStatisticPanel.add(createFrameButton("供应商信息查询", "SupplierQuery"));
        searchStatisticPanel.add(createFrameButton("销售信息查询", "SalesOrderQuery"));
        searchStatisticPanel.add(createFrameButton("销售退货查询", "RefundQuery"));
        searchStatisticPanel.add(createFrameButton("入库查询", "InStorageQuery"));
        searchStatisticPanel.add(createFrameButton("入库退货查询", "InStorageRefundQuery"));
        searchStatisticPanel.add(createFrameButton("销售排行", "SalesRank"));

        //进货管理面板
        JPanel stockManagePanel = new JPanel();
        stockManagePanel.setBackground(new Color(215, 223, 194));
        stockManagePanel.setLayout(new BoxLayout(stockManagePanel, BoxLayout.X_AXIS));
        stockManagePanel.add(createFrameButton("进货单", "Purchase"));
        stockManagePanel.add(createFrameButton("进货退货", "Refund"));

        //系统管理面板
        sysManagePanel = new JPanel();
        sysManagePanel.setBackground(new Color(215, 223, 194));
        sysManagePanel.setLayout(new BoxLayout(sysManagePanel, BoxLayout.X_AXIS));
        sysManagePanel.add(createFrameButton("操作员管理", "CzyGL"));
        sysManagePanel.add(createFrameButton("更改密码", "GengGaiMiMa"));
        sysManagePanel.add(createFrameButton("权限管理", "QuanManager"));

        tabbedPane.addTab("   基础信息管理   ", null, baseInfoManagePanel, "基础信息管理");
        tabbedPane.addTab("   进货管理   ", null, stockManagePanel, "进货管理");
        tabbedPane.addTab("   销售管理   ", null, sellManagePanel, "销售管理");
        tabbedPane.addTab("   查询统计   ", null, searchStatisticPanel, "查询统计");
        tabbedPane.addTab("   库存管理   ", null, inventoryManagePanel, "库存管理");
        tabbedPane.addTab("   系统管理   ", null, sysManagePanel, "系统管理");

        return tabbedPane;
    }


    /**
     * 顶部添加标签按钮(为内部窗体添加Action的方法)
     *
     * @param fName
     * @param cname
     * @return
     */
    private JButton createFrameButton(String fName, String cname) {
        String imgUrl = "res/ActionIcon/" + fName + ".png";
        String imgUrl_roll = "res/ActionIcon/" + fName + "_roll.png";
        String imgUrl_down = "res/ActionIcon/" + fName + "_down.png";
        Icon icon = new ImageIcon(imgUrl);
        Icon icon_roll = null;
        if (imgUrl_roll != null)
            icon_roll = new ImageIcon(imgUrl_roll);
        Icon icon_down = null;
        if (imgUrl_down != null)
            icon_down = new ImageIcon(imgUrl_down);

        Action action = new openFrameAction(fName, cname, icon);
        //创建按钮
        JButton button = new JButton(action);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setHideActionText(true);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        if (icon_roll != null)
            button.setRolloverIcon(icon_roll);
        if (icon_down != null)
            button.setPressedIcon(icon_down);

        return button;
    }


    /**
     * 主窗体菜单项的单击事件监听器
     */
    protected final class openFrameAction extends AbstractAction {
        private String frameName = null;

        private openFrameAction() {
        }

        public openFrameAction(String cname, String frameName, Icon icon) {
            this.frameName = frameName;
            putValue(Action.NAME, cname);
            putValue(Action.SHORT_DESCRIPTION, cname);
            putValue(Action.SMALL_ICON, icon);
        }

        public void actionPerformed(final ActionEvent e) {
            JInternalFrame jf = getIFrame(frameName);
            // 在内部窗体闭关时，从内部窗体容器ifs对象中清除该窗体。
            jf.addInternalFrameListener(new InternalFrameAdapter() {
                public void internalFrameClosed(InternalFrameEvent e) {
                    ifs.remove(frameName);
                }
            });
            if (jf.getDesktopPane() == null) {
                desktopPane.add(jf);
                jf.setVisible(true);
            }
            try {
                jf.setSelected(true);
            } catch (PropertyVetoException e1) {
                e1.printStackTrace();
            }
        }
    }


    /**
     * 获取内部窗体的唯一实例对象
     *
     * @param frameName
     * @return
     */
    private JInternalFrame getIFrame(String frameName) {
        JInternalFrame jf = null;
        if (!ifs.containsKey(frameName)) {
            try {
                Class fClass = Class.forName("internalFrame." + frameName);
                Constructor constructor = fClass.getConstructor(null);
                jf = (JInternalFrame) constructor.newInstance(null);
                ifs.put(frameName, jf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            jf = ifs.get(frameName);
        return jf;
    }


    /**
     * 窗体监听器
     */
    private final class FrameListener extends ComponentAdapter {
        public void componentResized(final ComponentEvent e) {
            updateBackImage();
        }
    }


}

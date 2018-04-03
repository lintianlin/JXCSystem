package com.sinfeeloo.invoicing.basicinfo.ui.panel;

import com.sinfeeloo.invoicing.base.BasePanel;
import com.sinfeeloo.invoicing.basicinfo.dao.GoodsDao;
import com.sinfeeloo.invoicing.basicinfo.listener.LimitNumKeyListener;
import com.sinfeeloo.invoicing.basicinfo.listener.OnlyNumKeyListener;
import com.sinfeeloo.invoicing.basicinfo.pojo.GoodsBean;
import com.sinfeeloo.invoicing.basicinfo.pojo.SelectItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @Author: mhj
 * @Desc:修改商品
 * @Date: 2018/3/30 10:55
 */
public class ModifyGoodPanel extends BasePanel {

    private JTextField tf_specification;
    private JTextField tf_stock;
    private JTextField tf_sellingprice;
    private JTextField tf_purchaseprice;
    private JTextField tf_category;
    private JTextField tf_brand;
    private JTextField tf_barCode;
    private JTextField tf_goodsCode;
    private JTextField tf_goodsName;
    private JComboBox cb_supplier;
    private int mSupplierId;
    private JTextField tf_comment;

    @Override
    protected void initView() {
        setLayout(new GridBagLayout());
        setBounds(10, 10, 550, 400);

        setupComponent(new JLabel("选择商品"), 0, 0, 1, 1, false);
        JComboBox jb_selectgood = new JComboBox();
        jb_selectgood.setPreferredSize(new Dimension(230, 21));
        // 处理客户信息的下拉选择框的选择事件
        jb_selectgood.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doSelectGoods();
            }
        });
        // 定位商品信息的下拉选择框
        setupComponent(jb_selectgood, 1, 0, 3, 1, true);


        //商品名称
        setupComponent(new JLabel("商品名称："), 0, 1, 1, 1, false);
        tf_goodsName = new JTextField();
        setupComponent(tf_goodsName, 1, 1, 3, 1, true);

        //商品编码
        setupComponent(new JLabel("商品编码："), 0, 2, 1, 1, false);
        tf_goodsCode = new JTextField();
        tf_goodsCode.addKeyListener(new OnlyNumKeyListener());
        setupComponent(tf_goodsCode, 1, 2, 3, 10, true);

        //商品条形码
        setupComponent(new JLabel("商品条形码："), 0, 3, 1, 1, false);
        tf_barCode = new JTextField();
        setupComponent(tf_barCode, 1, 3, 3, 300, true);

        //品牌
        setupComponent(new JLabel("品牌："), 0, 4, 1, 1, false);
        tf_brand = new JTextField();
        setupComponent(tf_brand, 1, 4, 1, 130, true);

        //品类
        setupComponent(new JLabel("品类："), 2, 4, 1, 1, false);
        tf_category = new JTextField();
        setupComponent(tf_category, 3, 4, 1, 1, true);

        //进货价
        setupComponent(new JLabel("进货价："), 0, 5, 1, 1, false);
        tf_purchaseprice = new JTextField();
        tf_purchaseprice.addKeyListener(new LimitNumKeyListener());
        setupComponent(tf_purchaseprice, 1, 5, 1, 1, true);

        //销售价
        setupComponent(new JLabel("销售价："), 2, 5, 1, 1, false);
        tf_sellingprice = new JTextField();
        tf_purchaseprice.addKeyListener(new LimitNumKeyListener());
        setupComponent(tf_sellingprice, 3, 5, 1, 1, true);

        //库存
        setupComponent(new JLabel("库存："), 0, 6, 1, 1, false);
        tf_stock = new JTextField();
        tf_stock.addKeyListener(new OnlyNumKeyListener());
        setupComponent(tf_stock, 1, 6, 3, 1, true);

        //供应商
        setupComponent(new JLabel("供应商："), 0, 7, 1, 1, false);
        cb_supplier = new JComboBox();
        cb_supplier.setMaximumRowCount(5);
        setupComponent(cb_supplier, 1, 7, 3, 1, true);

        //规格
        setupComponent(new JLabel("规格："), 0, 8, 1, 1, false);
        tf_specification = new JTextField();
        setupComponent(tf_specification, 1, 8, 3, 1, true);

        //备注
        setupComponent(new JLabel("备注："), 0, 9, 1, 1, false);
        tf_comment = new JTextField();
        setupComponent(tf_comment, 1, 9, 3, 1, true);


        //添加按钮
        JButton jButton = new JButton("添加");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                //检查数据
                if (checkInfo()) {
                    if (GoodsDao.getInstance().addGood(getData())) {
                        JOptionPane.showMessageDialog(ModifyGoodPanel.this,
                                "添加成功！", "商品添加", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                    } else {
                        JOptionPane.showMessageDialog(ModifyGoodPanel.this,
                                "添加失败！", "商品添加", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        setupComponent(jButton, 1, 10, 1, 1, false);

        final GridBagConstraints gridBagConstraints_20 = new GridBagConstraints();
        gridBagConstraints_20.weighty = 1.0;
        gridBagConstraints_20.insets = new Insets(0, 65, 0, 15);
        gridBagConstraints_20.gridy = 9;
        gridBagConstraints_20.gridx = 1;


        // 重添按钮的事件监听类
        JButton resetButton = new JButton("重置");
        setupComponent(resetButton, 2, 10, 1, 1, false);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                reset();
            }
        });
    }

    private void doSelectGoods() {
    }


    /**
     * 获取数据
     *
     * @return
     */
    private GoodsBean getData() {
        GoodsBean goodsBean = new GoodsBean();
        goodsBean.setGoodsname(tf_goodsName.getText().trim());
        goodsBean.setGoodscode(tf_goodsCode.getText().trim());
        goodsBean.setBarcode(tf_barCode.getText().trim());
        goodsBean.setBrandName(tf_brand.getText().trim());
        goodsBean.setBrandId(1);
        goodsBean.setCategoryName(tf_category.getText().trim());
        goodsBean.setCategoryId(1);
        goodsBean.setPurchaseprice(Double.parseDouble(tf_purchaseprice.getText().trim()));
        goodsBean.setSellingprice(Double.parseDouble(tf_sellingprice.getText().trim()));
        goodsBean.setStock(Integer.parseInt(tf_stock.getText().trim()));
        goodsBean.setSpecification(tf_specification.getText().trim());
        goodsBean.setSupplierid(mSupplierId);
        goodsBean.setComment(tf_comment.getText().trim());
        goodsBean.setStatus_("");
        return goodsBean;
    }

    /**
     * 重置
     */
    private void reset() {
        tf_purchaseprice.setText("");
        tf_barCode.setText("");
        tf_brand.setText("");
        tf_category.setText("");
        tf_goodsCode.setText("");
        tf_specification.setText("");
        tf_sellingprice.setText("");
        tf_stock.setText("");
        tf_goodsName.setText("");
        tf_comment.setText("");
    }


    /**
     * 检查数据
     */
    private boolean checkInfo() {
        if ("".equals(tf_goodsName.getText())) {
            JOptionPane.showMessageDialog(null, "请填写商品名称！");
            return false;
        }
        if ("".equals(tf_goodsCode.getText())) {
            JOptionPane.showMessageDialog(null, "请填写商品编码！");
            return false;
        }
        if ("".equals(tf_brand.getText())) {
            JOptionPane.showMessageDialog(null, "请填写商品品牌！");
            return false;
        }
        if ("".equals(tf_category.getText())) {
            JOptionPane.showMessageDialog(null, "请填写商品品类！");
            return false;
        }
        if ("".equals(tf_purchaseprice.getText())) {
            JOptionPane.showMessageDialog(null, "请填写商品进货价！");
            return false;
        }
        if ("".equals(tf_sellingprice.getText())) {
            JOptionPane.showMessageDialog(null, "请填写商品销售价格！");
            return false;
        }
        if ("".equals(tf_stock.getText())) {
            JOptionPane.showMessageDialog(null, "请填写商品库存！");
            return false;
        }
        if (GoodsDao.getInstance().isHaveThisGood(Integer.parseInt(tf_goodsCode.getText().trim()))) {
            JOptionPane.showMessageDialog(null, "该商品已存在");
            return false;
        }
        try {
            Double.parseDouble(tf_purchaseprice.getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "进货价格式错误！");
            return false;
        }
        try {
            Double.parseDouble(tf_sellingprice.getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "销售价格格式错误！");
            return false;
        }
        return true;

    }


    // 初始化商品下拉选择框
    public void initGoodsBox() {
        List<SelectItem> list = GoodsDao.getInstance().getSupplierList();
        cb_supplier.removeAllItems();
        for (SelectItem item : list) {
            cb_supplier.addItem(item);
        }
        doSelect();
    }


    // 初始化供应商下拉选择框
    public void initSupplierBox() {
        List<SelectItem> list = GoodsDao.getInstance().getSupplierList();
        cb_supplier.removeAllItems();
        for (SelectItem item : list) {
            cb_supplier.addItem(item);
        }
        doSelect();
    }

    /**
     * 选择供应商
     */
    private void doSelect() {
        SelectItem selectedItem = (SelectItem) cb_supplier.getSelectedItem();
        mSupplierId = selectedItem.getId();
    }
}

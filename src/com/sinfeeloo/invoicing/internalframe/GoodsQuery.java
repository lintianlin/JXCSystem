package com.sinfeeloo.invoicing.internalframe;

import com.sinfeeloo.invoicing.base.BaseInternalFrame;
import com.sinfeeloo.invoicing.basicinfo.dao.GoodsDao;
import com.sinfeeloo.invoicing.basicinfo.pojo.GoodsBean;
import com.sinfeeloo.invoicing.basicinfo.utils.*;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * @Author: mhj
 * @Desc: 商品查询
 * @Date: 2018/3/30 20:03
 */
public class GoodsQuery extends BaseInternalFrame {
    private JTable table;
    private JTextField jf_goodsName;
    private JTextField jf_goodsCode;
    private JComboBox jb_brand;
    private JComboBox jb_category;

    @Override
    protected void initView() {
        setIconifiable(true);
        setClosable(true);
        setTitle("商品信息查询");
        setMaximizable(true);
        getContentPane().setLayout(new GridBagLayout());
        setBounds(100, 100, 609, 375);

        //表格
        table = new JTable();
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(30);


        //设置表头居中显示
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        table.getTableHeader().setDefaultRenderer(hr);

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);

        //设置表头
        final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
        String[] tableHeads = new String[]{"序号", "商品名称", "商品编码", "商品条形码", "品牌",
                "品类", "规格", "进货价", "销售价", "库存", "供应商", "备注", "操作"};
        dftm.setColumnIdentifiers(tableHeads);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        //设置表格的编辑器
        table.getColumnModel().getColumn(12).setCellEditor(new MyRender());
        //设置表格的渲染器
        table.getColumnModel().getColumn(12).setCellRenderer(new MyRender());


        //JScrollPane是带有滚动条的面板。JScrollPane是Container类的子类，也是一种容器，但是只能添加一个组件。
        // JScrollPane的一般用法是先将一些组件添加到一个JPanel中，然后再把这个JPanel添加到JScrollPane中
         JScrollPane scrollPane = new JScrollPane(table);
        //GridBagLayout是一个灵活的布局管理器，部件如果想加入其中需借助GridBagConstraints
        final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
        gridBagConstraints_6.weighty = 1.0;//列的权重，通过这个属性来决定如何分配列的剩余空间
        gridBagConstraints_6.anchor = GridBagConstraints.NORTH;//同样是当组件不能填满其格时，通过 anchor来设置组件的位置，anchor有两种值，绝对和相对的值分别有 若干个，文档中有，可自行查看
        gridBagConstraints_6.insets = new Insets(0, 10, 0, 10);//当组件不能填满其格时，通过 insets来指定四周（即上下左右）所留空隙
        gridBagConstraints_6.fill = GridBagConstraints.BOTH;//当组件在其格内而不能撑满其格时，通过 fill的值来设定填充方式，有四个值
        gridBagConstraints_6.gridwidth = 8;//组件所占列数，也是组件的宽度
        gridBagConstraints_6.gridy = 2;//组件的纵坐标
        gridBagConstraints_6.gridx = 0;//组件的横坐标
        add(scrollPane, gridBagConstraints_6);

        //查询条件：商品名称
        setupComponet(new JLabel(" 商品名称："), 0, 0, 1, 1, false);
        jf_goodsName = new JTextField();
        setupComponet(jf_goodsName, 1, 0, 1, 140, true);

        //查询条件：商品编码
        setupComponet(new JLabel(" 商品编码："), 2, 0, 1, 1, false);
        jf_goodsCode = new JTextField();
        setupComponet(jf_goodsCode, 3, 0, 1, 140, true);

        //查询条件-品牌
        setupComponet(new JLabel(" 品牌："), 0, 1, 1, 1, false);
        jb_brand = new JComboBox();
        jb_brand.setModel(new DefaultComboBoxModel(new String[]{"无", "美的", "海尔", "奥克斯"}));
        setupComponet(jb_brand, 1, 1, 1, 30, true);

        //查询条件-品类
        setupComponet(new JLabel(" 品类："), 2, 1, 1, 1, false);
        jb_category = new JComboBox();
        jb_category.setModel(new DefaultComboBoxModel(new String[]{"无", "洗衣机", "电冰箱", "空调"}));
        setupComponet(jb_category, 3, 1, 1, 30, true);


        final JButton btn_query = new JButton("查询");
        btn_query.addActionListener(e -> {
            //查询
            query(dftm);
        });
        setupComponet(btn_query, 4, 1, 1, 1, false);

        final JButton btn_reset = new JButton("重置");
        btn_reset.addActionListener(e -> {
            //重置
            reset(dftm);
        });
        setupComponet(btn_reset, 5, 1, 1, 1, false);
    }


    private void query(DefaultTableModel dftm) {
        List<GoodsBean> goodsList = GoodsDao.getInstance().getGoodsList(jf_goodsName.getText().trim(), jf_goodsCode.getText().trim(), jb_brand.getSelectedItem().toString(), jb_category.getSelectedItem().toString());
        updateTable(goodsList, dftm);
    }

    private void reset(DefaultTableModel dftm) {
        jf_goodsName.setText("");
        jf_goodsCode.setText("");
        jb_brand.setSelectedIndex(0);
        jb_category.setSelectedIndex(0);
        List<GoodsBean> goodsList = GoodsDao.getInstance().getGoodsList(jf_goodsName.getText().trim(), jf_goodsCode.getText().trim(), jb_brand.getSelectedItem().toString(), jb_category.getSelectedItem().toString());
        updateTable(goodsList, dftm);
    }


    /**
     * 刷新列表
     *
     * @param list
     * @param dftm "序号", "商品名称", "商品编码", "商品条形码", "品牌",
     *             "品类", "规格", "进货价", "销售价", "库存", "供应商", "备注"
     */
    private void updateTable(List<GoodsBean> list, final DefaultTableModel dftm) {
        int num = dftm.getRowCount();
        for (int i = 0; i < num; i++)
            dftm.removeRow(0);

        for (GoodsBean bean : list) {
            Vector rowData = new Vector();
            rowData.add(bean.getId());
            rowData.add(bean.getGoodsname());
            rowData.add(bean.getGoodscode());
            rowData.add(bean.getBarcode());
            rowData.add(bean.getBrandName());
            rowData.add(bean.getCategoryName());
            rowData.add(bean.getSpecification());
            rowData.add(bean.getPurchaseprice());
            rowData.add(bean.getSellingprice());
            rowData.add(bean.getStock());
            rowData.add("");
            rowData.add(bean.getComment());
            dftm.addRow(rowData);
        }
    }

}

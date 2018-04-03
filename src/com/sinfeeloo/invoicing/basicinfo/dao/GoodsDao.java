package com.sinfeeloo.invoicing.basicinfo.dao;

import com.sinfeeloo.invoicing.base.utils.DbConnUtil;
import com.sinfeeloo.invoicing.base.utils.TextUtils;
import com.sinfeeloo.invoicing.basicinfo.pojo.CustomerBean;
import com.sinfeeloo.invoicing.basicinfo.pojo.GoodsBean;
import com.sinfeeloo.invoicing.basicinfo.pojo.SelectItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/3/30 14:08
 */
public class GoodsDao {

    //单例模式：保证该对象在程序中永远是唯一的，可以避免重复创建对象造成的系统内存被过多占用
    private static GoodsDao instance = null;

    public static GoodsDao getInstance() {
        if (null == instance) {
            instance = new GoodsDao();
        }
        return instance;
    }


    /**
     * 通过商品编码判断该商品是否存在
     *
     * @param goodsCode
     * @return
     */
    public boolean isHaveThisGood(int goodsCode) {       //从数据库中查询所需数据
        Connection conn = null;
        try {
            conn = DbConnUtil.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tb_goods where goodscode='" + goodsCode + "'");//执行SQL并返回结果集
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != conn)
                    conn.close(); //关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;  //返回结果
    }


    /**
     * 添加商品
     *
     * @param bean
     * @return
     */
    public boolean addGood(GoodsBean bean) {
        boolean result = false;
        Connection conn = null;
        try {
            conn = DbConnUtil.getConn();  //建立数据库连接
            String sql = "INSERT INTO tb_goods (barcode,goodscode,goodsname,brandid,brandname,categoryid,categoryname,purchaseprice,sellingprice,stock,status_,specification,supplierid,isdelete)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(sql);   //会抛出异常
            stmt.setString(1, bean.getBarcode());         //设置SQL语句第一个“？”的值
            stmt.setString(2, bean.getGoodscode());
            stmt.setString(3, bean.getGoodsname());
            stmt.setInt(4, bean.getBrandId());
            stmt.setString(5, bean.getBrandName());
            stmt.setInt(6, bean.getCategoryId());
            stmt.setString(7, bean.getCategoryName());
            stmt.setDouble(8, bean.getPurchaseprice());
            stmt.setDouble(9, bean.getSellingprice());
            stmt.setInt(10, bean.getStock());
            stmt.setString(11, bean.getStatus_());
            stmt.setString(12, bean.getSpecification());
            stmt.setInt(13, bean.getSupplierid());
            stmt.setInt(14, 1);//1是未删除
            int i = stmt.executeUpdate();            //执行插入数据操作，返回影响的行数
            if (i == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { //finally的用处是不管程序是否出现异常，都要执行finally语句，所以在此处关闭连接
            try {
                if (null != conn)
                    conn.close(); //关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 获取经销商列表
     *
     * @return
     */
    public List<SelectItem> getSupplierList() {       //从数据库中查询所需数据
        List<SelectItem> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DbConnUtil.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select code,name from tb_supplier");//执行SQL并返回结果集
            while (rs.next()) {
                SelectItem item = new SelectItem();
                item.setId(rs.getInt("code"));
                item.setName(rs.getString("name"));
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != conn)
                    conn.close(); //关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list; //返回结果
    }


    /**
     * 根据查询条件获取商品列表
     *
     * @return
     */
    public List<GoodsBean> getGoodsList(String goodsName, String goodsCode, String brandName, String categoryName) {       //从数据库中查询所需数据
        List<GoodsBean> list = new ArrayList<>();
        Connection conn = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM tb_goods WHERE");
        List<Object> objectList = addStatement(goodsName, goodsCode, brandName, categoryName, new ArrayList<Object>(), sql);
        try {
            conn = DbConnUtil.getConn();
            PreparedStatement statement = conn.prepareStatement(sql.toString());
            statementSetObj(statement, objectList);
            ResultSet rs = statement.executeQuery();//执行SQL并返回结果集
            while (rs.next()) {
                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setId(rs.getInt("id"));
                goodsBean.setGoodsname(rs.getString("goodsname"));
                goodsBean.setGoodscode(rs.getString("goodscode"));
                goodsBean.setBarcode(rs.getString("barcode"));
                goodsBean.setBrandId(rs.getInt("brandid"));
                goodsBean.setBrandName(rs.getString("brandname"));
                goodsBean.setCategoryId(rs.getInt("categoryid"));
                goodsBean.setCategoryName(rs.getString("categoryname"));
                goodsBean.setPurchaseprice(rs.getDouble("purchaseprice"));
                goodsBean.setSellingprice(rs.getDouble("sellingprice"));
                goodsBean.setStock(rs.getInt("stock"));
                goodsBean.setSpecification(rs.getString("specification"));
                goodsBean.setComment(rs.getString("comment_"));
                list.add(goodsBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != conn)
                    conn.close(); //关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list; //返回结果
    }

    private void statementSetObj(PreparedStatement pstm, List<Object> list) {
        if (list != null) {
            try {
                for (int i = 0; i < list.size(); i++) {
                    pstm.setObject(i + 1, list.get(i));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private List<Object> addStatement(String goodsName, String goodsCode, String brandName, String categoryName, List<Object> list, StringBuilder sql) {
        if (TextUtils.notEmpty(goodsName)) {
            sql.append(" goodsname like ? AND");
            list.add("%" + goodsName + "%");
        }

        if (TextUtils.notEmpty(goodsCode)) {
            sql.append(" goodscode = ? AND");
            list.add(goodsCode);
        }

        if (!"无".equals(brandName)) {
            sql.append(" brandname=? AND");
            list.add(brandName);
        }

        if (!"无".equals(categoryName)) {
            sql.append(" categoryname=? AND");
            list.add(categoryName);
        }

        sql.append(" isdelete=0");
        System.out.println(sql.toString());
        return list;

    }


}

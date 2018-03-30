package com.sinfeeloo.invoicing.basicinfo.dao;


import com.sinfeeloo.invoicing.base.utils.DbConnUtil;
import com.sinfeeloo.invoicing.basicinfo.pojo.CustomerBean;
import com.sinfeeloo.invoicing.basicinfo.pojo.SelectItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/3/29 13:57
 */
public class CustomerDao {

    //单例模式：保证该对象在程序中永远是唯一的，可以避免重复创建对象造成的系统内存被过多占用
    private static CustomerDao instance = null;

    public static CustomerDao getInstance() {
        if (null == instance) {
            instance = new CustomerDao();
        }
        return instance;
    }


    /**
     * 通过手机号判断该客户是否存在
     *
     * @param telephone
     * @return
     */
    public boolean isHaveThisCustomer(String telephone) {       //从数据库中查询所需数据
        Connection conn = null;
        try {
            conn = DbConnUtil.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tb_customer where telephone='" + telephone + "'");//执行SQL并返回结果集
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
        return false;                                             //返回结果
    }


    /**
     * 添加客户
     *
     * @param bean
     * @return
     */
    public boolean addCustomer(CustomerBean bean) {
        boolean result = false;
        Connection conn = null;
        try {
            conn = DbConnUtil.getConn();  //建立数据库连接
            String sql = "insert into tb_customer(name,shortname,address,telephone,contact,email,openbank,bankcardnum) " +
                    "values(?, ?, ?, ?,?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);   //会抛出异常
            stmt.setString(1, bean.getName());         //设置SQL语句第一个“？”的值
            stmt.setString(2, bean.getShortName());         //设置SQL语句第一个“？”的值
            stmt.setString(3, bean.getAddress());         //设置SQL语句第一个“？”的值
            stmt.setString(4, bean.getTelephone());         //设置SQL语句第一个“？”的值
            stmt.setString(5, bean.getContact());         //设置SQL语句第一个“？”的值
            stmt.setString(6, bean.getEmail());         //设置SQL语句第一个“？”的值
            stmt.setString(7, bean.getOpenbank());         //设置SQL语句第一个“？”的值
            stmt.setString(8, bean.getBankcardnum());         //设置SQL语句第一个“？”的值
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
     * 获取客户列表
     *
     * @return
     */
    public List<SelectItem> getCustomerList() {       //从数据库中查询所需数据
        List<SelectItem> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DbConnUtil.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select id,name from tb_customer");//执行SQL并返回结果集
            while (rs.next()) {
                SelectItem item = new SelectItem();
                item.setId(rs.getInt("id"));
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
     * 根据id读取客户信息
     *
     * @param id
     * @return
     */
    public CustomerBean getCustomerInfo(int id) {
        CustomerBean customerBean = new CustomerBean();
        Connection conn = null;
        String sql = "select * from tb_customer where id= ? ";
        try {
            conn = DbConnUtil.getConn();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                customerBean.setId(set.getInt("id"));
                customerBean.setName(set.getString("name").trim());
                customerBean.setShortName(set.getString("shortname") == null ? "" : set.getString("shortname").trim());
                customerBean.setAddress(set.getString("address").trim());
                customerBean.setTelephone(set.getString("telephone").trim());
                customerBean.setContact(set.getString("contact").trim());
                customerBean.setEmail(set.getString("email") == null ? "" : set.getString("email").trim());
                customerBean.setOpenbank(set.getString("openbank") == null ? "" : set.getString("openbank").trim());
                customerBean.setBankcardnum(set.getString("bankcardnum") == null ? "" : set.getString("bankcardnum").trim());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != conn)
                    conn.close(); //关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customerBean;
    }


    /**
     * 删除客户
     *
     * @param id
     * @return
     */
    public boolean deleteCustomer(int id) {
        boolean result = false;
        Connection conn = null;
        try {
            conn = DbConnUtil.getConn();
            String sql = "delete from tb_customer where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int i = stmt.executeUpdate();
            if (i == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != conn)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 修改客户信息
     *
     * @return
     */
    public boolean updateCustomer(CustomerBean customerBean) {
        boolean result = false;
        Connection conn = null;
        try {
            conn = DbConnUtil.getConn();
            String sql = "update tb_customer set name=?,address=?,shortname=?,telephone=?,contact=?,email=?,openbank=?,bankcardnum=? where id=?";  //update语句
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, customerBean.getName());                //设置SQL语句第一个"?"的参数值
            stmt.setString(2, customerBean.getAddress());
            stmt.setString(3, customerBean.getShortName());
            stmt.setString(4, customerBean.getTelephone());
            stmt.setString(5, customerBean.getContact());
            stmt.setString(6, customerBean.getEmail());
            stmt.setString(7, customerBean.getOpenbank());
            stmt.setString(8, customerBean.getBankcardnum());
            stmt.setInt(9, customerBean.getId());
            int flag = stmt.executeUpdate();  //执行修改操作，返回影响的行数
            if (flag == 1) {   //修改成功返回true
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != conn)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}

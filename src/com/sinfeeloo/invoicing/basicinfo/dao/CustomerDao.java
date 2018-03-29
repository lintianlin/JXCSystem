package com.sinfeeloo.invoicing.basicinfo.dao;


import com.sinfeeloo.invoicing.base.utils.DBUtils;
import com.sinfeeloo.invoicing.base.utils.DatabaseConnection;
import com.sinfeeloo.invoicing.basicinfo.pojo.CustomerBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/3/29 13:57
 */
public class CustomerDao {

    private static CustomerDao instance = null;

    private CustomerDao() {
    }

    public static CustomerDao newInstance() {
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
    public boolean isHaveThisCustomer(String telephone) {
        ResultSet resultSet = DBUtils.findForResultSet("select * from tb_customer where telephone='" + telephone + "'");
        try {
            if (resultSet.next()) {
                return true;
            }
            return false;

        } catch (Exception er) {
            er.printStackTrace();
        }

        return false;
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

            conn = DatabaseConnection.getConn();  //建立数据库连接
            String sql = "insert into tb_customer(name,shortname,address,telephone,contact,email,openbank,bankcardnum) values(?, ?, ?, ?,?, ?, ?, ?)";
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
                conn.close(); //打开一个Connection连接后，最后一定要调用它的close（）方法关闭连接，以释放系统资源及数据库资源
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
    public List<List<String>> getCustomerList() {
        List<List<String>> list = DBUtils.findForList("select id,name from tb_customer");
        return list;
    }

    /**
     * 根据id读取客户信息
     *
     * @param id
     * @return
     */
    public CustomerBean getCustomerInfo(String id) {
        CustomerBean customerBean = new CustomerBean();
        ResultSet set = DBUtils.findForResultSet("select * from tb_customer where id='" + id + "'");
        try {
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
        }
        return customerBean;
    }


    /**
     * 删除客户
     *
     * @param id
     * @return
     */
    public int deleteCustomer(String id) {
        return DBUtils.delete("delete tb_customer where id='" + id + "'");
    }


    /**
     * 修改客户信息
     *
     * @return
     */
    public int updateCustomer(CustomerBean customerBean) {
        return DBUtils.update("UPDATE tb_customer SET name='" + customerBean.getName()
                + "',address='" + customerBean.getAddress() + "',shortname='"
                + customerBean.getShortName() + "',telephone='" + customerBean.getTelephone() + "',contact='"
                + customerBean.getContact() + "',email='" + customerBean.getEmail() + "',openbank='"
                + customerBean.getOpenbank() + "',bankcardnum='" + customerBean.getBankcardnum()
                + "' where id='" + customerBean.getId() + "'");
    }
}

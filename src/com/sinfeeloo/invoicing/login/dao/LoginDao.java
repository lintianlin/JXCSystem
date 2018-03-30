package com.sinfeeloo.invoicing.login.dao;

import com.sinfeeloo.invoicing.base.utils.DbConnUtil;
import com.sinfeeloo.invoicing.login.pojo.UserBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/3/28 15:31
 */
public class LoginDao {

    private static LoginDao instance = null;

    private LoginDao() {
    }

    public static LoginDao getInstance() {
        if (null == instance) {
            instance = new LoginDao();
        }
        return instance;
    }

    /**
     * ∂¡»°”√ªß
     *
     * @param name
     * @param password
     * @return
     */
    public UserBean getUser(String name, String password) {
        UserBean user = new UserBean();
        Connection conn = null;
        String sql = "select * from tb_user where username=? and password=?";
        try {
            conn = DbConnUtil.getConn();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setComment(rs.getString("comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}

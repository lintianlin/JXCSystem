package com.sinfeeloo.invoicing.login.dao;

import com.sinfeeloo.invoicing.base.utils.DBUtils;
import com.sinfeeloo.invoicing.login.pojo.UserBean;

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

    public static LoginDao newInstance() {
        if (null == instance) {
            instance = new LoginDao();
        }
        return instance;
    }

    // ∂¡»°”√ªß
    public static UserBean getUser(String name, String password) {
        UserBean user = new UserBean();
        ResultSet rs = DBUtils.findForResultSet("select * from tb_user where username='" + name + "'");
        try {
            if (rs.next()) {
                user.setUsername(name);
                user.setPassword(rs.getString("password"));
                if (user.getPassword().equals(password)) {
                    user.setUsername(rs.getString("username"));
                    user.setComment(rs.getString("comment"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}

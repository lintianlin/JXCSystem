package com.sinfeeloo.invoicing.base.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mhj
 * @Desc:
 * @Date: 2018/3/28 15:35
 */
public class DBUtils {
    protected static String dbClassName = "com.mysql.jdbc.Driver";
    protected static String dbUrl = "jdbc:mysql://localhost:3306/newjxc?" + "useUnicode=true&characterEncoding=utf-8&useSSL=false";
    protected static String dbUser = "root";
    protected static String dbPwd = "root";
    protected static String second = null;

    private static DBUtils instance = null;
    public static Connection conn = null;

    private DBUtils() {
    }

    public static DBUtils newInstance() {
        if (null == instance) {
            instance = new DBUtils();
        }
        return instance;
    }

    static {
        try {
            if (conn == null) {
                Class.forName(dbClassName).newInstance();
                conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }


    /**
     * 查找
     *
     * @param sql
     * @return
     */
    public static ResultSet findForResultSet(String sql) {
        if (conn == null)
            return null;
        long time = System.currentTimeMillis();
        ResultSet rs = null;
        try {
            Statement stmt = null;
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            second = ((System.currentTimeMillis() - time) / 1000d) + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }


    /**
     * 插入
     *
     * @param sql
     * @return
     */
    public static boolean insert(String sql) {
        boolean result = false;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新
     *
     * @param sql
     * @return
     */
    public static int update(String sql) {
        int result = 0;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查列表
     *
     * @param sql
     * @return
     */
    public static List findForList(String sql) {
        List<List> list = new ArrayList<>();
        ResultSet rs = findForResultSet(sql);
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int colCount = metaData.getColumnCount();
            while (rs.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 1; i <= colCount; i++) {
                    String str = rs.getString(i);
                    if (str != null && !str.isEmpty())
                        str = str.trim();
                    row.add(str);
                }
                list.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

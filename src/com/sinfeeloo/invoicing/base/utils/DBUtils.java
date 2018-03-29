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

    protected static String second = null;

    private static DBUtils instance = null;

    private DBUtils() {
    }

    public static DBUtils newInstance() {
        if (null == instance) {
            instance = new DBUtils();
        }
        return instance;
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


    public static boolean add(PreparedStatement stmt) {
        try {




            int i = stmt.executeUpdate();            //执行插入数据操作，返回影响的行数
            if (i == 1) {
                return true;
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
        return false;
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
    public static List<List<String>> findForList(String sql) {
        List<List<String>> list = new ArrayList<>();
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


    // 执行指定查询
    public static ResultSet query(String QueryStr) {
        ResultSet set = findForResultSet(QueryStr);
        return set;
    }

    // 执行删除
    public static int delete(String sql) {
        return update(sql);
    }
}

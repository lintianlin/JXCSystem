package com.sinfeeloo.invoicing.base.utils;


import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnUtil {
    private static Connection conn = null;
    protected static String dbClassName = "com.mysql.jdbc.Driver";
    protected static String dbUrl = "jdbc:mysql://localhost:3306/newjxc?" + "useUnicode=true&characterEncoding=utf-8&useSSL=false";
    protected static String dbUser = "root";
    protected static String dbPwd = "root";
    protected static String second = null;

    public static Connection getConn() {
        try {
            Class.forName(dbClassName); //加载数据库连接驱动
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);  //获取连接
        } catch (Exception e) {
            System.out.println("连接数据库失败");
            e.printStackTrace();
        }
        return conn;
    }

}
package com.justvastness.webchat.Utils;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil {
    private static volatile MysqlDataSource DS = null;

    // 获取数据库连接池实例
    private static MysqlDataSource getDS() throws SQLException {
        if (DS == null) {
            synchronized (DBUtil.class) {
                if (DS == null) {
                    DS = new MysqlDataSource();
                    DS.setURL("jdbc:mysql://127.0.0.1:3306/chatroom");
                    DS.setUser("root");
                    DS.setPassword("xiaobai520..@@@");
                    DS.setUseSSL(false);
//                    DS.setUseUnicode(true);
                    DS.setCharacterEncoding("UTF-8");
                }
            }
        }
        return DS;
    }

    // 获取数据库连接
    public static Connection getConnection() throws SQLException {
        return getDS().getConnection();
    }

    // 关闭数据库连接
    public static void closeConnection(Connection connection, Statement statement, Resultset resultset) {
        try {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
//            if (resultset != null) resultset.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

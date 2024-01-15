package com.sun.qing.util;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
    static DataSource dataSource = null;
    private static ThreadLocal<Connection> threadLocal =new ThreadLocal<>();

    static Properties properties =  new Properties();
    static {
        InputStream stream = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() {
        Connection connection = threadLocal.get();
        if (connection == null){
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            threadLocal.set(connection);
        }
        return connection;
    }

    public static void releaseConnection() {
        Connection connection = threadLocal.get();
        if (connection != null) {
            threadLocal.remove();
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

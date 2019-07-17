package cn.learn.login.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
   Druid连接池工具类
 */
public class JDBCUtils {
    //定义成员变量
    private  static DataSource ds;
    //静态代码块
    static {


            //加载配置文件
            Properties prop = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取数据库连接池DataSource
        try {
            ds = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取连接
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 关闭DML（增删改）资源
     * @param stmt  释放执行sql语句的对象 Statement
     * @param conn  释放数据库连接对象 Connection
     */
    public static void close(Statement stmt,Connection conn){
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close(); //归还连接到连接池
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 关闭DQL（查询语句）资源
     * @param rs 释放结果集对象 ResultSet
     * @param stmt 释放执行SQL语句对象 Statement
     * @param conn 释放数据库连接对象 Connection
     */
    public static void close(ResultSet rs,Statement stmt, Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close(); //归还连接到连接池
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     *   获取连接池的方法
     */
    public static DataSource getDataSource(){
        return ds;
    }
}
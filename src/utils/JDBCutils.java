package utils;

import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @version 1.0
 * @author： 贾志豪
 * @date： 2021-09-24 08:32
 * JDBC 工具类 可以获取连接 关闭连接
 */
public class JDBCutils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;


//    private static Connection conn = null;

    private static LinkedList<Connection> connections = new LinkedList<>();

    static {
        try {
            InputStream is = JDBCutils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties p = new Properties();
            p.load(is);
            driver = p.getProperty("driver");
            url = p.getProperty("url");
            username = p.getProperty("username");
            password = p.getProperty("password");

            Class.forName(driver);
            for (int i = 0; i < 10; i++) {
                connections.add(DriverManager.getConnection(url, username, password));
                System.out.println("个连接" + connections);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {// 获取
        Connection pop = connections.pop();
        System.out.println("获取到连接" + pop + "，当前连接池中剩余" + connections.size() + "个连接");
        return pop;
    }

    /**
     * 来获取 getPreparedStatement 并且自动拼接参数的
     * conn   创建 getPreparedStatement 需要的数据库连接
     * sql    getPreparedStatement 创建时指定的需要预编译的sql
     * args    不想个数的参数列表, 需要按照占位符的顺序向这个方法中填充参数
     */
    public static PreparedStatement getPreparedStatement(Connection conn, String sql, Object... args) {
        PreparedStatement ps ;
        try {
             ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            return  ps;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection connection) {
        System.out.println("连接已归还,未关闭连接。没下次还能用");
        connections.addLast(connection);
    }

    public static void close(Connection connection, Statement statement) {
        System.out.println("连接已归还,未关闭连接。没下次还能用");
        connections.addLast(connection);
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection connection, Statement st, ResultSet rs) {
        System.out.println("连接已归还,未关闭连接。下次还能用");
        connections.addLast(connection);
        System.out.println("归还成功，当前连接池中剩余" + connections.size() + "个连接");
        try {
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
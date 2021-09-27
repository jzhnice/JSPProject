package day0924;

import utils.JDBCutils;
import java.sql.*;
/**
 * @version 1.0
 * @author： 贾志豪
 * @date： 2021-09-24 08:10
 */
public class Day0924 {
    private static Connection conn = null;
    /*静态代码块 只执行一次*/
            static{
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://127.0.0.1/homework";
                    conn = DriverManager.getConnection(url, "root", "root");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
    public static void main(String[] args) throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JDBCutils.getConn();
            st = conn.createStatement();
            rs = st.executeQuery("select*from student");
            while (rs.next()) {
                int s_id = rs.getInt("s_id");
                String s_sex = rs.getString("s_sex");
                String s_name = rs.getString("s_name");
                Date birth = rs.getDate("birth");
                System.out.println(s_id + "|" + s_name + "|" + s_sex + "|" + birth);
            }
        } finally {
            JDBCutils.close(conn,st,rs);
            conn = null;
        }
    }
}
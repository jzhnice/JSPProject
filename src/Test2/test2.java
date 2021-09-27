package Test2;

import utils.JDBCutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @version 1.0
 * @author： 贾志豪
 * @date： 2021-09-26 15:57
 * Course 的查询
 */
public class test2 {
        private static Connection conn = null;
        private static Scanner sc = new Scanner(System.in);
        static PreparedStatement ps = null;
        public static void main(String[] args) throws SQLException {
            ResultSet rs ;
            try {
                conn = JDBCutils.getConn();
                System.out.println("请输入名字");
                String name = sc.nextLine();

                String sql = "select*from Course where c_name=?";
                ps = conn.prepareStatement(sql);
                ps.setObject(1, name);
                System.out.println(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int c_id = rs.getInt("c_id");
                    String t_name = rs.getString("c_name");
                    String t_id = rs.getString("t_id");
                    System.out.println(c_id + "|" + t_name + "|"+t_id );
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn = null;
            }
        }
    }
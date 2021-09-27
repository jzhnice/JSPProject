package Test2;

import utils.JDBCutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * course 的删除
 */
public class Test4 {
    private static Connection conn = null;
    private static Scanner sc = new Scanner(System.in);
    static PreparedStatement ps = null;

    public static void main(String[] args) throws SQLException {
        conn = JDBCutils.getConn();
        System.out.println("请输入要删除的c_id");
        String c_id = sc.nextLine();

        String sql = "delete from course where c_id=? ";
        ps = conn.prepareStatement(sql);
        ps.setObject(1, c_id);
        ps.execute();
        PreparedStatement insert_ps = JDBCutils.getPreparedStatement(conn, sql, c_id);
        boolean execute = insert_ps.execute();
        System.out.println(execute ? "删除失败" : "删除成功");
    }
}

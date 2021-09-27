package Test2;

import utils.JDBCutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * @version 1.0
 * @author： 贾志豪
 * @date： 2021-09-26 18:35
 */
public class Test3 {
    //  course 的修改
    private static Connection conn = null;
    private static Scanner sc = new Scanner(System.in);
    static PreparedStatement ps = null;

    public static void main(String[] args) throws Exception {
        conn = JDBCutils.getConn();
        System.out.println("请输入要修改的c_id");
        String c_id = sc.nextLine();

        System.out.println("请输入要修改的c_name");
        int t_id = sc.nextInt();

        System.out.println("请输入要修改t_id");
        String c_name = sc.next();

        String sql = "update course  set  c_name=? , t_id=? where c_id=?";
        ps = conn.prepareStatement(sql);
        ps.setObject(1, c_id);
        ps.setObject(2, t_id);
        ps.setObject(3, c_name);
        System.out.println(sql);
        ps.execute();
        PreparedStatement insert_ps = JDBCutils.getPreparedStatement(conn, sql, c_id, t_id, c_name);
        boolean execute = insert_ps.execute();
        System.out.println(execute ? "修改失败" : "修改成功");
    }
}

package Test;

import utils.JDBCutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @version 1.0
 * @author： 贾志豪
 * @date： 2021-09-26 18:27
 *
 * teacher 的修该
 */
public class xiugai {
    private static Connection conn = null;
    private static Scanner sc = new Scanner(System.in);
    static PreparedStatement ps = null;
    public static void main(String[] args) throws SQLException {
        conn = JDBCutils.getConn();
        System.out.println("请输入要修改的id");
        String t_id = sc.nextLine();

        System.out.println("请输入要修改的名字");
        String t_name = sc.next();

        String sql = "update teacher  set  t_id=? where t_name=?";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,t_id );
        ps.setObject(2,t_name );

        System.out.println(sql);
        ps.execute();

        PreparedStatement insert_ps = JDBCutils.getPreparedStatement(conn,sql ,  t_id,t_name);
        boolean execute = insert_ps.execute();
        System.out.println(execute ? "修改失败" : "修改成功");
    }
}
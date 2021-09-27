package Test;
import utils.JDBCutils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @version 1.0
 * @author： 贾志豪
 * @date： 2021-09-26 15:13
 *
 *                       teacher的新增
 */
public class add {
    private static Connection conn = null;
    private static Scanner sc = new Scanner(System.in);
    static PreparedStatement ps = null;
    public static void main(String[] args) throws SQLException {
            conn = JDBCutils.getConn();
            String sql = "insert into teacher(t_id,t_name)values(?,?)";
            System.out.println("请输入要新增的教师编号");
            int t_id = sc.nextInt();

            System.out.println("请输入教师名字");
            String t_name = sc.next();

            ps = conn.prepareStatement(sql);
            JDBCutils.getPreparedStatement(conn, sql,t_id,t_name);
            ps.setObject(1, t_id);
            ps.setObject(2, t_name);
            ps.execute();
        PreparedStatement insert_ps = JDBCutils.getPreparedStatement(conn, sql,  t_id,t_name);
        boolean execute = insert_ps.execute();
        System.out.println(execute ? "新增失败" : "新增成功");
    }
}
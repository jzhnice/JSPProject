package Test2;
import utils.JDBCutils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
/**
 * @version 1.0
 * @author： 贾志豪
 * @date： 2021-09-26 16:47
 *
 * Course 的新增
 */
public class add {
    private static Connection conn = null;
    private static Scanner sc = new Scanner(System.in);
    static PreparedStatement ps = null;
    public static void main(String[] args) throws SQLException {
            conn = JDBCutils.getConn();
            String sql = "insert into course(c_name,t_id)values(?,?)";
            System.out.println("请输入要新增的课程名字");
            int c_name = sc.nextInt();

            System.out.println("请输入教师编号");
            String t_id = sc.next();

            ps = conn.prepareStatement(sql);
            JDBCutils.getPreparedStatement(conn, sql, c_name, t_id);
            ps.setObject(1, c_name);
            ps.setObject(2, t_id);

            ps.execute();




        PreparedStatement insert_ps = JDBCutils.getPreparedStatement(conn, sql, c_name, t_id);
        boolean execute = insert_ps.execute();
        System.out.println(execute ? "新增失败" : "新增成功");






        }
    }



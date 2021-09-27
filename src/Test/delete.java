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
 *
 * teacher 的删除
 * @date： 2021-09-26 17:08
 */
public class delete {
    private static Connection conn = null;
    private static Scanner sc = new Scanner(System.in);
    static PreparedStatement ps = null;
    public static void main(String[] args) throws SQLException {

            conn = JDBCutils.getConn();

            System.out.println("请输入要删除的t_id");
            String t_id = sc.nextLine();

            String sex = null;
            String sql = "delete from teacher where t_id=? ";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, t_id);
            ps.execute();
        PreparedStatement insert_ps = JDBCutils.getPreparedStatement(conn,sql ,  t_id);
        boolean execute = insert_ps.execute();
        System.out.println(execute ? "删除失败" : "删除成功");
    }
}

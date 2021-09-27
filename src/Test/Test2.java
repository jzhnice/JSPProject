package Test;
import utils.JDBCutils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Test2 {


    /**
     * teacher的查询
     * */
    private static Connection conn = null;
    private static Scanner sc = new Scanner(System.in);
    static PreparedStatement ps = null;
    public static void main(String[] args) throws SQLException {
        ResultSet rs ;
        try {
            conn = JDBCutils.getConn();
            System.out.println("请输入名字");
            String name = sc.nextLine();

            String sex = null;
            String sql = "select*from teacher where t_name=?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, name);

            System.out.println(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int t_id = rs.getInt("t_id");
                String t_name = rs.getString("t_name");
                System.out.println(t_id + "|" + t_name + "|" );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }
}

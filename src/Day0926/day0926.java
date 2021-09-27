package Day0926;
import utils.JDBCutils;
import java.sql.*;
import java.util.Scanner;
public class day0926 {
    private static Connection conn = null;
    private static Scanner sc = new Scanner(System.in);
    static PreparedStatement ps = null;
    public static void main(String[] args) throws SQLException {
        ResultSet rs = null;
        String t_id = null;
        try {
            conn = JDBCutils.getConn();
//            System.out.println("请输入名字");
//            String name = sc.nextLine();
//            String sex = "男";
//            String sql = "select*from student where s_name=?and s_sex=?";
//            // PreparedStatement : 是sql编译对象 作用和statement相同 可以执行SQL
////            是另一个数据库操作对象
////            优点： 可以通过预编译防止SQL注入 提高SQL语句的安全性
////            客户以避免拼SQL字符串的情况 把需要往SQL上品的参数，
////            使用？最为占位符 然后通过方法往SQL上进行设置
////            因为使用了预编译 所以jdbc执行的效率有所提高
//
////        使用第一步：通过数据库连接对象的 preparStatement（sql）方法对sql进行预编译
////                    并获取到preparStatement对象
////            preparStatement 是编译好sql后的sql执行对象
//
////            SQL语句已预编译并存储在PreparedStatement对象中。 然后可以使用该对象多次有效地执行此语句。
//            ps = conn.prepareStatement(sql);
////        使用第二部：对sql设置参数使用参数替换占位符
////                注意prepareStatementx是占位符所在的位置  从1开始 第一个占位符就用 1 第二个就用2
////            Object是该占位符位置上所需要填充的参数
//
//          /**
//           * setObject(int parameterIndex, Object x, int targetSqlType)
//           使用给定对象设置指定参数的值
//           */
//            ps.setObject(1, name);
//            ps.setObject(2, sex);
//            System.out.println(sql);
////        使用第三部 执行sql语句
////             查询executeQuery
////            增删改 execute
////
//
//
////        PreparedStatement 是sql预编译
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                int s_id = rs.getInt("s_id");
//                String s_sex = rs.getString("s_sex");
//                String s_name = rs.getString("s_name");
//                Date birth = rs.getDate("birth");
//                System.out.println(s_id + "|" + s_name + "|" + s_sex + "|" + birth);
            String insertSql = "insert into course(c_name,t_id)values(?,?)";
            System.out.println("请输入要新增的课程名");
            String courseName = sc.next();
            while (true) {
                System.out.println("请输入课程的授课教师名");
                String t_name = sc.next();
                PreparedStatement t_ps = JDBCutils.getPreparedStatement(conn, "select t_id from teacher where t_name=?", t_name);
                t_ps.setObject(1, t_name);

                ResultSet t_rs = t_ps.executeQuery();
                while (t_rs.next()) {
                    t_id = t_rs.getString("t_id");
                }
                if (t_id == null) {
                    System.out.println("查询的教师名不存在，请重新输入");
                } else {
                    break;
                }
            }
//            PreparedStatement insert_ps = conn.prepareStatement(insertSql);
//           insert_ps.setObject(1,courseName);
//           insert_ps.setObject(2,t_id);
            PreparedStatement insert_ps = JDBCutils.getPreparedStatement(conn, insertSql, courseName, t_id);
            boolean execute = insert_ps.execute();
            System.out.println(execute ? "新增失败" : "新增成功");
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }
}
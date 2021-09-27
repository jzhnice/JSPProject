import java.sql.*;
import java.util.Scanner;

/**
 * @version 1.0
 * @author： 贾志豪
 * @date： 2021-09-23 16:41
 */
public class Sc {
        public static void main(String[] args) throws SQLException {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://127.0.0.1:3306/homework";
                Connection connection = DriverManager.getConnection(url, "root", "root");
                System.out.println("获取到了数据库连接\t"+connection);
                Statement st = connection.createStatement();
                System.out.println("获取到了数据库操作对象\t"+st);
//            增删改查——增
                Scanner scanner = new Scanner(System.in);
                System.out.println("请输入学号");
                int c_id = scanner.nextInt();
                System.out.println("请输入课程编号");
                String c_name = scanner.next();
                System.out.println("请输入成绩");
                int t_id = scanner.nextInt();
                String insertsql = "insert into Sc(s_id,c_id,score)values(";
                insertsql += "'" + c_id +"',";
                insertsql += "'" + c_name +"',";
                insertsql += "'" + t_id + "')";
                boolean execute = st.execute(insertsql);
                System.out.println(execute ? "新增失败":"新增成功");
////            增删改查——删
                System.out.println("请输入你要删除的课程编号");
                String next = scanner.next();
                String delesql = "delete from Sc where s_id ='"+next+"'";
                boolean execute1 = st.execute(delesql);
                System.out.println(execute1 ? "删除失败":"删除成功");
//            增删改查——查
                String sql = "select*from Sc";
                ResultSet resultSet = st.executeQuery(sql);
                while(resultSet.next()){
                    String sid = resultSet.getString("s_id");
                    String cid = resultSet.getString("c_id");
                    String score = resultSet.getString("score");
                    System.out.println(sid+"|"+cid+"|"+score);
                }
//            增删改查——改
                System.out.println("请输入要修改的学号");
                String s_id = scanner.next();
                System.out.println("请输入课程编号");
                String usex = scanner.next();
                System.out.println("请输入教师成绩");
                String ubir = scanner.next();
                String updateSql = "update Sc set s_id='"+s_id+"',c_id ='"+usex+ "',score='"+ubir+
                        "'where s_id='" + s_id + "'";
                boolean execute2 = st.execute(updateSql);
                System.out.println(execute2 ? "修改失败" : "修改成功");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }



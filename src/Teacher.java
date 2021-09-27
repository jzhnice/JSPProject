import java.sql.*;
import java.util.Scanner;

/**
 * @version 1.0
 * @author： 贾志豪
 * @date： 2021-09-23 16:41
 */
public class Teacher {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/homework";
            Connection connection = DriverManager.getConnection(url, "root", "root");
            System.out.println("获取到了数据库连接\t" + connection);
            Statement st = connection.createStatement();
            System.out.println("获取到了数据库操作对象\t" + st);
//            增删改查——增
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入教师编号");
            int c_id = scanner.nextInt();
            System.out.println("请输入教师名称");
            String c_name = scanner.next();
            String insertsql = "insert into teacher(t_id,t_name)values(";
            insertsql += "'" + c_id + "',";
            insertsql += "'" + c_name + "')";
            boolean execute = st.execute(insertsql);
            System.out.println(execute ? "新增失败" : "新增成功");
////            增删改查——删
            System.out.println("请输入你要删除的课程编号");
            int next = scanner.nextInt();
            String delesql = "delete from teacher where t_id ='" + next + "'";
            boolean execute1 = st.execute(delesql);
            System.out.println(execute1 ? "删除失败" : "删除成功");
//            增删改查——查
            String sql = "select*from teacher";
            ResultSet resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                String sid = resultSet.getString("t_id");
                String cid = resultSet.getString("t_name");
                System.out.println(sid + "|" + cid + "|");
            }
//            增删改查——改
            System.out.println("请输入要修改的教师编号");
            int s_id = scanner.nextInt();
            System.out.println("请输入要修改的教师名称");
            String usex = scanner.next();
            String updateSql = "update teacher set t_id='" + s_id + "',t_name ='" + usex
                    + "'where t_id='" + s_id + "'";
            boolean execute2 = st.execute(updateSql);
            System.out.println(execute2 ? "修改失败" : "修改成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

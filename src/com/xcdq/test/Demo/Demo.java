package com.xcdq.test.Demo;
import java.sql.*;
import java.util.Scanner;
/**
 * @version 1.0
 * @author： 贾志豪
 * @date： 2021-09-22 10:33
 */
public class Demo {
    public static void main(String[] args) {
        // 1:在项目中 导入数据库驱动包 先把驱动包整到项目里 先上车
        // 2：添加倒库  把数据库驱动包添加到项目的库中 让项目认识这个驱动包  后补票
        /**
         * 如果 只做了第一步 没有第二部 项目就不认识这个驱动包
         * */
        // 3：加载 数据库驱动包的驱动 加载完成后就可以对数据库进行操作
        try {
            // 通过反射，反射驱动类的类名，来注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //4.运行驱动。获取与数据库的连接
            // 通过驱动管理类 DriverManager   获取数据库连接
            //getConnection（） 方法就是获取数据库连接的方法
            // 指定使用的数据库， 数据库用户名 ， 数据库密码
            // 指定数据库
            //            jdbc协议:子协议://数据库IP地址:数据库端口/数据库名
            //   JDBC -->JDBC协议
            // mysql -- >子协议  ===》使用的是什么数据库 ===》例子 ： jdbc:orancle
            // IP地址  -- >  数据库所在的IP地址
            // 端口 ---》 数据库端口 mysql默认的是3306
            // 数据库名 ---》 自己创建的数据库
            String url = "jdbc:mysql://127.0.0.1:3306/homework";
            //？useUnicode=true&characterEncoding=utf8
            // 通过驱动管理器，获取到数据库的连接，
            // 获取数据库连接的前提是 知道数据库的地址，知道数据库的用户名和密码
            Connection conn = DriverManager.getConnection(url, "root", "root");
            System.out.println("获取到了数据库连接\t" + conn);
            // 5：获取数据库操作对象 ，这个对象能够直接对数据库进行操作
            // 这个操作对象 就是用来执行sql语句的
            Statement st = conn.createStatement();
            System.out.println("获取到了数据库操作对象\t" + st + "  可以执行sql语句了");
            // 6：操作数据库
            //6.1 写SQL
            /**新增*/
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入要新增的编号");
            int id = sc.nextInt();
            System.out.println("请输入要新增的学生姓名");
            String name = sc.next();
            System.out.println("请输入要新增的学生性别");
            String sex = sc.next();
            System.out.println("请输入学生生日(按照yyyy-mmmm-dd格式进行输入)");
            String next = sc.next();
//           拼接sql语句
            String insertsql = "insert into student(s_id,s_name,s_sex,birth)values(";
            insertsql += "'" + id + "',";
            insertsql += "'" + name + "',";
            insertsql += "'" + sex + "',";
            insertsql += "'" + next + "')";
            boolean execute = st.execute(insertsql);
            System.out.println(execute ? "新增失败" : "新增成功");
            /**查询*/
            String sql = "select*from student";
//              ResultSet --> result = 结果  set  = set集合
//             连起来就是结果集
//            使用方式类似于set集合中的迭代器  set集合只能使用迭代器遍历
//                    里面存放的内容，就是在数据库里查询道德内容
            ResultSet rs = st.executeQuery(sql);
            //resultSet 中的next（）方法没相当于iterator迭代器中的hasNext()都是用来判断集合中是否存在吓一跳数据的存在
            while (rs.next()) {
                //  varchar 使用String进行接受
                int s_id = rs.getInt("s_id");
                String s_sex = rs.getString("s_sex");
                String s_name = rs.getString("s_name");
                Date birth = rs.getDate("birth");
                System.out.println(s_id + "|" + s_name + "|" + s_sex + "|" + birth);
            }
            /**删除 */
            System.out.println("请输入要删除的学生姓名");
            String deleName = sc.next();
            String delesql = "delete from student where s_name='" + deleName + "'";
            boolean execute1 = st.execute(delesql);
            System.out.println(execute1 ? "删除失败" : "删除成功");
            /**修改*/
            System.out.println("请输入要修改的学生名");
            String stuName = sc.next();
            System.out.println("请输入学生性别");
            String usex = sc.next();
            System.out.println("请输入学生生日 （按照 yyyy-mm-dd格式进行输入）");
            String ubir = sc.next();
            Date date1 = Date.valueOf(ubir);
            String updateSql = "update student set  s_sex = '" + usex + "' , birth = '" + date1 +
                    "'  where  s_name = '" + stuName + "'";
            boolean execute2 = st.execute(updateSql);
            System.out.println(execute2 ? "修改失败" : "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         *
         * 总结1：
         * 执行查询语句时 ， executeQuery（） ， 其余的sql操作（新增 ， 修改 ， 删除） 都使用 execute() ;
         * */
    }
}
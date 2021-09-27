package day0927.day0927;

import entily.Student;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author： 贾志豪
 * @date： 2021-09-27 16:16
 */
public class day0927 {
    public static void main(String[] args) {
// 泛型
// 泛型是什么?
        List<Object> list = new ArrayList<>();
//      1：  泛型是给出了一个范围。和这个范围有直接继承关系的就属于这个范围内的数据
//       2： 泛型不能定义为基本数据类型，只能使用引用数据类型作为泛型
//        最基本的反射操作
//        注册JDBC驱动时 使用过 通过类的全类名来加载一个类

        try {
            Class<?> clazz = Class.forName("entily.Student");
            // 通过加载好的类 获取到这个类的实例对象 不适用 new 关键字来创建对象
            Student s = (Student) clazz.newInstance();

            s.setS_id(1);
            s.setS_name("黄四郎");
            s.setS_sex("男");
            s.setS_old_name("小四二");
            System.out.println(s.toString());

            // 通过反射 来获取这个类中发的方法和属性
            try {
                method(s);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    public static void method(Object o) throws Exception {
// 在这个类中  o 是一个未知类型的对象  只知道是一个实例对象。
// 怎么才能获取到 o的属性和方法呢
        // 通过getcalss方法 获取到这个类的自己吗文件
        Class<?> clazz = o.getClass();
//        getName获取的是全类名 包含报名
        String className = clazz.getName();
        System.out.println("这个类的类名是" + className);
        // getSimpleName 获取到的是不包含报名的类名
        String simpleName = clazz.getSimpleName();
        System.out.println("正式的类名=" + simpleName);
        // 获取这个类中的属性列表
        System.out.println("下面的是 getfields方法获取的");
//        Field[] fields = clazz.getField();
//        for (int i = 0; i < fields.length; i++) {
//            System.out.println(fields[i].getName());
//        }

        //   getDeclaredFields 获取到了属性列表

//
        Field[] declaredFields = clazz.getDeclaredFields();

        for (int i = 0; i < declaredFields.length; i++) {
            String fieldName = declaredFields[i].getName();
            System.out.println(fieldName);


            if (fieldName.contains("name")) {


                // 开放属性访问权限 declaredFields[i].setAccessible(true); 是获取访问权限的 开启后可以访问被private修饰的实行
//                 传入true 就是开启  传入falseuse 就是关闭
                declaredFields[i].setAccessible(true);
                Object o1 = declaredFields[i].get(o);
                System.out.println(o1);
                declaredFields[i].set(o, "马邦德");

                // 这里是修改后的名字
                System.out.println("这里对属性值进行了修改\n");
                Object v = declaredFields[i].get(o);
                System.out.println(v);

                /**  直接进行get权限不足  抛出权限不足的错误
                 * 需要开放权限
                 * Object o1 = declaredFields[i].get(o);
                 *                 System.out.println(o1);
                 *
                 * */
            }
        }


//        如何获取这个属性的属性值/
//                要获取name的值 但是有两个 一个s_name  s_old_name



        /* getFields（）获取不到被private修饰的属性 */
//        但是 getDeclaredFields 可以获取到 被private修饰的属性

        System.out.println("\n");

        Method[] method = clazz.getDeclaredMethods();
        for (int i = 0; i < method.length; i++) {

            String name = method[i].getName();
            System.out.println(name);
            if (name.contains("toString")) {
                System.out.println("在这里调用实例对象的toString方法");
//                invoke方法就是method绕过实例对象调用方法的途径
                Object result = method[i].invoke(o);
                System.out.println("hello world  " + result);
            }


        }
    }
}

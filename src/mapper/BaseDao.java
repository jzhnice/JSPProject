package mapper;

import java.util.List;

/**
 * @version 1.0
 * @author： 贾志豪
 * @date： 2021-09-27 17:15
 *
 * 这个接口是泛型接口
 */
public interface BaseDao <E> {


    /**
     * E ：代表的是实体类对象，就是所有的实例对象（被new出来的），需要有自己的构造方法，必须是引用数据类型
     * C：代表class  代表的所有的类 被加载之后的字节码文件
     * T: 代表Type  是Java中最大的类型
     * K：代表key    是键值对中的键 ，通常以键值对形式出现 和V一起使用
     * V：代表value  是键值对中的值 ，通常以键值对形式出现 和K一起使用
     * */


    /**
    * 这个方法是泛型方法
    * */
    List<E> select(E e);

}

package entily;

/**
 * @version 1.0
 * @author： 贾志豪
 * @date： 2021-09-27 17:25
 */

/*
* 学生实体类
* ----------这是类   也就是class
* */
public class Student {
/**
 * 这部分叫做属性
 *
 * ---------属性部分开始  也就是这个类的Field
 * */
    private  Integer s_id;
    private  String s_name;
    private  String s_sex;
    private String s_old_name;

    public String getS_old_name() {
        return s_old_name;
    }


    public void setS_old_name(String s_old_name) {
        this.s_old_name = s_old_name;
    }
    /*
     * -----------属性部分结束
     * */


    /**
    --------------- 方法部分开始   也就是这个类的 Method
     */
    public Integer getS_id() {
        return s_id;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }


    public String getS_sex() {
        return s_sex;
    }

    public void setS_sex(String s_sex) {
        this.s_sex = s_sex;
    }


    @Override
    public String toString() {
        return "Student{" +
                "s_id=" + s_id +
                ", s_name='" + s_name + '\'' +
                ", s_sex='" + s_sex + '\'' +
                '}';
    }
    /*
    方法部分结束
    Method
    */


    /**
    ----------- 构造部分开始  也就是这个类的Constructor
    */

    public  Student(){

    }
    /*
     --------------------构造部分结束
    */

}

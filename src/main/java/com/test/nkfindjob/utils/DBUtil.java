package com.test.nkfindjob.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBUtil {
    //    声明私有静态属性，实例化Properties类对象，因为要给public static ...使用
    private static Properties prop = new Properties();
    //声明静态代码块（因为其他属性是静态的）,借助私有静态属性prop来完成dbconfig.properties 文件的读入
    //借助getClassLoader()保证再web 项目中可以定位并且读入配置文件
    static {
        try {

            prop.load(DBUtil.class.getClassLoader().getResourceAsStream("dbconfig.properties"));
//            prop.load(new FileInputStream("F:\\WorkSpace\\IDEA-workspace\\projectback\\src\\main\\resources\\dbconfig.properties"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //声明共有属性，保存获取的数据库配置信息
    public static String driver=prop.getProperty("jdbc.driver");
    public static String url= prop.getProperty("jdbc.url");
    public static String user=prop.getProperty("jdbc.user");
    public static String password=prop.getProperty("jdbc.password");
}

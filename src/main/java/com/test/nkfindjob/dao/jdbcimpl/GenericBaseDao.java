package com.test.nkfindjob.dao.jdbcimpl;

import com.test.nkfindjob.utils.DBUtil;

import java.sql.*;

public class GenericBaseDao {
    //0.声明私有静态属性，获取保存数据库链接的配置参数
    private static String driver = DBUtil.driver;
    private static String url = DBUtil.url;
    private static String user = DBUtil.user;
    private static String password = DBUtil.password;
    //1.借助static code 完成一次性的注册驱动程序
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //2.共性：获取链接，普适的增删改方法，普适的查询方法，关闭链接
    //声明JDBC基础接口对象,因为是父类，可能被子类使用所以声明protected
    protected Connection conn;
    protected PreparedStatement pstmt;
    protected ResultSet rs;
    protected int result = -1 ;

    //2-1 获取链接的方法,获取|赋值conn属性
    public void getConnection() throws SQLException {
        url=DBUtil.url;
        user=DBUtil.user;
        password=DBUtil.password;

        conn = DriverManager.getConnection(url,user,password);
    }

    //2-2 关闭链接
    public void closeAll() throws SQLException {
        //先判断，非空并且没有关闭的时候，再关闭
        if(rs!=null && !rs.isClosed())
            rs.close();
        if(pstmt!=null && !pstmt.isClosed()){
            pstmt.close();
        }
        if(conn!=null && !conn.isClosed()){
            conn.close();
        }
        if(result!=-1)
            result=-1;
    }

    //2-3 普适的增删改的方法
    //生成sql语句的容器对象，放入sql语句，执行获取返回结果
    public void executeUpdate(final String sql,final Object...params) throws SQLException {
        //0.生成sql容器对象
        pstmt = conn.prepareStatement(sql);
        //1.判断是否有参数，如果有，逐个遍历参数数组，将参数逐个传入sql语句对象中
        if(params != null){ //有参数传入，开始遍历进行参数设置
            for(int i=0;i<params.length;i++){
                pstmt.setObject(i+1,params[i]);
            }
        }
        //2.执行sql 语句（增删改),将返回结果放入 result 中
        result = pstmt.executeUpdate();

    }

    //2-4 普适的查询方法
    public void executeQuery(final String sql,final Object...params) throws SQLException {
        //0.生成sql容器对象
        pstmt = conn.prepareStatement(sql);
        //1.判断是否有参数，如果有，逐个遍历参数数组，将参数逐个传入sql语句对象中
        if(params != null){ //有参数传入，开始遍历进行参数设置
            for(int i=0;i<params.length;i++){
                pstmt.setObject(i+1,params[i]);
            }
        }
        //2.执行sql 语句（增删改),将返回结果集放入 rs 中
        rs=pstmt.executeQuery();

    }
}

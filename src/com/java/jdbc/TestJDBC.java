package com.java.jdbc;

import com.java.entity.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestJDBC {
    public static void main(String[] args) {

        //实例化Student对象
        Student stu = new Student();
        stu.setStuId(1003);
        stu.setStuName("lily");
        stu.setStuPassword("123456");
       //调用添加数据的方法
        boolean flag = register(stu);
        if(flag){
            System.out.println("成功");
        }else{
            System.out.println("失败");
        }

    }


    public static boolean register(Student student) {

        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //连接数据库
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?characterEncoding=utf-8&useSSL=false", "root", "root");
            //加载sql语句
            PreparedStatement ps = connection.prepareStatement("INSERT INTO student(stuId,stuName,stuPassword) VALUES (?,?,?)");
            //为点位符赋值
            ps.setInt(1, student.getStuId());
            ps.setString(2, student.getStuName());
            ps.setString(3, student.getStuPassword());
            //执行，返回影响sql的行数
            int i = ps.executeUpdate();

            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载失败");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delStudent(int stuId){

        return false;
    }
}

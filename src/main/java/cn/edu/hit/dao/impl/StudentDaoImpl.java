package cn.edu.hit.dao.impl;

import cn.edu.hit.dao.StudentDao;
import cn.edu.hit.entity.Student;
import cn.edu.hit.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public void add(Student student) {
        String sid = student.getSid();
        String name = student.getName();
        int age = student.getAge();
        String birthday = student.getBirthday();
        String gender = student.getGender();
        String sql = "insert into student values(?,?,?,?,?)";
        Connection con = DbUtil.getConnection();
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            //rs = ps.executeQuery();
            ps.setString(1, sid);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setDate(4, Date.valueOf(birthday));
            ps.setString(5, gender);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(ps, con);
        }
    }

    @Override
    public void modify(Student student) {
        String sid = student.getSid();
        String name = student.getName();
        int age = student.getAge();
        String birthday = student.getBirthday();
        String gender = student.getGender();
        String sql = "update student set name=?, age=?, birthday=?, gender=? where sid=?";
        Connection con = DbUtil.getConnection();
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            //rs = ps.executeQuery();
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setDate(3, Date.valueOf(birthday));
            ps.setString(4, gender);
            ps.setString(5, sid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(ps, con);
        }
    }

    @Override
    public void remove(String sid) {
        String sql = "delete from student where sid=?";
        Connection con = DbUtil.getConnection();
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            //rs = ps.executeQuery();
            ps.setString(1, sid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(ps, con);
        }
    }

    @Override
    public List<Student> getAll(String sql) {
        String sid;
        String name;
        int age;
        String birthday;
        String gender;

        Connection con = DbUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                sid = rs.getString("sid");
                name = rs.getString("name");
                age = rs.getInt("age");
                birthday = rs.getString("birthday");
                gender = rs.getString("gender");
                list.add(new Student(sid, name, age, birthday, gender));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DbUtil.close(rs, ps, con);
        }
    }

    @Override
    public Student getStudent(String sid) {
        String name;
        int age;
        String birthday;
        String gender;
        String sql = "select * from student where sid=?";
        Connection con = DbUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, sid);
            rs = ps.executeQuery();
            Student student = null;
            if (rs.next()) {
                sid = rs.getString("sid");
                name = rs.getString("name");
                age = rs.getInt("age");
                birthday = rs.getString("birthday");
                gender = rs.getString("gender");
                student = new Student(sid, name, age, birthday, gender);
            }
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DbUtil.close(rs, ps, con);
        }
    }

    @Override
    public List<Student> getByName(String name) {
        String sid;
        int age;
        String birthday;
        String gender;
        String sql = "select * from student where name like '%" + name + "%'";
        Connection con = DbUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                sid = rs.getString("sid");
                name = rs.getString("name");
                age = rs.getInt("age");
                birthday = rs.getString("birthday");
                gender = rs.getString("gender");
                list.add(new Student(sid, name, age, birthday, gender));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DbUtil.close(rs, ps, con);
        }
    }
}
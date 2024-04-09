package cn.edu.hit.util;

import java.sql.*;

public class DbUtil {
    private static Connection con;
    private static final String URL = "jdbc:postgresql://localhost:5432/webLab2";

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "ubuntu2004";

    public static Connection createConnection() {
        try {
            // 使用反射将数据库驱动加载到jvm中
            Class.forName("org.postgresql.Driver");
            System.out.println("数据库驱动加载成功");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("连接失败！！！");
            throw new RuntimeException(e);

        }
        return con;
    }

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                con = createConnection();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
    public static void close(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void close(PreparedStatement ps, Connection con) {
        try {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//ResultSet rs?
    public static void close(ResultSet rs, PreparedStatement ps, Connection con) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

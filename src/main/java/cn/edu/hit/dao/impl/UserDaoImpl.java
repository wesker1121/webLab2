package cn.edu.hit.dao.impl;

import cn.edu.hit.dao.UserDao;
import cn.edu.hit.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean login(String username, String password) {
        Connection con = DbUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;
        try {
            ps = con.prepareStatement("select count(*) from \"user\" where username = ? and password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DbUtil.close(rs, ps, con);
        }
    }
}

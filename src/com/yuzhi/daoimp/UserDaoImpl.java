package com.yuzhi.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yuzhi.bean.User;
import com.yuzhi.dao.UserDAO;
import com.yuzhi.util.JDBCPoolUtil;

public class UserDaoImpl implements UserDAO {
	private Connection connection;
	private PreparedStatement preparestatement;
	private ResultSet reultset;

	@Override
	public boolean findUserByPwdAndName(String username, String pwd) {
		try {
			connection = JDBCPoolUtil.getConnection();
			preparestatement = connection.prepareStatement("select * from user1 where uname=? and upwd=?");
			preparestatement.setString(1, username);
			preparestatement.setString(2, pwd);
			reultset = preparestatement.executeQuery();
			if (reultset.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.yuzhi.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCPoolUtil {
	private static DataSource dataSource = null;

	static {
		dataSource = new ComboPooledDataSource("mvcapp");
	}

	// 获取连接对象
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	// 释放连接
	public static void closeResource(Connection conn, Statement stat, ResultSet rs) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(JDBCPoolUtil.getConnection());
	}
}

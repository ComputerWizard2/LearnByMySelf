package com.yuzhi.dao;

import com.yuzhi.bean.User;

public interface UserDAO {
	// 查询用户对象是否存在于数据库中
	public boolean findUserByPwdAndName(String username, String pwd);

	// 更具id返回对象
	public User findUserById(int id);

}

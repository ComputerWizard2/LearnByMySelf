package com.yuzhi.dao;

import com.yuzhi.bean.User;

public interface UserDAO {
	// ��ѯ�û������Ƿ���������ݿ���
	public boolean findUserByPwdAndName(String username, String pwd);

	// ����id���ض���
	public User findUserById(int id);

}

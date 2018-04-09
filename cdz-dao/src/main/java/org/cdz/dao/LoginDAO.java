package org.cdz.dao;

import java.util.List;
import java.util.Map;

import com.cdz.User;

public interface LoginDAO {
	List<User> list(Map<String,Object> map);
	List<User> findUserInfo();
	List<User> findUserInfo2();

	List<User> getUserInfoByName(String username);

	int remove(int id);
}


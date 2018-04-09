package org.cdz.dao;

import java.util.List;
import java.util.Map;

import com.cdz.User;

public interface UserDAO {
	List<User> findUserInfo();

	List<User> findUserInfo2();

	List<User> getUserInfoByName(String username);

	List<User> list(Map<String,Object> map);
	int remove(int id);

	User get(int id);

	int update(User user);

	int batchRemove(int[] ids);

	int add(User user);
}

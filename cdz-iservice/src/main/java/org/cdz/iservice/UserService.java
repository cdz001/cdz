package org.cdz.iservice;

import java.util.List;
import java.util.Map;

import com.cdz.User;
import com.github.pagehelper.PageInfo;

public interface UserService {

	public List<User> getUserInfo();

	public PageInfo<User> findItemByPage(Integer currentPage,Integer pageSize) ;

	public List<User> getUserInfoByName(String username);

	public int remove(int parseInt);
	
	List<User> list(Map<String,Object> map);

	public User get(int userId);

	public int update(User user);

	public int batchRemove(int[] ids);

	public int add(User user);

}

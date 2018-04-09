package org.cdz.service.impl;

import java.util.List;
import java.util.Map;

import org.cdz.dao.LoginDAO;
import org.cdz.dao.UserDAO;
import org.cdz.iservice.LoginService;
import org.cdz.iservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.cdz.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	

	@Override
	public List<User> list(Map<String,Object> map2) {
		return userDAO.list(map2);
	}

	@Override
	public List<User> getUserInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<User> findItemByPage(Integer pageNo, Integer pageSize) {
		Integer pageNum = pageNo == null ? 1 : pageNo;
		Integer limit = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNum, limit);
		List<User> list = userDAO.findUserInfo2();
		PageInfo<User> page = new PageInfo<User>(list);
		return page;
	}

	@Override
	public List<User> getUserInfoByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int remove(int id) {
		return userDAO.remove(id);
	}

	@Override
	public User get(int id) {
		return userDAO.get(id);
	}

	@Override
	public int update(User user) {
		return userDAO.update(user);
	}

	@Override
	public int batchRemove(int[] ids) {
		return userDAO.batchRemove(ids);
	}

	@Override
	public int add(User user) {
		return userDAO.add(user);
	}


}

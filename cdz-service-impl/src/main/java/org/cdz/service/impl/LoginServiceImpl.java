package org.cdz.service.impl;

import java.util.List;
import java.util.Map;

import org.cdz.dao.LoginDAO;
import org.cdz.iservice.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.cdz.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDAO loginDAO;

	public List<User> getUserInfo() {
		return loginDAO.findUserInfo();
	}

	public PageInfo<User> findItemByPage(Integer pageNo, Integer pageSize) {
//		PageHelper.startPage(pageNo,pageSize);
//		List<User> userList = userDAO.findUserInfo2();
//		return userList;
		
		Integer pageNum = pageNo == null ? 1 : pageNo;
		Integer limit = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNum, limit);
		List<User> list = loginDAO.findUserInfo2();
		PageInfo<User> page = new PageInfo<User>(list);
		return page;
	}

	public List<User> getUserInfoByName(String username) {
		return loginDAO.getUserInfoByName(username);
	}


	@Override
	public List<User> list(Map<String, Object> map) {
		return loginDAO.list(map);
	}

}

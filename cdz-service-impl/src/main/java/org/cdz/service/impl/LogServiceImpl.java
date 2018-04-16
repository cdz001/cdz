package org.cdz.service.impl;

import org.cdz.dao.LogDAO;
import org.cdz.iservice.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.cdz.Log;
@Service
@Transactional
public class LogServiceImpl implements LogService {
	@Autowired
	private LogDAO logDAO;
	public int save(Log log) {
		return logDAO.save(log);
	}

}

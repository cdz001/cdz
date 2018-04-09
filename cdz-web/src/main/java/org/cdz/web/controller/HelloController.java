package org.cdz.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.cdz.iservice.UserService;
import org.cdz.utils.DataTablePageUtil;
import org.cdz.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cdz.User;
import com.github.pagehelper.PageInfo;

@Controller
public class HelloController {
	private Logger logger = Logger.getLogger(UserController.class);

	@Reference
	private UserService userService;

	/*
	 * http://localhost:8080/getUserInfo
	 */

	@RequestMapping(value = "/getUserInfo")
	@ResponseBody
	public List<User> getUserInfo() {
		List<User> user = userService.getUserInfo();
		for (User u : user) {
			System.out.println("user.getName():" + u.getUsername());
			logger.info("user.getAge():" + u.getPassword());
		}

		return user;
	}
	
	@RequestMapping(value = "/getUserInfo",method=RequestMethod.GET)
	@ResponseBody
	public List<User> getUserInfoByName(@RequestParam String username) {
		List<User> user = userService.getUserInfoByName(username);
		for (User u : user) {
			System.out.println("user.getName():" + u.getUsername());
			logger.info("user.getAge():" + u.getPassword());
		}

		return user;
	}
	
	@RequestMapping(value = "/page",method=RequestMethod.GET)
	@ResponseBody
	public DataTablePageUtil<User> getUserByPage(HttpServletRequest request) {
		DataTablePageUtil<User> dataTable = new DataTablePageUtil<User>(request); 
		PageInfo<User> pageInfo = userService.findItemByPage(dataTable.getPage_num(),dataTable.getLength()) ;
//		logger.info(user.size()+"****************");
//		for (User u : user) {
//			System.out.println("user.getName():" + u.getUsername());
//			logger.info("user.getAge():" + u.getPassword());
//		}
		//封装数据给DataTables  
		dataTable.setDraw(dataTable.getDraw());  
		dataTable.setData(pageInfo.getList());    
		dataTable.setRecordsTotal((int)pageInfo.getTotal());    
		dataTable.setRecordsFiltered(dataTable.getRecordsTotal());  
		return dataTable;
	}
	
	@RequestMapping(value = "/page2",method=RequestMethod.GET)
	@ResponseBody
	public Page<User> getUserByPage2(HttpServletRequest request) {
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));  
		Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber")); 
		PageInfo<User> pageInfo = userService.findItemByPage(pageNumber,pageSize) ;
//		logger.info(user.size()+"****************");
//		for (User u : user) {
//			System.out.println("user.getName():" + u.getUsername());
//			logger.info("user.getAge():" + u.getPassword());
//		}
		
		Page<User> p = new Page<User>();
		p.setRows(pageInfo.getList());
		p.setTotal(pageInfo.getTotal());
		//封装数据给DataTables  
		return p;
	}
	
	@RequestMapping(value = "/hello")
    public String helloGet(Map<String, Object> map) {
		map.put("name", "jetty");
        return "bt";
    }

}

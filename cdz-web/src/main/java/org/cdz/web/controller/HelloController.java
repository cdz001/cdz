package org.cdz.web.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.cdz.iservice.UserService;
import org.cdz.utils.DataTablePageUtil;
import org.cdz.utils.Page;
import org.cdz.web.log.Logg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	@Logg("执行了page方法")
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
	
	@Logg("执行了page2方法")
//	@RequestMapping(value = "/page2/{pageSize}/{pageNumber}",method=RequestMethod.GET)
	@RequestMapping(value = "/page2",method=RequestMethod.GET)
	@ResponseBody
	public Page<User> getUserByPage2(HttpServletRequest request) {
//		public Page<User> getUserByPage2(HttpServletRequest request,HttpServletResponse httpResponse,@PathVariable String pageSize,@PathVariable String pageNumber) {
//		 Enumeration enu=request.getParameterNames();
//	        while(enu.hasMoreElements()){
//	        String paraName=(String)enu.nextElement();
//	        System.out.println(paraName+": "+request.getParameter(paraName));
//	        } 
//		 Enumeration headerNames = request.getHeaderNames();
//		    while (headerNames.hasMoreElements()) {
//		        String key = (String) headerNames.nextElement();
//		        String value = request.getHeader(key);
//		        System.out.println(key+" "+value);
//		    }
//		    
//		    System.out.println("****************************");
//		    
//		    String originHeader = request.getHeader("Origin");
//		    System.out.println("originHeader="+originHeader);
//		    System.out.println(pageSize+" "+pageNumber);
		    
		Integer pageSize1 = Integer.parseInt(request.getParameter("pageSize"));  
		Integer pageNumber1 = Integer.parseInt(request.getParameter("pageNumber")); 
		PageInfo<User> pageInfo = userService.findItemByPage(pageNumber1,pageSize1) ;
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
	
	@Logg("执行了Hello方法")
	@RequestMapping(value = "/hello")
    public String helloGet(Map<String, Object> map) {
		map.put("name", "jetty");
        return "bt";
    }

}

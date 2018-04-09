package org.cdz.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cdz.iservice.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cdz.User;

@Controller
public class LoginController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference
	LoginService loginService;
	
	@GetMapping(value={"/login","","/"})
	String login() {
		return "login";
	}
	
	@PostMapping(value={"/login"})
	@ResponseBody
	Map<String,String> loginValidate(@RequestParam String username, @RequestParam String password) {
		Map<String,String> map = new HashMap<String,String>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("username", username);
		map2.put("password", password);
		List<User> list = loginService.list(map2);
		if(!list.isEmpty() && list.size()>0){
			map.put("code", "1");
		}
		map.put("code", "0");
		return map;
	}
}

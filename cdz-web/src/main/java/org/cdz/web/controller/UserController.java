package org.cdz.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.cdz.iservice.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cdz.User;

@Controller
public class UserController {
	private Logger logger = Logger.getLogger(UserController.class);

	@Reference
	private UserService userService;

	/*
	 * http://localhost:8080/getUserInfo
	 */

	@RequestMapping(value = "/get")
	@ResponseBody
	public List<User> getUserInfo() {
		return userService.getUserInfo();
	}

	@GetMapping(value = "/edit")
	public String preEdit(@RequestParam String userId,Model model) {
		User user = userService.get(Integer.parseInt(userId));
		model.addAttribute("user",user);
		return "edit2";
	}
	
	@PostMapping(value = "/remove")
	@ResponseBody
	public String remove(@RequestParam String userId) {
		int flag = userService.remove(Integer.parseInt(userId));
		String s = (flag > 0) ? "1" : "0";
		return s;
	}
	
	
	@PostMapping(value = "/batchRemove")
	@ResponseBody
	public String batchRemove(@RequestParam(value = "ids[]") int[] ids) {
		int flag = userService.batchRemove(ids);
		String s = (flag > 0) ? "1" : "0";
		return s;
	}
	
	@PostMapping(value = "/update")
	@ResponseBody
	public String update(@RequestBody User user) {
		int flag = userService.update(user);
		String s = (flag > 0) ? "1" : "0";
		return s;
	}
	
	@GetMapping(value = "/preAdd")
	public String preAdd() {
		return "add";
	}
	
	@PostMapping(value = "/add")
	@ResponseBody
	public String add(@RequestBody User user) {
		int flag = userService.add(user);
		String s = (flag > 0) ? "1" : "0";
		return s;
	}
}

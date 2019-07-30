package com.klfront.klcms.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.klfront.klcms.dao.DeptDao;
import com.klfront.klcms.entity.User;
import com.klfront.klcms.service.UserService;
import com.klfront.klcms.util.MD5Util;

/**
 * <p>
 * Title: UserController.java
 * </p>
 * <p>
 * Description: 用户管理 登录等
 * </p>
 * <p>
 * Copyright: Copyright (c) 2019
 * </p>
 * <p>
 * Company: www.klfront.com
 * </p>
 *
 * @author Lu Jinlong
 * @date 2019-07-12
 * @version 1.0
 */

@Controller
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping(value = "/user/login")
	public String login(HttpServletRequest request,
			@RequestParam("username") String username, 
			@RequestParam("password") String password,
			Map<String, Object> map, HttpSession session) {	
		String md5pwd = MD5Util.encode(password,"klcmskey");
		User user = service.getByUsername(username);
		if(null!=user&&user.getPassword().equals(md5pwd)) {
			// 登陆成功，防止表单重复提交，可以重定向到主页
			session.setAttribute("loginUser", username);
			String referer = request.getHeader("Referer");	
//			String requestUrl = request.getRequestURL().toString();
			if(referer==null||referer.isEmpty()||referer.endsWith("login")) {
				referer ="/index.html";
			}
			return "redirect:"+referer;
		} else {
			// 登陆失败
			map.put("msg", "用户名密码错误");
			return "login";
		}
	}
	
	@GetMapping(value = "/user/logout")
	public void logout(HttpServletResponse response, HttpSession session) {
		session.removeAttribute("loginUser");
		try {
			response.sendRedirect("/");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}

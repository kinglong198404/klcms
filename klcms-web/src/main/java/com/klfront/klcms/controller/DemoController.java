package com.klfront.klcms.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.klfront.klcms.entity.Dept;
import com.klfront.klcms.entity.User;
import com.klfront.klcms.service.DeptService;

/**
 * 演示控制器
 * 
 * <p>Title: DemoController</p>
 * <p>Description: 测试基本的接口功能：返回Json数据或视图</p>
 *
 * @author Lu Jinlong
 * @date 2019-07-05
 * @version 1.0
 * @since 1.8.0_92
 */
@Controller
public class DemoController {
	@Autowired
	private DeptService service;
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello(@RequestParam("user") String user) {
		return "Hello World";
	}
	
	/**
	 * 返回json数据，则添加@ResponseBody注解
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/json")
	public User json() {
		User user = new User();
		user.setUsername("king");
		user.setEmail("kinglong1984@126.com");
		return user;
	}
	
	@ResponseBody
	@RequestMapping(value = "/dept/list", method = { RequestMethod.GET })
	public List<Dept> listDept() {
		List<Dept> depts = service.list();
    	return depts;
	}
	@ResponseBody
	@RequestMapping(value = "/dept/get/{id}", method = { RequestMethod.GET })
	public Dept getDept(@PathVariable("id") Long id) {
    	Dept dept = service.get(id);
    	return dept;
	}

	@ResponseBody
	@RequestMapping(value = "/dept/add", method = { RequestMethod.POST })
	public Boolean addDept(@RequestBody Dept dept) {
		Boolean res = service.add(dept);
    	return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/dept/getstr/{id}", method = { RequestMethod.GET })
	public String getDeptStr(@PathVariable("id") Long id) {
    	Dept dept = service.get(id);
    	return dept.toString();
	}

	//如果有该方法,http://localhost:8081/默认进入该方法，否则到静态资源目录找index.html,index.htm
	//	@RequestMapping(value = "/index", method = RequestMethod.GET)
	//	public String index(ModelMap map) {
	//		map.put("name", "freemarker");
	//		map.put("age", "30");
	//		return "index";
	//	}
}

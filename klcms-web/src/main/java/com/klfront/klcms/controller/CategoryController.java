package com.klfront.klcms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.klfront.klcms.entity.Category;
import com.klfront.klcms.service.CategoryService;


/**
 * <p>
 * Title: CategoryController.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2019
 * </p>
 * <p>
 * Company: www.klfront.com
 * </p>
 *
 * @author Lu Jinlong
 * @date 2019-07-10
 * @version 1.0
 * 
 *          Modification History: Date Author Version Description
 *          ------------------------------------------------ 2019-07-10 Lu
 *          Jinlong 1.0 1.0 Version
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryService service;

	@ResponseBody
	@GetMapping("/rootlist")
	public List<Category> getRootList() {
		return service.getRootList();
	}

	@ResponseBody
	@GetMapping("/sublist/{parentId}")
	public List<Category> getSubList(@PathVariable("parentId") String parentId) {
		return service.findByParentId(parentId);
	}

	/**
	 * 
	 *
	 * @author Lu Jinlong
	 *
	 * @param map
	 * @param parentId 目前所有一级类别的父Id为02
	 * @return
	 */
	@GetMapping("/list.html")
	public String index(ModelMap map, @RequestParam(value = "parentId", required = false) String parentId) {
		Category parent=null;
		if (parentId == null) {
			parentId = "02";
		}
		parent = service.findById(parentId);
		if(null==parent) {
			parent=new Category();
			parent.setId("02");
			parent.setName("所有类别");
		}
		map.put("parent", parent);
		
		List<Category> list = service.findByParentId(parentId);
		map.put("list", list);
		return "category-list";
	}

	@GetMapping("/edit/{id}.html")
	public String item(ModelMap map, @PathVariable("id") String id) {
		Category category = service.findById(id);
		map.put("category", category);
		map.put("isAdd", "false");
		return "category-edit";
	}

	@GetMapping("/add/{parentId}")
	public String add(ModelMap map, @PathVariable("parentId") String parentId) {
		Category parent = service.findById(parentId);
		if(null==parent) {
			parent=new Category();
			parent.setId("02");
			parent.setName("所有类别");
		}
		Category category = new Category();
		category.setPid(parentId);
		category.setParentName(parent.getName());
		map.put("category", category);
		map.put("isAdd", "true");
		return "category-edit";
	}

	@PostMapping(value = "/save")
	public void save(HttpServletResponse response, @ModelAttribute("form") Category category,
			@RequestParam(value = "isAdd", required = true) Boolean isAdd) throws IOException {
		if(category.getId().startsWith(category.getPid())
				&&category.getId().length()==category.getPid().length()+2) {
			if (isAdd) {
				service.add(category);
			} else {
				service.update(category);
			}
			String url = "/category/list.html?parentId="+category.getPid();
			response.sendRedirect(url);
		}
		else{
			response.setCharacterEncoding("UTF-8");//解决浏览器显示时中文乱码
			response.sendError(412,"类别编号应该在父级类别编号上增加2位");
		}
	}

	@GetMapping("/delete/{parentId}/{id}")
	public void delete(HttpServletResponse response, @PathVariable("parentId") String parentId,
			@PathVariable("id") String id) {
		Boolean res = service.deleteById(id);
		try {
			if (!parentId.equals("0")) {
				response.sendRedirect("/category/list.html?parentId=" + parentId);
			} else {
				response.sendRedirect("/category/list.html");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

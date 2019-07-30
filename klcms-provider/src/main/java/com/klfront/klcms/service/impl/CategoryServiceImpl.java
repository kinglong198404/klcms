package com.klfront.klcms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klfront.klcms.dao.CategoryDao;
import com.klfront.klcms.entity.Category;
import com.klfront.klcms.service.CategoryService;

/**
 * <p>
 * Title: ArticleServiceImpl.java
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
 * @date 2019-07-08
 * @version 1.0
 * 
 * Modification History: 
 * Date			Author		Version		Description
 * ------------------------------------------------ 
 * 2019-07-08 	Lu Jinlong 	1.0 		1.0 Version
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao dao;

	@Override
	public boolean add(Category item) {
		Integer res = dao.add(item);
		return res>0;
	}

	@Override
	public boolean update(Category item) {
		Integer res = dao.update(item);
		return res>0;
	}

	@Override
	public boolean deleteById(String id) {
		Integer res = dao.deleteById(id);
		return res>0;
	}

	@Override
	public Category findById(String id) {
		return dao.findById(id);
	}
	
	@Override
	public List<Category> findByParentId(String parentId) {
		return dao.findByParentId(parentId);
	}

	@Override
	public List<Category> findAll() {
		List<Category> root = dao.getRootList();
		List<Category> list = dao.findAll();
		root.forEach(cat->{
			List<Category> sublist =list.stream().filter(item->{
				return item.getPid().equals(cat.getId());
			}).collect(Collectors.toList());
			cat.setSublist(sublist);
		});
		
		return root;
	}

	@Override
	public List<Category> getRootList() {
		return dao.getRootList();
	}

}

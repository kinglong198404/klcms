package com.klfront.klcms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klfront.klcms.dao.ArticleDao;
import com.klfront.klcms.entity.Article;
import com.klfront.klcms.service.ArticleService;

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
 * @date 2019-07-06
 * @version 1.0
 * 
 *          Modification History: Date Author Version Description
 *          ------------------------------------------------ 2019-07-06 Lu
 *          Jinlong 1.0 1.0 Version
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleDao dao;

	@Override
	public boolean add(Article item) {
		try {
			dao.add(item);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Article item) {
		try {
			Integer rows = dao.update(item);
			return rows > 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteById(Long id) {
		try {
			Integer rows = dao.deleteById(id);
			return rows > 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Article findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Article> findByPage(String category, String keyword, Integer pageIndex, Integer pageSize) {
		Integer fromIndex = pageIndex * pageSize;
		return dao.findByPage(category, keyword, fromIndex, pageSize);
	}

	@Override
	public Integer getPageCount(String category, String keyword, Integer pageSize) {
		Integer rows = dao.getRows(category, keyword);
		if (rows % pageSize == 0) {
			return rows / pageSize;
		} else {
			return rows / pageSize + 1;
		}
	}
}

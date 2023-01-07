package com.klfront.klcms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klfront.klcms.dao.ArticleDao;
import com.klfront.klcms.entity.Article;
import com.klfront.klcms.service.ArticleService;
import com.klfront.klcms.util.HtmlUtils;
import com.mysql.jdbc.StringUtils;

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
		Article item = dao.findById(id);
		if(item.getKeyword()!=null) {
			String regex = "，|,|、";
			String[] arr = item.getKeyword().split(regex);
			List<String> keywordList = new ArrayList<String>();
			for(String k : arr) {
				keywordList.add(k);
			}
			item.setKeywordList(keywordList);
		}
		return item;
	}

	@Override
	public List<Article> findByPage(String category, String keyword, Integer pageIndex, Integer pageSize) {
		Integer fromIndex = pageIndex * pageSize;
		List<Article> list = dao.findByPage(category, keyword, fromIndex, pageSize);
		list.forEach(item -> {
			String content = item.getContent();
			String imgSrc = HtmlUtils.getFirstImgSrc(content);
			item.setImgSrc(imgSrc);
			if(!StringUtils.isNullOrEmpty(item.getKeyword())) {
				String regex = "，|,|、";
				String[] arr = item.getKeyword().split(regex);
				List<String> keywordList = new ArrayList<String>();
				for(String k : arr) {
					keywordList.add(k);
				}
				item.setKeywordList(keywordList);
			}
		});
		return list;
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

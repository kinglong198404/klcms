package com.klfront.klcms.service;

import java.util.List;

import com.klfront.klcms.entity.Article;

/**
 * <p>
 * Title: ArticleService.java
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

public interface ArticleService {
	boolean add(Article item);

	boolean update(Article item);

	boolean deleteById(Long id);

	Article findById(Long id);

	/**
	 * 分页获取文章列表
	 *
	 * @author Lu Jinlong
	 *
	 * @param category  文章类别
	 * @param keyword   搜索关键字
	 * @param pageSize  每页条数
	 * @param pageIndex 当前页 从0开始
	 * @return
	 */
	List<Article> findByPage(String category,String keyword,Integer isSubject, Integer pageIndex,  Integer pageSize);

	/**
	 * 获取满足指定条件的记录的页数
	 *
	 * @author Lu Jinlong
	 *
	 * @param category
	 * @param keyword
	 * @param pageSize
	 * @return
	 */
	public Integer getPageCount(String category, String keyword, Integer isSubject, Integer pageSize);
}

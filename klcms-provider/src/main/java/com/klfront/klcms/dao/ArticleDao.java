package com.klfront.klcms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.klfront.klcms.entity.Article;
import com.klfront.klcms.entity.Dept;

/** 
 * <p>Title: ArticleDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.klfront.com</p>
 *
 * @author Lu Jinlong
 * @date 2019-07-06  
 * @version	1.0  
 *   
 * Modification History:   
 * Date			Author		Version		Description   
 * ------------------------------------------------
 * 2019-07-06	Lu Jinlong	1.0			1.0 Version   
 */
@Mapper
public interface ArticleDao {
	boolean add(Article article);
	Integer update(Article article);
	Integer deleteById(Long id);
	Article findById(Long id);
	/**
	 * 分页获取文章列表
	 *
	 * @author Lu Jinlong
	 *
	 * @param keyword 搜索关键字
	 * @param pageSize 每页条数
	 * @param pageIndex 当前页 从0开始
	 * @return
	 */
	List<Article> findByPage(@Param("categoryId") String categoryId,@Param("keyword") String keyword,
			@Param("isSubject") Integer isSubject, @Param("fromIndex") Integer fromIndex,@Param("pageSize") Integer pageSize);
	
	Integer getRows(@Param("categoryId") String categoryId,@Param("keyword") String keyword,@Param("isSubject") Integer isSubject);
}


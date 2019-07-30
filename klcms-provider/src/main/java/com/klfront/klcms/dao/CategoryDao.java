package com.klfront.klcms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.klfront.klcms.entity.Article;
import com.klfront.klcms.entity.Category;

/** 
 * <p>Title: CategoryDao.java</p>
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
public interface CategoryDao {
	/**
	 * 添加类别
	 *
	 * @author Lu Jinlong
	 *
	 * @param item
	 * @return 受影响的行数
	 */
	Integer add(Category item);
	
	/**
	 * 更新类别
	 *
	 * @author Lu Jinlong
	 *
	 * @param item
	 * @return 受影响的行数
	 */
	Integer update(Category item);
	

	Integer deleteById(String id);
	
	Category findById(String id);
	
	List<Category> findByParentId(String pid);
	
	List<Category> findAll();
	
	/**
	 * 查询第一级类别
	 *
	 * @author Lu Jinlong
	 *
	 * @return
	 */
	List<Category> getRootList();
}

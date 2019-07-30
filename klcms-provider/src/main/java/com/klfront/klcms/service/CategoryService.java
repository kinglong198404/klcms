package com.klfront.klcms.service;

import java.util.List;

import com.klfront.klcms.entity.Article;
import com.klfront.klcms.entity.Category;

/**
 * <p>
 * Title: CategoryService.java
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
 * 2019-07-06	Lu Jinlong	1.0			1.0 Version 
 */

public interface CategoryService {
	boolean add(Category item);

	boolean update(Category item);

	boolean deleteById(String id);

	Category findById(String id);
	
	List<Category> findByParentId(String pid);
	
	List<Category> findAll();
	
	List<Category> getRootList();
	

}

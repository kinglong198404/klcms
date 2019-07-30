package com.klfront.klcms.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.klfront.klcms.entity.Article;
import com.klfront.klcms.entity.WebSite;

/** 
 * <p>Title: WebSiteDao.java</p>
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
public interface WebSiteDao extends Serializable {
	boolean add(WebSite item);
	boolean update(WebSite item);
	boolean deleteById(Integer id);
	List<WebSite> findAll();
}

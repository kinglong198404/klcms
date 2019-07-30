package com.klfront.klcms.dao;

import org.apache.ibatis.annotations.Mapper;

import com.klfront.klcms.entity.User;

/** 
 * <p>Title: UserDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.klfront.com</p>
 *
 * @author Lu Jinlong
 * @date 2019-07-12  
 * @version	1.0  
 */
@Mapper
public interface UserDao {
	public User findByUsername(String username);
}

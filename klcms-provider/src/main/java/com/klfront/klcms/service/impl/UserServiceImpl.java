package com.klfront.klcms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klfront.klcms.dao.DeptDao;
import com.klfront.klcms.dao.UserDao;
import com.klfront.klcms.entity.User;
import com.klfront.klcms.service.UserService;

/** 
 * <p>Title: UserServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.klfront.com</p>
 *
 * @author Lu Jinlong
 * @date 2019-07-12  
 * @version	1.0  
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao dao;
	@Override
	public User getByUsername(String username) {	
		return dao.findByUsername(username);
	}

}

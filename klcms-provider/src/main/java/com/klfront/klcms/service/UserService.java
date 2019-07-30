package com.klfront.klcms.service;

import com.klfront.klcms.entity.User;

/** 
 * <p>Title: UserService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.klfront.com</p>
 *
 * @author Lu Jinlong
 * @date 2019-07-12  
 * @version	1.0  
 */

public interface UserService {
	User getByUsername(String username);
}

package com.klfront.klcms.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 登录用户
 * <p>Title: User</p>
 * <p>Description: </p>
 *
 * @author Lu Jinlong
 * @date 2019-07-06
 * @version 1.0
 * @since 1.8.0_92
 */
@Data
public class User implements Serializable{
	private Integer id;
	private String username;
	private String nickname;
	private String password;
	private String email;
}

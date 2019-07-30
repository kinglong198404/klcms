package com.klfront.klcms.entity;

import java.io.Serializable;

import lombok.Data;

/** 
 * <p>Title: WebSite.java</p>
 * <p>Description:技术网站导航 </p>
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
@Data
public class WebSite implements Serializable {
	/**
	 * 主键Id
	 */
	private Integer id;
	/**
	 * 网站名
	 */
	private String text;
	/**
	 * 链接地址
	 */
	private String link;
	/**
	 * 站点描述
	 */
	private String desc;
	/**
	 * 排序
	 */
	private Integer sort;
}

package com.klfront.klcms.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/** 
 * <p>Title: Article.java</p>
 * <p>Description: 文章类</p>
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
public class Article implements Serializable {
	private Long id;
	private String title;
	private String keyword;
	private String content;
	private String text;
	private String categoryId;
	private String author;
	private String authorName;
	/**
	 * 问题1：使用java.sql.Date，Mybatis做时间处理的时候把时分秒去掉了
	 * 解决方案：使用java.util.Date
	 * 
	 * 问题2：Restful接口返回数据格式化问题：默认为"createTime":1557969732000
	 * 解决方案1：在application.properties配置文件中增加下面两个配置：
				spring.jackson.time-zone=GMT+8 #时区设置
				spring.jackson.date-format=yyyy-MM-dd HH:mm:ss #日期期时格式设置置
	 * 解决方案2：使用@DateTimeFormat将String转换成Date，一般前台给后台传值时用。
				 使用@JsonFormat将Date转换成String 一般后台传值给前台时用。
				 使用@JsonFormat需要maven引入com.fasterxml.jackson.core下jackson-annotations
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createTime;
	private int pageView;
}

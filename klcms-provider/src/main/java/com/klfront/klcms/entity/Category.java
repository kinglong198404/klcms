package com.klfront.klcms.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 文章类别 
 * <p>Title: Read</p>
 * <p>Description: 只支持两级</p>
 *
 * @author Lu Jinlong
 * @date 2019-07-06
 * @version 1.0
 * @since 1.8.0_92
 */
@Data
public class Category implements Serializable {
	private String id;
	private String pid;
	private String parentName;
	private String name;
	private String icon;
	private String remark;
	private List<Category> sublist = null;
}

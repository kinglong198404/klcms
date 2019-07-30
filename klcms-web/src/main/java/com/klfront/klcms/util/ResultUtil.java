package com.klfront.klcms.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 接口返回结果的封装类
 * <p>Title: ResultUtil</p>
 * <p>Description: {code:400,result:xxx,message:xxx }</p>
 *
 * @author Lu Jinlong
 * @date 2019-07-13
 * @version 1.0
 * @since 1.8.0_92
 */
public class ResultUtil {
	private final static String SUCCESS = "200";
	private final static String LOGOUT = "401";
	private final static String ERROR = "500";

	/**
	 * 返回成功的结果
	 * 
	 * @return
	 */
	public static JSONObject successResult() {
		JSONObject jso = new JSONObject();
		jso.put("code", SUCCESS);
		jso.put("result", "success");
		return jso;
	}

	/**
	 * 返回成功的结果
	 * 
	 * @param result
	 * @return
	 */
	public static JSONObject successResult(Object result) {
		JSONObject jso = new JSONObject();
		jso.put("code", SUCCESS);
		jso.put("result", result);
		return jso;
	}

	/**
	 * 返回错误的结果
	 * 
	 * @param message
	 * @return
	 */
	public static JSONObject errorResult(String message) {
		JSONObject jso = new JSONObject();
		jso.put("code", ERROR);
		jso.put("message", message);
		return jso;
	}

	/**
	 * 返回登录失效的结果
	 * 
	 * @param codeEnum
	 * @return
	 */
	public static JSONObject logoutResult() {
		JSONObject jso = new JSONObject();
		jso.put("code", LOGOUT);
		jso.put("message", "登录失效！");
		return jso;
	}

}

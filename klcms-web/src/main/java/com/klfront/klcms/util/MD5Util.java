package com.klfront.klcms.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** 
 * <p>Title: MD5Util.java</p>
 * <p>Description: MD5摘要算法对文本进行处理</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.klfront.com</p>
 *
 * @author Lu Jinlong
 * @date 2019-07-12  
 * @version	1.0  
 */

public class MD5Util {
	 //写一个md5加密的方法
    public static String encode(String text) {
        //定义一个字节数组
        byte[] secretBytes = null;
        try {
              // 生成一个MD5加密计算摘要  
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(text.getBytes());
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String res = ""; 
		/** 把加密后字节数组转化成32位字符串 (把每一位转化成16进制的两位) */ 
		for (int i = 0; i < secretBytes.length; i++){ 
			int temp = secretBytes[i] & 0xFF; 
			/** 把temp值转化成16进制的两位数，如果不够两位前面补零 */ 
			if (temp <= 0xF){ 
				res += "0"; 
			} 
			res += Integer.toHexString(temp); 
		} 
		return res; 
    }
    
    public static String encode(String text,String key) {
    	String temp = encode(text);
    	String res = encode(temp+key);
		return res; 
    } 

}

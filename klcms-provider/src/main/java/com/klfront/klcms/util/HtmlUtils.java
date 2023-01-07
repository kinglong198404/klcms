package com.klfront.klcms.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/** 
 * <p>Title: HtmlUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: www.klfront.com</p>
 *
 * @author Lu Jinlong
 * @date 2023-01-07  
 * @version	1.0  
 *   
 * Modification History:   
 * Date			Author		Version		Description   
 * ------------------------------------------------
 * 2023-01-07	Lu Jinlong	1.0			1.0 Version   
 */

public class HtmlUtils {
	
	public static List<String> getImgSrc(String htmlStr) {
        List<String> pics = new ArrayList<String>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        
        //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据 
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

	public static String getFirstImgSrc(String htmlStr) {
		List<String> list =  getImgSrc(htmlStr);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}

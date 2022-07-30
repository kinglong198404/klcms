package com.klfront.klcms.util;

import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
/** 
 * <p>Title: HtmlExtract.java</p>
 * <p>Description: 抽取html里面的文本信息</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.klfront.com</p>
 *
 * @author Lu Jinlong
 * @date 2019-07-11  
 * @version	1.0  
 *   
 * Modification History:   
 * Date			Author		Version		Description   
 * ------------------------------------------------
 * 2019-07-11	Lu Jinlong	1.0			1.0 Version   
 */

public class HtmlExtract {

	private String strHtml;

    public HtmlExtract(String inStrHtml){
        strHtml = inStrHtml;
    }
    
    public String extractText(){
    	String result = strHtml;
       
    	String regEx_comment = "<!--[^-]*-->";
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
         
        Pattern p_comment=Pattern.compile(regEx_comment,Pattern.CASE_INSENSITIVE); 
        Matcher m_comment=p_comment.matcher(result); 
        result= m_comment.replaceAll(""); //过滤注释
        
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(result); 
        result= m_script.replaceAll(""); //过滤script标签 
         
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(result); 
        result=m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(result); 
        result=m_html.replaceAll(""); //过滤html标签 

        return result.trim(); //返回文本字符串 
    }
}

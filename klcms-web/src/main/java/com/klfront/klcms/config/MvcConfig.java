package com.klfront.klcms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.klfront.klcms.component.KFLocaleResolver;
import com.klfront.klcms.component.LoginHandlerInterceptor;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc   不要接管SpringMVC
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		//super.configurePathMatch(configurer);
		// 访问URL设置大小写不敏感
		AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
	}
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送 /login 请求转到 login.ftl
        registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/mobile").setViewName("mobile");
        registry.addViewController("/test").setViewName("test");
    }


    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            //注册登录拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //SpringBoot已经做好了静态资源映射,不需要处理静态资源拦截：*.css , *.js
            	//拦截所有页面检查是否登录
            	//排除对主页、文章详情页和登录相关页面的拦截
                registry.addInterceptor(new LoginHandlerInterceptor())
                		.addPathPatterns("/**")
                        .excludePathPatterns("/","/test","../shared/layout.ftl","/**.ico","/index.html","/mobile","/mobile/","/mobile/item/*","/article/item/*","/item/*","/login","/user/login","/error");
            }
        };
        return adapter;
    }

    // 国际化：区域和语言解析器
    @Bean
    public LocaleResolver localeResolver(){
        return new KFLocaleResolver();
    }

}

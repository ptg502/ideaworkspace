package com.aaa.sm;

import com.aaa.sm.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * className:MvcConfigClass
 * discriptoin:
 * author:zz
 * createTime:2018-11-10 16:44
 */
@SpringBootConfiguration  //标识该类为配置类
public class MvcConfigClass implements  WebMvcConfigurer {
    final String[] notLoginInterceptPaths = {"/emp/toUpload","/","/emp/toLogin","/emp/login","/static/**"};
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加自定义拦截器  配置拦截路径addPathPatterns和不拦截的路径excludePathPatterns 多个路径使用,隔开
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(notLoginInterceptPaths);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
      //  super.addResourceHandlers(registry);
    }
}

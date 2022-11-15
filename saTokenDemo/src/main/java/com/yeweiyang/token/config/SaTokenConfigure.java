package com.yeweiyang.token.config;

import cn.dev33.satoken.basic.SaBasicUtil;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.strategy.SaStrategy;
import cn.dev33.satoken.util.SaFoxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.config
 * @date 2022/1/27 3:55 下午
 * 拦截器
 * 在高版本 SpringBoot (≥2.6.x) 版本下，需要额外添加 @EnableWebMvc 注解才可以使注册拦截器生效。
 * 拦截器模式和AOP模式不可同时集成
 */
//@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册Sa-Token的注解拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        //registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");

        // 注册Sa-Token的路由拦截器
        registry.addInterceptor(new SaRouteInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/doLogin", "/user/**","/swagger-ui.html/**", "/swagger-resources/**")
        ;
    }


    // 在全局拦截器 或 过滤器中启用 Basic 认证
//    @Bean
//    public SaServletFilter getSaServletFilter() {
//        return new SaServletFilter()
//                .addInclude("/**").addExclude("/favicon.ico")
//                .setAuth(obj -> {
//                    SaRouter.match("/user/checkBasic", () -> SaBasicUtil.check("sa:123456"));
//                });
//
//    }


    /**
     * 重写 Sa-Token 框架内部算法策略
     */
    @Autowired
    public void rewriteSaStrategy() {
        // 重写 Token 生成策略
        SaStrategy.me.createToken = (loginId, loginType) -> {
            return SaFoxUtil.getRandomString(60)+"-jay";    // 随机60位长度字符串
        };
    }
}

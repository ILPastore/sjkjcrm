package com.sjkjcrm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录配置
 */
@Configuration
@Slf4j
public class WebSecurityConfig implements WebMvcConfigurer {

    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "user";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

//        // 排除配置
//        addInterceptor.excludePathPatterns("/error");
//        addInterceptor.excludePathPatterns("/login**");
//        addInterceptor.excludePathPatterns("/static");
//
//        // 拦截配置
//        addInterceptor.addPathPatterns("/**");
//        addInterceptor.addPathPatterns("/**").excludePathPatterns("/login");
//        addInterceptor.excludePathPatterns("/static");
//        super.addInterceptors(registry);

//        addInterceptor.addPathPatterns("/**").excludePathPatterns("/validate/**", "/static/**", "/error", "/css/**", "/customer/**");
        addInterceptor.addPathPatterns("/**").excludePathPatterns("/validate/**", "/static/**", "/error", "/css/**", "/js/**", "/images/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            log.info(request.getRequestURL().toString());
            HttpSession session = request.getSession();
            if (session.getAttribute(SESSION_KEY) != null) {
                return true;
            }

            // 跳转登录
            String url = "/validate/login";
            response.sendRedirect(url);
            return false;
        }
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/resources/static/images/");
//        registry.addResourceHandler("/js/**").addResourceLocations("classpath:./resources/static/js/");
//        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/resources/static/css/");
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/customer/homepage").setViewName("customer/home");
//        registry.addViewController("/customer/customerpage").setViewName("customer/list");
        registry.addViewController("/customer/welcome").setViewName("welcome");
        registry.addViewController("/customer/addcustomer").setViewName("customer/edit");
        registry.addViewController("/validate/login").setViewName("login");
    }
}

package com.nhom27.nhatkykhambenh.configuration;

import com.nhom27.nhatkykhambenh.intercepter.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register", "/forgotpassword/**", "/resetpassword/**",
                        "/error", "/css/**", "/js/**", "/image/**",
                        "/baguetteBox.js-dev/**", "/public/**",
                        "/font/**", "/news-ticker-controls-acme/**", "/vendor/**");
    }
}

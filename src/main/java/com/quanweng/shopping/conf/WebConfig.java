package com.quanweng.shopping.conf;

import com.quanweng.shopping.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOriginPatterns("*") // 允许所有来源
                    .allowedMethods("*")         // 允许所有HTTP方法
                    .allowedHeaders("*")         // 允许所有头信息
                    .exposedHeaders("*")         // 暴露所有头信息
                    .allowCredentials(true)      // 如果需要凭据，注意与allowedOrigins("*")不兼容
                    .maxAge(1800);
        }
}

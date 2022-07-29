package com.atguigu.gulimall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class GulimallCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        //配置跨域
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许的请求头
        corsConfiguration.addAllowedHeader("*");
        //允许的请求方法 ==> GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
        corsConfiguration.addAllowedMethod("*");
        // 允许的请求源 （如：http://localhost:8080）
        corsConfiguration.addAllowedOrigin("*");
        //是否允许携带Cookie
        corsConfiguration.setAllowCredentials(true);

        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsWebFilter(source);
    }
}

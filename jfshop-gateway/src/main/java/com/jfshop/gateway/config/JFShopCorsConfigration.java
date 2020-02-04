package com.jfshop.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class JFShopCorsConfigration {


    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://manage.jfshop.com");//允许跨域的域名，如果要携带cookie不能写**
        corsConfiguration.setAllowCredentials(true);//设置是否允许携带cookie
        corsConfiguration.addAllowedMethod("*");//代表允许所有的请求方法
        corsConfiguration.addAllowedHeader("*");//允许携带让任何头信息
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(configurationSource);
    }
}

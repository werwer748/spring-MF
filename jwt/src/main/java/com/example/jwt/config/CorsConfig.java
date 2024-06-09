package com.example.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration // 메모리에 올려야 됨.
public class CorsConfig {

    @Bean // 굳이 필터로 안두고 httpSecurity에 cors 설정에 집어넣어두됨
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // CORS 구성 등록, 관리
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // CORS 요청을 허용한다.
        config.addAllowedOrigin("*"); // 모든 경로에서 오는 요청을 허용한다.
        config.addAllowedHeader("*"); // 모든 헤더 설정을 허용한다.
        config.addAllowedMethod("*"); //

        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
    }
}

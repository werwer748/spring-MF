package com.example.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/", "/home").permitAll() // 인증없이 접근 가능
                            .anyRequest().authenticated()
                )
                // ui에서 구글로그인 버튼을 통해 http://localhost:8081/oauth2/authorization/google 경로로 요청(로그인시도 URL)
                .oauth2Login(httpSecurityOAuth2LoginConfigurer ->
                        httpSecurityOAuth2LoginConfigurer
                                .loginPage("/")
                                .defaultSuccessUrl("/")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                );

        return httpSecurity.build();
    }
}

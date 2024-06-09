package com.example.jwt.config;

import com.example.jwt.filter.JwtAuthenticationFilter;
import com.example.jwt.filter.JwtAuthorizationFilter;
import com.example.jwt.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsConfig corsConfig;
    private final CustomerService customerService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // JWT 서버구현: React.js 프론트 구성(로그인 폼 필요 X, 인증방식, jwt 토큰 방식 - ID, PWD, 세션 X)
        // 서버(Spring) <--- CORS ---> 클라이언트(React)
        // CSRF(form - CSRF 토큰을 넘기게 되어있다.) jwt를 써서 굳이 사용할 필요가 없음
        http
//                .csrf(httpSecurityCsrfConfigurer ->
//                        httpSecurityCsrfConfigurer
//                                .disable()
//                ) // 람다 전후
                .csrf(AbstractHttpConfigurer::disable) //? csrf 사용하지 않음
                .sessionManagement(httpSecuritySessionManagementConfigurer -> //? 세션을 만들지 않는다.
                        httpSecuritySessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
//                .formLogin(form -> form.disable()) // 람다 전후
                .formLogin(AbstractHttpConfigurer::disable)
//                .httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.disable()) // 람다 전후
                .httpBasic(AbstractHttpConfigurer::disable) // 기본적인 인증방식을 아이디 패스워드 방식으로 하지않겠다. => jwt토큰 쓸꺼니까
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/api/v1/user/**").hasAnyRole("USER", "MANAGER", "ADMIN") // 세 권한중 하나라도 가지고 있는 유저는 해당 URL에 접근이 가능하다
                                .requestMatchers("/api/v1/manager/**").hasAnyRole("MANAGER", "ADMIN") // 매니저, 어드민만 접근가능
                                .requestMatchers("/api/v1/admin/**").hasAnyRole("ADMIN") // 어드민만 접근가능
                                .anyRequest().permitAll() // 외의 모든 요청은 제한을 두지 않는다.
                )
                // CORS 정책을 정해야 한다. - 필터 객체를 만들어서 등록하기.
                //.addFilter(corsFilter()); // 파일로 분리했기때문에 주석
                .with(new MyCustomerDSL(), MyCustomerDSL::builder);

        return http.build();
    }

    // 사용자정의 DSL(Domain Specific Language) - 구성을 좀 더 쉽게 작성할 수 있도록 작성하는 문법? => 유연성있는 작입이 가능하다.
    //? AbstractHttpConfigurer<MyCustomerDSL, HttpSecurity> => 현재 클래스에서 HttpSecurity 설정을 더 확장하겠다는 의미
    public class MyCustomerDSL extends AbstractHttpConfigurer<MyCustomerDSL, HttpSecurity> {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            //? AuthenticationManager => 스프링 시큐리티가 올라가면 자동으로 메모리에 만들어져있게 된다. 그래서 이런식으로 가져와서 써야 함.
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilter(corsConfig.corsFilter())
                    .addFilter(new JwtAuthenticationFilter(authenticationManager)) // AuthenticationManager가 필요하다.
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, customerService));
        }

        public HttpSecurity builder() {
            return getBuilder();
        }
    }
}

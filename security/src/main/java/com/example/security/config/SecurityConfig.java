package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 설정파일
public class SecurityConfig {

    // 회원가입시 패스워드를 암호화 해야 한다.
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // SecurityFilterChain <= 내부적으로 인증처리를 하는 클래스
    @Bean // 스프링빈 등록
    public SecurityFilterChain securityFilterChain(
            //? 클라이언트로 넘어오는 요청 - 요청을 검증한 후 URL로 접속이 가능하게끔 해준다.
            HttpSecurity http // * 클라이언트의 요청 URL
    ) throws Exception {
        http
                // 권한 부여 규칙 정의 시작
                .authorizeHttpRequests(
                // 요청에 대한 권한 규칙을 부여해야 한다. 그래서 http로 받은 내용을 변수로 받아줘야 한다.-> 이 변수로 권한에 대한 규칙을 설정함.
                authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                /*
                                .requestMatchers("/ui/**").permitAll() // 해당경로 접근은 모두에게 오픈(인증없이), 여러개를 걸 수 있고
                                .requestMatchers("/admin/**").hasRole("ADMIN") // 인증이 되야 하고, ADMIN권한이 있는 사람의 요청만 허용
                                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // USER, ADMIN 중 하나의 권한만 있다면 접근 가능
                                .anyRequest().authenticated() // 설정한 경로외의 나머지 경로는 인증된 사람이면 다 통과 아니면 인증을 요구
                                 */
                                .requestMatchers("/api/**").authenticated() // /api로 시작하는 모든 경로는 인증 후 접근이 가능하다.
                                .requestMatchers("/books/**").authenticated() // /books라는 경로로 들어오는 모든 요청은 인증 후 접근이 가능하다.
                                // .requestMatchers("/api/**", "/books/**").authenticated() // 위 두가지를 이렇게 합쳐서도 사용 가능
                                .anyRequest().permitAll() // 나머지는 인증없이 접근을 허용한다.
                )// 권한부여 규칠 끝
                // 로그인 폼 설정 시작
                .formLogin(httpSecurityFormLoginConfigurer ->
                        httpSecurityFormLoginConfigurer
                                // 권한부여 규칙에서 리퀘스트 매쳐에 정의해놓은 url로 접근시 login페이지로 리다이렉트 됨.
                                .loginPage("/") // login page URL -> /login -> MainController -> View(index.html)
                                // UsernamePasswordAuthenticationFilter: username, password를 가로채서 필요한 작업을 하게 된다.
                                // ----> UserDetailService(interface) 를 실행하게 된다. ----> 서비스로 구현해 줘야 함
                                .loginProcessingUrl("/loginProcess") // 해당 URL로 요청시 스프링 시큐리티로 제어권을 넘긴다.(username, password)
                                .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉션 시킬 경로
                                .failureUrl("/?error=true") // 로그인 인증에 실패했을 때 리다이렉션 시킬 경로
                                .permitAll() // 로그인 페이지는 누구나 접근이 가능하게 풀어줘야 한다.
                ) // 로그인 폼 설정 끝
                // 로그아웃 처리 시작
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer
                                .logoutUrl("/logout") // 로그아웃 URL -> 시큐리티가 요청을 가로채서 로그아웃 처리를 해준다.
                                .logoutSuccessUrl("/?logout=true") // 로그아웃 성공 후 리다이렉션 경로
                                .invalidateHttpSession(true) // 현재 만들어진 세션을 종료시킴. - 세션 무효화(true)
                                .deleteCookies("JSESSIONID") // 쿠키가 있다면 삭제. 보통 JSESSIONID 이름
                        ); // 로그아웃 처리 끝
        return http.build(); // 빌드로 적용시켜서 SecurityFilterChain에 정보를 넘겨준다.
    }
}

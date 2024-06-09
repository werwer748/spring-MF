package com.example.jwt.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwt.entity.Customer;
import com.example.jwt.entity.CustomerUser;
import com.example.jwt.service.CustomerService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private CustomerService customerService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, CustomerService customerService) {
        super(authenticationManager);
        this.customerService = customerService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("인증이나 권한이 필요한 요청들은 이 부분을 실행");
        String jwtHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("jwtToken: {}", jwtHeader);
        // 토큰이 넘어오지 않은 경우 로직 종료
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        // 토큰이 넘어온 경우 검증이 필요
        String jwtToken = jwtHeader.replace("Bearer ", "");
        String userId = JWT.require(Algorithm.HMAC256(JwtProperties.SECRET))
                .build()
                .verify(jwtToken) // 토큰을 검증
                .getClaim("userId")
                .asString();

        if (userId != null) {
            Customer customer = customerService.findByUserId(userId);
            CustomerUser customerUser = new CustomerUser(customer);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    customerUser, null, customerUser.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        chain.doFilter(request, response);
    }
}

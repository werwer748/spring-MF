package com.example.jwt.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwt.entity.Customer;
import com.example.jwt.entity.CustomerUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

// * 로그인 시도
@Slf4j
@RequiredArgsConstructor // 로그인 경로는 /login이 되야 한다. -> username, password 두값이 넘어와야 한다.
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
//        // * 여기에 로그인 api가 생겼는데 스웨거를 작성해야하는경우 따로 컨트롤러를 생성하고 거기에 똑같은 엔드포인트로 문서작성하면됨
//        // * 필터가 먼저작동되서 요청을 가로채가서 상관없음.
//        this.authenticationManager = authenticationManager;
//        setFilterProcessesUrl("/auth/login"); // Set custom login URL
//    }

    @Override // UsernamePasswordAuthenticationFilter 가 등록되면 내부에서 attemptAuthentication은 무조건 처음에 자동으로 실행 됨.
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("JwtAuthenticationFilter ::: 로그인 시도중...");
        // PrincipalDetailsService 객체의 loadUserByUsername()을 실행
        // Authentication 객체 생성 -> SecurityContextHolder에 등록 --> View에 사용할 수 도 있으니까..?
        // JWT 토큰도 여기서 생성
        // form, json(문자열)으로 날아오는 두가지 방식 모두 체크가 필요함. --> IO(BufferedReader), ObjectMapper(객체=Customer)
        try {
            /* // - 스트림으로 데이터 읽어보기(폼데이터)
            BufferedReader br = request.getReader(); // request에서 스트림을 읽어온다.
            String input = null;

            while((input = br.readLine()) != null) {
                log.info(input);
            }*/
            // 오브젝트맵퍼 써보기(json문자열)
            ObjectMapper objectMapper = new ObjectMapper();
            Customer customer = objectMapper.readValue(request.getInputStream(), Customer.class);
            log.info("customer : {}", customer);
            // username, password -> 스프링에서 제공하는 유저정보를 저장하는 클래스를 사용한다.
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(customer.getUserId(), customer.getPassword());

            // PrincipalDetailsService -> loadUserByUsername 실행된다.
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            CustomerUser customerUser = (CustomerUser) authentication.getPrincipal();
            log.info("customerUser : {}", customerUser);
            log.info("customerUser.getUserId() : {}", customerUser.getUserId());
            log.info("customerUser.getUsername()() : {}", customerUser.getUsername());

            return authentication; // SecurityContextHolder에 저장 된다. ==> 여기까지가 DB 인증처리

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // attemptAuthentication가 정상처리되고 난 후 실행되는 메서드
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // JWT 토큰 생성 -> 클라이언트에 헤더로 전달을 해준다.
        log.info("인증 통과 => successfulAuthentication 실행");
        Date now = new Date(System.currentTimeMillis());
        CustomerUser customerUser = (CustomerUser) authResult.getPrincipal();
        String jwtToken = JWT.create()
                .withSubject("JWT 토큰")
                .withClaim("id", customerUser.getId())
                .withClaim("userId", customerUser.getUserId())
//                .withAudience(customerUser.getUserId()) // aid
//                .withIssuedAt(now) // iat
                .withExpiresAt(new Date(now.getTime() + (JwtProperties.EXPIRATION_TIME))) // 10분 만료시간
//                .withIssuer("hugo") // iss
                .withArrayClaim("authorities", customerUser.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority) // public Collection<GrantedAuthority> getAuthorities() {return this.authorities;}
                        .toArray(String[]::new) // ROLE_USER, ROLE_MANAGER ....
                )
                .sign(Algorithm.HMAC256(JwtProperties.SECRET));
        // 헤더에[ 응답
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
        // Body에 내려보내기
        // JSON 응답 작성
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"accessToken\": \"" + jwtToken + "\"}");
        response.getWriter().flush();
    }
}

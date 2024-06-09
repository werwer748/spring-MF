package com.example.oauth2.service;

import com.example.oauth2.entity.Member;
import com.example.oauth2.entity.PrincipalUser;
import com.example.oauth2.entity.Role;
import com.example.oauth2.repository.MemberRepository;
import com.example.oauth2.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
//                                      implements OAuth2UserService<OAuth2UserRequest, OAuth2User>
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        log.info("userRequest={}", userRequest.getClientRegistration()); // google에서 제공한 정보 확인
//        log.info("getAccessToken.getTokenValue={}", userRequest.getAccessToken().getTokenValue()); // 토큰값
//        log.info("userInfo={}", super.loadUser(userRequest).getAttributes()); // 로그인한 유저정보

        // * 회원가입을 진행하기 위한 정보 추출
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId(); // google
//        String providerId = oAuth2User.getAttributes().get("sub").toString();
        String providerId = oAuth2User.getAttribute("sub");
        String username = provider + "_" + providerId;

        // * DB에 저장하기 전 가입한 유저인지 체크
        Member member = null;
        Optional<Member> optional = memberRepository.findByUsername(username);
        if (optional.isPresent()) {
            log.info("이미 가입한 유저 => 로그인");
            member = optional.get();
        } else {
            log.info("첫 로그인 유저 => 회원가입");
            String password = passwordEncoder.encode("임의의값");
            String uname = (String) oAuth2User.getAttribute("name");
            String email = (String) oAuth2User.getAttribute("email");

            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName("USER");
            roles.add(userRole);
            member = new Member();
            member.setUsername(username);
            member.setPassword(password);
            member.setUname(uname);
            member.setEmail(email);
            member.setProvider(provider);
            member.setProviderId(providerId);
            member.setRoles(roles);

            memberRepository.save(member);
        }

        // 로그인 성공 -> 세션
        // OAuth2User 타입(interface)으로 반환되야함 =>  OAuth2User를 구현하면서 Member값으로 구성된 클래스가 필요
        return new PrincipalUser(member); // 세션으로 만들어진다.
    }
}

/*
* userInfo={
* sub=110921855845262893751,
* name=강준기,
* given_name=준기,
* family_name=강,
* picture=https://lh3.googleusercontent.com/a/ACg8ocLG8x_Lct7foP79SraExwUxjrlSBcPZOf6YnMM6_q2VNSLD7Q=s96-c,
* email=hugo.aws.444@gmail.com,
* email_verified=true
* }
*
* Member 테이블에 정보를 기재(회원가입)
* */

package com.example.oauth2.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class PrincipalUser extends User implements OAuth2User {

    private final Member member;

    public PrincipalUser(Member member) {
        super(
                member.getUsername(),
                member.getPassword(),
                getAuthorities(member.getRoles())
        );
        this.member = member;
    }

    public static Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .toList();
    }

    // implements로 타입을 맞추기 위한 용도..
    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public String getName() {
        return null;
    }

    public Member getMember() {
        return member; // 이렇게 principal로 가는듯??
    }

//    public void setMember(Member member) {
//        this.member = member;
//    }
}

package com.example.jwt.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class CustomerUser extends User {

    private Long id;

    private String userId;

    private List<String> roles = new ArrayList<>();

//    public CustomerUser(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
    public CustomerUser(Customer customer) {
        super(
                customer.getUserId(),
                customer.getPassword(),
                makeConvertRole(customer.getRoleList()) // GrantedAuthority -> Collection(List)
        );
        this.id = customer.getId();
        this.userId = customer.getUserId();
        this.roles = customer.getRoleList();
    }

    // static이어야 먼저 호출된다?
    public static Collection<? extends GrantedAuthority> makeConvertRole(List<String> list) {
        return list.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();
    }
}

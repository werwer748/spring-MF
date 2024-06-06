package com.example.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerUserDetails extends User { // User(username(아이디), password, authorities(권한정보))

    private Customer customer; // 로그인에 성공한 고객 정보(상속받은 User에 포함되지 않는 정보도 필요하기 때문에)

//    public CustomerUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) { // 메뉴얼하게 구현할 때
    public CustomerUserDetails(Customer customer) {
        super(
                customer.getUserId(),
                customer.getPassword(), // 넘겨주면 비밀번호 검사를 하는...?
                getAuthorities(customer.getRoles()) // USER, MANAGER, ADMIN
        );

//        customer.setPassword(null); // 이래서 엔티티에 setter 열어두면 안됨 => DB 업데이트 쳐짐
        this.customer = customer; // 비밀번호까지 실린다...
    }

    public static Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream() // role안에 들어있는 여러개의 객체를 스트림으로 변경한다.(다른타입으로 바꾸기 위해서)
                // ROLE_USER, ROLE_MANAGER, ROLE_ADMIN => 시큐리티는 권한을 가져올떄 ROLE_ 를 붙여서 가져오니까 이렇게 바꿔줘야 됨.
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}

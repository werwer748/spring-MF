package com.example.jwt.service;

import com.example.jwt.entity.Customer;
import com.example.jwt.entity.CustomerUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final CustomerService customerService;

    @Override // 기존 세션방식처럼 폼으로 로그인하는게 아니기 때문에 필터로 걸어서 이 로직을 타도록 해야한다.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerService.findByUserId(username);
        return new CustomerUser(customer); // UserDetails 모양으로 바꿔서 customer를 리턴해야 함.
    }
}

/*
* ID, PWD --> /login --> UsernamePasswordAuthenticationFilter --> PrincipalDetailsService(loadUserByUsername)
* */

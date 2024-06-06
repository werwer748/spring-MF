package com.example.security.service;

import com.example.security.entity.Customer;
import com.example.security.entity.Role;
import com.example.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;

    // 회원가입
    @Transactional
    public Customer memberRegister(Customer customer) {
        // 1. 패스워드 암호화
        String encodePassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodePassword);

        // 2. 권한 부여(USER, MANAGER, ADMIN)
        Set<Role> roles = new HashSet<>(); // DB업데이트에 따라 ROLE번호가 다를 수 있음. => RoleRepository 만듬
        Role userRole = roleService.findByName("USER");
        roles.add(userRole);
        customer.setRoles(roles);

        // 저장하고 리턴
        return memberRepository.save(customer); // insert SQL
    }
}

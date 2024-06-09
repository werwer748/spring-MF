package com.example.jwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String password;

    // 한명의 회원이 여러개의 권한을 가질 수 있다. - Customer
    // member_roles (관계 테이블)
    // 하나의 권한은 여러명의 회원이 가질 수 있다. - Role === 기존까지 공부했던 방식

    private String roles; // "USER, MANAGER, ADMIN" === , 로 구분해 여러개를 하나의 문자열로 넣는 방식

    // 권한을 리스트로 만들어서 리턴해주는 메서드
    public List<String> getRoleList() {
        if (!roles.isEmpty()) {
            return Arrays.asList(roles.split(","));
        }
        return new ArrayList<>();
    }
}

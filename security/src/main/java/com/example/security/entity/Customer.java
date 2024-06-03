package com.example.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 일련번호

    @Column(nullable = false, unique = true)
    private String userId; // 고객ID

    private String password; // 고객 비밀번호(암호화)
    private String customerName; // 고객이름
    private int age; // 나이
    private String rating; // 등급
    private String occupation; // 직업
    private String email; // 이메일

    @Column(columnDefinition = "int default 0")
    private int reserves; // 적립금

//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews; // 컬럼으로 만들어지면 안됨!

    // 장바구니에 담은 물건 정보
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Cart> carts;

    // 한명의 회원은 여러개의 권한(중복 - Set)을 가지고 있다.
    @ManyToMany // M:N(다:다) - 별도의 테이블이 필요하다.
    @JoinTable(
            name = "member_roles",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}

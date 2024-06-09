package com.example.oauth2.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username; // google_{sub값} 으로 아이디를 만들어 줌.
    private String password; // 임의의 값!
    private String uname;
    private String email;

    // OAuth2에서 추가되는 정보
    private String provider; // 어떤 소셜에서 왔는지
    private String providerId; // sub=123123123123123

    @CreationTimestamp
    private Timestamp createDate;

    // 권한정보(USER, MANAGER, ADMIN): Role
    @ManyToMany
    @JoinTable(
            name = "member_roles",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}

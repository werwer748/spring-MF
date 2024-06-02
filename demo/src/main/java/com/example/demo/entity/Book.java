package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity // 테이블과 매핑
@Data
public class Book { // Book(Object) ---> ORM(Hibernate): 스프링에서 쓰기편하게 만든게 JPA ---> DB: book table 생성

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 연결된 DB의 생성전략을 따른다.
    private Long id; // int보다 long이 관리가 쉬움

    @Column(length = 50, nullable = false, unique = true) // 50자 제한, null 안됨, 유니크 제약 조건
    private String title;
    private int price;
    private String author;
    private int page;
}

package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 리뷰작성자(Customer정보)가 필요함
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false) // FK
    private Customer customer; // 오브젝트는 컬럼으로 만들어지지 않음. => foreign key

    // 어떤 책에 리뷰가 작성이 되었는가?
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private int cost; // 평점

    private String content; // 리뷰 내용

    private Date createdAt;
}

/*
* Hibernate:
    create table review (
        id bigint not null auto_increment,
        content varchar(255),
        cost integer not null,
        created_at datetime(6),
        customer_id bigint not null,
        primary key (id)
    ) engine=InnoDB
* Hibernate:
    alter table review
       add constraint FKgce54o0p6uugoc2tev4awewly(랜덤으로 생성되는 외래키 이름)
       foreign key (customer_id)
       references customer (id)
*/

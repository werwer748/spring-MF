package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    /*
    고객(Object) -> ORM -> TABLE: Review(리뷰)
        1 (PK, 부모)      :      N(FK, 자식)
          ------- 연관관계 --------| 주인 - 외래키필드가 리뷰에 생성되기 떄문에
     고객(부모)는 자신이 작성한 리뷰목록(List<Review>)을 가지고 있어야 함.
    */

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

    @Column(columnDefinition = "int default 0")
    private int reserves; // 적립금

    // JPA에 연관관계의 주인이 아닌것을 알려야함. => mappedBy
    // CascadeType.ALL? 고객이 삭제가 되면 연결된 리뷰 정보도 함께 삭제되도록 설정.
    /*
    ? fetch = FetchType.EAGER: 즉시로딩 - 메모리를 비효율적으로 사용하게 될 수 있다.(연관관계테이블 다같이 한번에 죠인)
    ? fetch = FetchType.LAZY: 지연로딩 - 메모리를 절약할 수 있다. (연관된 테이블 정보가 필요할 때 -get으로 꺼낼때 따로 호출)
    ? 참조 순환이(N + 1: 데이터베이스 참조가 계속 발생) 발생할 수 있다.

    * default는 EAGER
    */
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews; // 컬럼으로 만들어지면 안됨!

    // 장바구니에 담은 물건 정보
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Cart> carts;
}

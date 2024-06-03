package com.example.jpa.repository;

import com.example.jpa.entity.Customer;
import com.example.jpa.entity.QCustomer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CustomerQueryDSLRepository { // 보통은 implements 사용해서 많이 구현함

    private final JPAQueryFactory queryFactory;

    // Customer -> QCustomer가 생성된다.
    public Customer getUserIdAndPassword(String userId, String password) {
        QCustomer customer = QCustomer.customer;
        log.info("==== queryDSL 실행 ==== ");

        return queryFactory.selectFrom(customer)
                .where(
                        customer.userId.eq(userId)
                                .and(customer.password.eq(password))
                ).fetchOne();
    };

    public List<Customer> getRating(String rating) {
        QCustomer customer = QCustomer.customer;

        return queryFactory.selectFrom(customer)
                .where(
                        customer.rating.eq(rating)
                ).fetch();
    };
}

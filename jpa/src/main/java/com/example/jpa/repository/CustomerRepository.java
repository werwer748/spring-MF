package com.example.jpa.repository;

import com.example.jpa.dto.CustomerDto;
import com.example.jpa.dto.ICustomerDto;
import com.example.jpa.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // 1. JpaRepository에서 제공하는 메서드를 사용하기
    // 2. 쿼리 메서드를 사용하는 방법(규칙을 가지고 만들어지는 메서드)
    //SQL -> select c from customer c where c.age = 30;
//    public Customer findByAge(int age); // 검색 결과 고객이 두명이상이면 에러
    public List<Customer> findByAge(int age); // 검색결과 고객이 두명이상이면 모두 가져옴
    public Customer findTopByAge(int age); // 검색 결과 고객이 두명 이상이라면 첫번째 데이터를 반환

    // select c from customer c where c.userId=:userId and c.password=:password
    public Customer findByUserIdAndPassword(String userId, String password);

    public Customer findByUserIdIs(String userId);

    public List<Customer> findByAgeGreaterThanEqual(int age);

    // 3. JPQL(사용자 정의 쿼리): Java Persistence Query Language
    // - Entity(Object)를 기준으로 쿼리 만들기
    // - DB Table 기준으로 쿼리를 만들수 있다.(nativeQuery)

//    @Query("select c from Customer c where c.rating = :rating") // entityQuery
    //List<Customer> getRating(@Param("rating") String rating);
    @Query(value = "select * from customer where rating=?1", nativeQuery = true) // DB Table
    List<Customer> getRating(String rating);

    // DB에서 사용중인 컬럼명써야됨 userId 이렇게하면 500 에러..
    @Query(value = "select * from customer where user_id=?1 and password=?2", nativeQuery = true)
    Customer getUserIdAndPassword(String userId, String password);

    // 4. QueryDSL 사용하기

    // 5. 혼자 해보기
    @Query("select new com.example.jpa.dto.CustomerDto(" +
            "c.id, c.customerName, c.age, c.occupation"
            + ") from Customer c")
    Page<CustomerDto> findPage(Pageable pageable);

    Page<ICustomerDto> findAllBy(Pageable pageable);
}

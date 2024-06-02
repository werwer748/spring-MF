package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // JpaRepository -> CRUD Method 정의(Table, PK)
public interface BookRepository extends JpaRepository<Book, Long> {
    // JPA에서 제공해주는 메서드를 사용
    // - 전체리스트 가져오기 - findAll()..
    // - 데이터 저장하기 - save()
    // - 특정 데이터 가져오기 findById(Long id)

    // 트렌젝션 처리(All or Nothing)
    // - 특정 데이터 수정하기 -> save(): 기존 pk값이 존재하면 -> update SQL
    // 데이터가 저장되는 공간 : 영속성(일관성, 정보가 항상 일치) 메모리 (더티체킹)
    // Book(Object) <-----> book(Table): 메모리의 객체가 변경되면 테이블에 정보도 업데이트된다. - 자동 더티체킹

    // - 특정데이터 삭제하기 deleteById(Long id)
}

// interface BookRepository => 인터페이스니까 어디서는 구현이 되야 하는데?
/*
    MyBatis의 경우
    public class SqlSessionFactory implements BookRepository {}

    JPA의 경우
    public class EntityManagerFactory implements BookRepository {}
*/

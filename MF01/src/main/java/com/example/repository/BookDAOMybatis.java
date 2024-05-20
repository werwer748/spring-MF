package com.example.repository;

import com.example.entity.BookDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class BookDAOMybatis { // DB 연결부 분리하는 리팩토링 (어차피 DAO는 세션만 필요함) => 중앙집중식 관리가 가능해짐

    /* // 해당 부 분리 리팩토링 전
        private static SqlSessionFactory sqlSessionFactory; // SqlSession이 들어감 => 이 세션을 꺼내와서 DB로 요청함.

        // DB 접속은 프로그램 실행시 한번만 처리 => static 초기화 블럭
        static {
            try {
                // XML에서 SqlSessionFactory 빌드하기
                String resource = "mybatis-config/config.xml";
                InputStream inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); // Connection(SqlSession)
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    */
    public List<BookDTO> bookList(String search) {
        /* // session 분리 리팩토링 전
            SqlSession session = sqlSessionFactory.openSession();
            // selectList: 전체 리스트 가져올 떄 사용 - 인자는 일반적으로 이 메서드명을 써준다.
            List<BookDTO> list = session.selectList("bookList"); // bookList => SQL
            session.close();
            return list;
         */

        try (SqlSession session = MybatisUtil.openSession()) { // try문 종료시 자동으로 close()
//            return session.selectList("bookList");
            return session.selectList("com.example.repository.BookDAOMybatis.bookList", search); // 더 정확하게 하고 싶으면..
        }
    }

    // 선행학습
    public void deleteBook(int num) {
        try (SqlSession session = MybatisUtil.openSession()) {
            session.delete("com.example.repository.BookDAOMybatis.deleteBook", num);
            session.commit();
        }
    }
    // 수업
    public int bookDelete(int num) {
        try (SqlSession session = MybatisUtil.openSession()) {
            int cnt = session.delete("bookDelete", num);
            session.commit(); // 완료
            return cnt;
        }
    }

    // 선행학습
    public void createBook(BookDTO bookDTO) {
        try (SqlSession session = MybatisUtil.openSession()) {
            session.insert("com.example.repository.BookDAOMybatis.createBook", bookDTO);
            session.commit();
        }
    }
    // 수업
    public int bookInsert(BookDTO dto) {
        try (SqlSession session = MybatisUtil.openSession()) {
            int cnt = session.insert("bookInsert", dto);
            session.commit();
            return cnt;
        }
    }

    // 선행학습
    public BookDTO getBook(int num) {
        try (SqlSession session = MybatisUtil.openSession()) {
            return session.selectOne("com.example.repository.BookDAOMybatis.getBook", num);
        }
    }
    // 수업
    public BookDTO bookView(Long num) {
        try (SqlSession session = MybatisUtil.openSession()) {
            return session.selectOne("bookView", num);
        }
    }

    public void updateBook(BookDTO book) {
        try (SqlSession session = MybatisUtil.openSession()) {
            session.update("com.example.repository.BookDAOMybatis.updateBook", book);
            session.commit();
        }
    }

    public int bookUpdate(BookDTO dto) {
        try (SqlSession session = MybatisUtil.openSession()) {
            int cnt = session.insert("bookUpdate", dto);
            session.commit();
            return cnt;
        }
    }
}

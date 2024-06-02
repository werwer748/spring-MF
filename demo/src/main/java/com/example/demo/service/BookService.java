package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository; // EntityManagerFactory

    // 1. 전체리스트 가져오기
    public List<Book> getAllList() {
        return bookRepository.findAll();
    }

    // 2. 데이터 등록하기
    public Book register(Book book) {
        return bookRepository.save(book);
    }

    // 3. 특정 데이터 가져오기(PK)
    public Book getById(Long id) {
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isPresent()) { // 값이 존재하는지 체크
            return optional.get();
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    // 4. 특정 데이터 수정하기
    @Transactional // org.springframework.transaction.annotation.Transactional
    public Book update(Long id, Book reqBook) {
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isPresent()) { // 수정하기
            Book book = optional.get(); // 영속성 메모리에 담은 책 데이터(DB테이블과 연결되어 있음)
            book.setTitle(reqBook.getTitle()); // 영속성 메모리에 변경사항이 생김
            book.setPrice(reqBook.getPrice()); // 영속성 메모리에 변경사항이 생김
//            return book; // 더티 체킹에 의한 자동 업데이트. (속도지연이 발생할 수 있다, 구현하기는 쉽다.) - 업데이트쿼리 확인 X
            return bookRepository.save(book); // save() 활용 - 업데이트 쿼리 나가는거 확인 됨
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    // 5. 특정 데이터 삭제하기
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}

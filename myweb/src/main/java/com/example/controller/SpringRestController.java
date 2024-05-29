package com.example.controller;

import com.example.entity.Book;
import com.example.repository.BookMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController // 원격의 리소스를 제공해주는 접속 경로를 rest라고 한다. => 리턴값이 무조건 JSON 형태가 됨
public class SpringRestController {

    @Autowired
    private BookMapper mapper;

    @RequestMapping("/rest")
    // ResponseEntity? 응답 데이터를 통일하기 위해서
    public ResponseEntity<List<String>> rest() { // 컨트롤러가 RestController라면 @ResponseBody 는 생략해도 된다.
        List<String> list = new ArrayList<>();
        list.add("스프링 프레임워크");
        list.add("잘 하면 ㅠㅠ");
        list.add("된다!!!!!");
//        return "rest"; // rest.jsp <-- 뷰를 만들어야 함. JSON 형태로 데이터를 리턴하려면?? -> { key: value }
//        return list; // 현재는 키없는 value의 모음
        return new ResponseEntity<>(list, HttpStatus.OK); // 데이터, 응답 메시지
    }

    @RequestMapping("/rest-list")
    public ResponseEntity<List<Book>> list() {
        List<Book> list = mapper.bookList();
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @PostMapping("rest-save")
    public ResponseEntity<String> saveBook(@RequestBody Book dto) {
        log.info("dto: {}", dto);
        mapper.saveBook(dto);
        return new ResponseEntity<>("success", HttpStatus.NOT_FOUND); // 데이터는 추가되지만 리턴되는 상태값은 404
    }
}

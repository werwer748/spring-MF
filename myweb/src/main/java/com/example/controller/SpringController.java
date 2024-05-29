package com.example.controller;

import com.example.entity.Book;
import com.example.repository.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller// 이 어노테이션이 붙어야 스프링에 등록되어 관리 됨.
public class SpringController {

    @Autowired // 의존성 주입
    private BookMapper mapper;

    @RequestMapping("/spring")
    public String index() {
        return "template"; // template.jsp
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<Book> list = mapper.bookList();
        model.addAttribute("list", list);
        return "list"; // list.jsp
    }

    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }

    @PostMapping("/register")
//    public String registerPost(Book dto) { // 파라메터 수집이 자동으로 된다.(form으로 보냈기 때문에...)
    public String registerPost(@RequestBody Book dto) { // json 데이터를 받을 경우
        mapper.registerBook(dto);
        return "redirect:/list";
    }

    @GetMapping("/get/{num}")
    public String get(@PathVariable long num, Model model) { // path 파라메터가 여러개면 @PathVariable을 여러개 써서 사용하면 된다.
        Book book = mapper.get(num);
        model.addAttribute("book", book);

        return "get";
    }

    @GetMapping("/remove/{num}")
    public String remove(@PathVariable long num) {
        mapper.remove(num);
        return "redirect:/list";
    }

    @GetMapping("/update/{num}")
    public String updateGet(@PathVariable long num, Model model) {
        Book book = mapper.get(num);
        model.addAttribute("book", book);
        return "update";
    }

    @PostMapping("/update")
    public String updatePost(Book dto) { // 파라메터 수집
        mapper.update(dto);
//        return "redirect:/list";
        return "redirect:/get/" + dto.getNum(); // 수정 후 상세보기
    }

    @RequestMapping("/json-list")
    @ResponseBody // json데이터로 응답
    public List<Book> jsonList() {
        List<Book> list = mapper.bookList();

        return list; // [{}, {}, {}]
    }
}

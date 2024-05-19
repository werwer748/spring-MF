package com.example.controller;

import com.example.entity.BookDTO;
import com.example.repository.BookDAO;
import com.example.repository.BookDAOMybatis;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// http://localhost:8081/MF01/edit
@WebServlet("/create")
public class BookCreateController extends HttpServlet {

    // Mybatis 사용 전
//    @Override
//    // doPost? 포스트방식 요청을 처리할때 사용된다. get post 외의 방식은 restful한 서비스에 사용되는거임
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//
//
//        String title = req.getParameter("title");
//        int price = Integer.parseInt(req.getParameter("price"));
//        String author = req.getParameter("author");
//        int page = Integer.parseInt(req.getParameter("page"));
//
//        BookDTO book = new BookDTO();
//        book.setTitle(title);
//        book.setPrice(price);
//        book.setAuthor(author);
//        book.setPage(page);
//
//        BookDAO dao = new BookDAO();
//        dao.createBook(book);
//
//        resp.sendRedirect("list");
//    }

    @Override
    // doPost? 포스트방식 요청을 처리할때 사용된다. get post 외의 방식은 restful한 서비스에 사용되는거임
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");


        String title = req.getParameter("title");
        int price = Integer.parseInt(req.getParameter("price"));
        String author = req.getParameter("author");
        int page = Integer.parseInt(req.getParameter("page"));

        BookDTO book = new BookDTO();
        book.setTitle(title);
        book.setPrice(price);
        book.setAuthor(author);
        book.setPage(page);

        BookDAOMybatis dao = new BookDAOMybatis();
        dao.createBook(book);

        resp.sendRedirect("list");
    }

}

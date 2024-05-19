package com.example.controller;

import com.example.repository.BookDAOMybatis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/delete")
public class BookDeleteControllerSelf extends HttpServlet {

    // Mybatis 사용 전
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int num = Integer.parseInt(req.getParameter("num"));
//        BookDAO dao = new BookDAO();
//        dao.deleteBook(num);
//
//        resp.sendRedirect("list");
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int num = Integer.parseInt(req.getParameter("num"));
        BookDAOMybatis dao = new BookDAOMybatis();
        dao.deleteBook(num);

        resp.sendRedirect("list");
    }
}

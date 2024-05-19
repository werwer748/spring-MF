package com.example.controller;

import com.example.entity.BookDTO;
import com.example.repository.BookDAOMybatis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/view")
public class BookViewController extends HelloServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long num = Long.parseLong(req.getParameter("num"));

        BookDAOMybatis dao = new BookDAOMybatis();
        BookDTO book = dao.bookView(num);

        req.setAttribute("book", book);
        req.getRequestDispatcher("/WEB-INF/views/view.jsp").forward(req, resp);
    }
}

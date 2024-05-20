package com.example.controller;

import com.example.entity.BookDTO;
import com.example.repository.BookDAO;
import com.example.repository.BookDAOMybatis;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// http://localhost:8081/MF01/list
//@WebServlet("/list")
//public class BookListController extends HttpServlet {
public class BookListController implements Controller { // 공통처리 컨트롤러(FrontController)에서 불러쓸 수 있도록 코드 변경

    // Mybatis 사용 전
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        BookDAO dao = new BookDAO();
//        List<BookDTO> list = dao.bookList();
//
//        // 객체 바인딩 기술 (객체를 리퀘스트에 연결시킨다) - list를 뷰로 넘겨줘야 하니까
//        req.setAttribute("list", list); // ${list}
//
//        // view로 데이터를 넘긴다. forward, dispatcher
//        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/list.jsp");
//        rd.forward(req, resp);
//    }

//    @Override
//    protected void requestHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    public String requestHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String search = req.getParameter("search") == null ? "" : req.getParameter("search");

        BookDAOMybatis dao = new BookDAOMybatis();
        List<BookDTO> list = dao.bookList(search);

        // 객체 바인딩 기술 (객체를 리퀘스트에 연결시킨다) - list를 뷰로 넘겨줘야 하니까
        req.setAttribute("list", list); // ${list}

        // view로 데이터를 넘긴다. forward, dispatcher
//        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/list.jsp");
//        rd.forward(req, resp); // 포워딩을 FrontController에서 할 수 있도록 변경 - 뷰의 이름만 리턴해준다.
        return "list"; // /WEB-INF/views/list.jsp
    }

}

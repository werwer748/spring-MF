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
import java.util.List;

// http://localhost:8081/MF01/list
@WebServlet("/detail")
public class BookDetailController extends HttpServlet {

    // Mybatis 적용 전
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String numStr = req.getParameter("num");
//        int num = 0;
//
//        if (numStr != null && !numStr.isEmpty()) {
//            try {
//                num = Integer.parseInt(numStr);
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//                // 유효하지 않은 num 파라미터 처리 (예: 에러 페이지로 리다이렉트)
//                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid book number.");
//                return;
//            }
//        } else {
//            // num 파라미터가 없는 경우 처리 (예: 에러 페이지로 리다이렉트)
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Book number is required.");
//            return;
//        }
//
//        // DAO를 통해 책의 상세 정보를 가져옵니다.
//        BookDAO dao = new BookDAO();
//        BookDTO book = dao.getBook(num);
//
//        if (book == null) {
//            // 책을 찾지 못한 경우 처리 (예: 에러 페이지로 리다이렉트)
//            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found.");
//            return;
//        }
//
//        // 객체 바인딩 기술 (객체를 리퀘스트에 연결시킵니다) - book을 뷰로 넘겨줍니다.
//        req.setAttribute("book", book); // ${book}
//
//        // view로 데이터를 넘깁니다. forward, dispatcher
//        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/detail.jsp");
//        rd.forward(req, resp);
//    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String numStr = req.getParameter("num");
        int num = 0;

        if (numStr != null && !numStr.isEmpty()) {
            try {
                num = Integer.parseInt(numStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // 유효하지 않은 num 파라미터 처리 (예: 에러 페이지로 리다이렉트)
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid book number.");
                return;
            }
        } else {
            // num 파라미터가 없는 경우 처리 (예: 에러 페이지로 리다이렉트)
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Book number is required.");
            return;
        }

        // DAO를 통해 책의 상세 정보를 가져옵니다.
        BookDAOMybatis dao = new BookDAOMybatis();
        BookDTO book = dao.getBook(num);

        if (book == null) {
            // 책을 찾지 못한 경우 처리 (예: 에러 페이지로 리다이렉트)
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found.");
            return;
        }

        // 객체 바인딩 기술 (객체를 리퀘스트에 연결시킵니다) - book을 뷰로 넘겨줍니다.
        req.setAttribute("book", book); // ${book}

        // view로 데이터를 넘깁니다. forward, dispatcher
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/detail.jsp");
        rd.forward(req, resp);
    }

}

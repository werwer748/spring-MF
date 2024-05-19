package com.example.controller;

import com.example.repository.BookDAO;
import com.example.repository.BookDAOMybatis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 초기 MVC 패턴은 컨트롤러가 요청과 1:1로 매핑되기 떄문에 관리해야할 파일이 많아진다.

// http://localhost:8081/MF01/delete?num=(queryString)
@WebServlet("/delete")
public class BookDeleteController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int num = Integer.parseInt(req.getParameter("num")); // 쿼리로 넘어온 스트링을 int로 바꾼다.
        BookDAOMybatis dao = new BookDAOMybatis();
        int cnt = dao.bookDelete(num);

        if (cnt > 0) {
            // 다시 리스트보기 페이지로 이동(redirect)
            resp.sendRedirect("/MF01/list");
        } else {
            System.out.println("삭제실패");
        }
    }
}

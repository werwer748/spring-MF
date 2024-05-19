package com.example.controller;

import com.example.entity.BookDTO;
import com.example.repository.BookDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// http://localhost:8081/MF01/edit
@WebServlet("/edit")
public class BookEditController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String numStr = req.getParameter("num");
        if (numStr == null || numStr.isEmpty()) {
            resp.sendRedirect("list");
            return;
        }

        int num = Integer.parseInt(numStr);

        BookDAO dao = new BookDAO();
        BookDTO book = dao.getBook(num);

        if (book == null) {
            resp.sendRedirect("list");
            return;
        }

        // 객체 바인딩 기술 (객체를 리퀘스트에 연결시킵니다) - book을 뷰로 넘겨줍니다.
        req.setAttribute("book", book); // ${book}

        // view로 데이터를 넘깁니다. forward, dispatcher
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/edit.jsp");
        rd.forward(req, resp);
    }

}

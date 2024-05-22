package com.example.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// http://localhost:8081/MF01/register-get
//@WebServlet("/register-get")
public class BookRegisterGetController implements Controller {

    @Override
    public String requestHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 로그인 체크
        HttpSession session = req.getSession(false); // false를 통해서 세션을 새로생성하지 않고 사용중인 세션을 가져오도록 한다.
        if (session.getAttribute("cus") == null) {
            return "redirect:/list.do";
        }

//        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/register.jsp");
//        rd.forward(req, resp);
        return "register";
    }

}

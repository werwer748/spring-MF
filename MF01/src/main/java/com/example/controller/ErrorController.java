package com.example.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("/error")
public class ErrorController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String msgCode = req.getParameter("msg");
        String messageKey = "error." + msgCode;

        //? ResourceBundle: properties파일을 읽어올 때 사용한다.
        // req.getLocale() 이 resource를 카리킨다. => message이름을 가진 프로퍼티 파일을 읽어옴
        ResourceBundle messages = ResourceBundle.getBundle("message", req.getLocale());
        String errorMessage = messages.getString(messageKey);
        System.out.println("errorMessage = " + errorMessage);

        req.setAttribute("errorMessage", errorMessage);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/error.jsp");
        rd.forward(req, resp);
    }
}

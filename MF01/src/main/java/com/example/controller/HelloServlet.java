package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// http://localhost:8081/MF01/hello
@WebServlet("/hello") // servlet 맵핑 - web.xml에서 세팅한경우 필요없음(같이쓰면 에러)
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter(); // 출력 스트림을 만듬
        out.println("Hello Servlet"); // 원래 서블렛은 컨트롤러 목적이기 때문에 뷰를 던질 필요는 없음
        //? jsp로 뷰를 그리고 서블렛과 연결하여 화면을 띄우는데 이걸 포워딩이라고 한다고 함.
    }

}

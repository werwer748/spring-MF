package com.example.controller;

import com.example.entity.CustomerDTO;
import com.example.repository.LoginDAOMybatis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutController implements Controller {
    @Override
    public String requestHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션연결을 무효화 시켜야 한다.
        HttpSession session = req.getSession();
        session.invalidate(); // 세션 무효화! == 로그아웃
        return "redirect:/list.do";
    }
}

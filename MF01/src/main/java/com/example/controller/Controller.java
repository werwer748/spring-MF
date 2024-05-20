package com.example.controller;

import com.example.entity.BookDTO;
import com.example.repository.BookDAOMybatis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// 컨트롤러의 형식을 맞추기 위해 만들어서 사용
public interface Controller {
    public String requestHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}

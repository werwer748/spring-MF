package com.example.repository;

import com.example.entity.BookDTO;
import com.example.entity.CustomerDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class LoginDAOMybatis {
    // ==== 로그인 관련 ==== //
    public CustomerDTO login(CustomerDTO dto) {
        try (SqlSession session = MybatisUtil.openSession()) {
            return session.selectOne("com.example.repository.LoginDAOMybatis.login", dto);
        }
    }
}

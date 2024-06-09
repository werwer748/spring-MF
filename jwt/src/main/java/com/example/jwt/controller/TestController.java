package com.example.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // 로그인 요청을 처리하고, JWT 토큰을 생성하여 반환
        return ResponseEntity.ok("로그인 성공");
    }
}

class LoginRequest {
    private String username;
    private String password;

    // getters and setters
}
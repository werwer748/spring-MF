package com.example.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @GetMapping("/api/v1/user")
    public String user() {
        return "customer role is user";
    }

    @GetMapping("/api/v1/manager")
    public String manager() {
        return "customer role is manager";
    }

    @GetMapping("/api/v1/admin")
    public String admin() {
        return "customer role is admin";
    }
}

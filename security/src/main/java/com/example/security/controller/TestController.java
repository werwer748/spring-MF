package com.example.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class TestController {

    @ResponseBody
    @GetMapping("/info")
//    @Secured("ROLE_MANAGER")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    public String info () {
        return "개인정보";
    }

    @ResponseBody
    @GetMapping("/admin")
    @Secured("ROLE_ADMIN") //? Secured: 하나의 권한에만 적용할 때
    public String admin () {
        return "관리자 정보";
    }
}

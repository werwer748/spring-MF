package com.example.security.controller;

import com.example.security.entity.Customer;
import com.example.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("member-register")
    public String memberRegisterGet(){
        return "member/register";
    }

    @PostMapping("member-register")
    public String memberRegisterPost(Customer customer){
        memberService.memberRegister(customer);
        return "redirect:/"; // 시작페이지(index.html)
    }
}

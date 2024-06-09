package com.example.jwt.controller;

import com.example.jwt.entity.Customer;
import com.example.jwt.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;

    @PostMapping("/join")
    public String join(@RequestBody Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRoles("USER"); // ROLE_USER
        customerService.register(customer);
        return "success";
    }
}

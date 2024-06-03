package com.example.jpa.controller;

import com.example.jpa.entity.Customer;
import com.example.jpa.entity.Review;
import com.example.jpa.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public String showCustomerList(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customerList";
    }

    @GetMapping("/customer/reviews")
    public String getReviews(@RequestParam("id") Long id, Model model) {
        log.info("id = " + id);
        List<Review> reviews = customerService.getReviewsByCustomerId(id);
        model.addAttribute("reviews", reviews);
        return "reviewList";
    }
}

package com.example.jpa.controller;

import com.example.jpa.controller.advice.ApiResponse;
import com.example.jpa.controller.advice.ApiResponseException;
import com.example.jpa.dto.CustomerDto;
import com.example.jpa.dto.ICustomerDto;
import com.example.jpa.entity.Customer;
import com.example.jpa.repository.CustomerQueryDSLRepository;
import com.example.jpa.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class CustomerRestController {

    private final CustomerRepository customerRepository;
    private final CustomerQueryDSLRepository customerQueryDSLRepository;

//    @GetMapping("/customer/{age}")
    public ResponseEntity<?> getByAge(@PathVariable int age) {
//        Customer cus = customerRepository.findByAge(age);
        List<Customer> cus = customerRepository.findByAge(age);
        return ResponseEntity.ok(cus);
    }

    @PostMapping("/customer")
    public ApiResponse getUserIdAndPassword(@RequestBody Customer customer) {
        Customer cus = customerRepository.findByUserIdAndPassword(customer.getUserId(), customer.getPassword());
        if (cus == null) {
            return ApiResponse.error(HttpStatus.NOT_FOUND, "Not Fount User");
        }
        return ApiResponse.success(cus);
    }

    @PostMapping("/customerV2")
    public ApiResponse getUserIdAndPassword2(@RequestBody Customer customer) {
        Customer cus = customerRepository.getUserIdAndPassword(customer.getUserId(), customer.getPassword());
        if (cus == null) {
//            return ApiResponse.error(HttpStatus.NOT_FOUND, "Not Fount User");
            throw new ApiResponseException(HttpStatus.NOT_FOUND, "Not Found User");
        }
        return ApiResponse.success(cus);
    }

    @PostMapping("/customerV3")
    public ApiResponse getUserIdAndPasswordQueryDsl(@RequestBody Customer customer) {
        Customer cus = customerQueryDSLRepository.getUserIdAndPassword(customer.getUserId(), customer.getPassword());
        if (cus == null) {
            throw new ApiResponseException(HttpStatus.NOT_FOUND, "Not Found User");
        }
        return ApiResponse.success(cus);
    }

    @GetMapping("/customer/user/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable String userId) {
        Customer user = customerRepository.findByUserIdIs(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/customer/{age}")
    public ApiResponse findByAgeGreaterThanEqual(@PathVariable int age) {
        List<Customer> list = customerRepository.findByAgeGreaterThanEqual(age);
        return ApiResponse.success(list);
    }

    @GetMapping("/customer/rating/{rating}")
    public ApiResponse getRating(@PathVariable String rating) {
        List<Customer> list = customerRepository.getRating(rating);
        return ApiResponse.success(list);
    }

    @GetMapping("/customer/ratingV2/{rating}")
    public ApiResponse getRatingQueryDsl(@PathVariable String rating) {
        List<Customer> list = customerQueryDSLRepository.getRating(rating);
        return ApiResponse.success(list);
    }

    @GetMapping("/customers")
    public ApiResponse customerList(
            @PageableDefault(size=5, sort="id", direction= Sort.Direction.ASC) Pageable pageable
    ) {
        Page<CustomerDto> customers = customerRepository.findPage(pageable);
        return ApiResponse.success(customers);
    }

    @GetMapping("/customersV2")
    public ApiResponse customerList2(
            @PageableDefault(size=5, sort="id", direction= Sort.Direction.DESC) Pageable pageable
    ) {
        Page<ICustomerDto> customers = customerRepository.findAllBy(pageable);
        return ApiResponse.success(customers);
    }
}

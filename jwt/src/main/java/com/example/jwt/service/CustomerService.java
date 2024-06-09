package com.example.jwt.service;

import com.example.jwt.entity.Customer;
import com.example.jwt.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer findByUserId(String userId) {
        return customerRepository.findByUserId(userId);
    }

    @Transactional
    public void register(Customer customer) {
        customerRepository.save(customer);
    }
}

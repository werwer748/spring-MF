package com.example.jpa.service;

import com.example.jpa.entity.Customer;
import com.example.jpa.entity.Review;
import com.example.jpa.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        // 고객 전체 리스트 가져오기
        /*
        * DB에서 고객 정보를 가져올때 리뷰를 JOIN을 걸어서 함께 가져온다. EAGER
        * DB에서 고객 정보를 가져올때 리뷰값을 필요로 할 때 따로 호출한다. LAZY
        */
        List<Customer> customers = customerRepository.findAll();
        // fetch 전략 확인용
//        for (Customer customer : customers) {
//            // LAZY로딩일 경우 리뷰를 불러오는 때가 SQL 쿼리 트리거가되어 이벤트가 발생한다.
//            System.out.println(customer.getReviews().size());
//        }
        return customers;
    }

    public List<Review> getReviewsByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        log.info("Get reviews by customer id {}", customer.getId());

        return customer.getReviews(); // 페치전략에 따라 다르게 동작.
    }
}

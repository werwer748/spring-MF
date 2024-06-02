package com.example.controller;

import com.example.entity.Product;
import com.example.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TemplateController {

    @Autowired
    private TemplateService service;

    @RequestMapping("/rest")
    public ResponseEntity<?> test(){
        return new ResponseEntity<>("Restful API Test", HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<?> products() {
        List<Product> list = service.products();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/products")
    // 메서드명을 products로 해도 매개변수가 달라져서 오버로드 기능을 사용 가능하기때문에 원하면 메서드명을 똑같이 써도 된다.(어차피 요청 메소드도 다르니까)
    public ResponseEntity<?> register(@RequestBody Product product) {
        service.register(product);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/products/{product_number}")
    public ResponseEntity<?> deleteById(@PathVariable int product_number) {
        int cnt = service.deleteById(product_number);
        return new ResponseEntity<>(cnt, HttpStatus.OK);
    }

    @GetMapping("/products/{product_number}")
    public ResponseEntity<?> getById(@PathVariable int product_number) {
        Product product = service.getById(product_number);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // PUT요청과 POST요청은 요청 주소가 겹칠경우 에러
    // PUT product_name, inventory, price
    @PutMapping("/products/{product_number}")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable int product_number) {
        service.update(product);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}

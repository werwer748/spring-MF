package com.example.backend.controller;

import com.example.backend.entity.Product;
import com.example.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<?> test() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

//    @CrossOrigin
    @PostMapping("/product")
    public ResponseEntity<?> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.productSave(product), HttpStatus.CREATED);
    }

//    @CrossOrigin
    @GetMapping("/product")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(productService.productGetAll(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.productGetOne(id), HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.productDelete(id), HttpStatus.OK);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {
        return new ResponseEntity<>(productService.productUpdate(id, product), HttpStatus.OK);
    }
}

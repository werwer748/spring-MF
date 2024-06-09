package com.example.backend.service;

import com.example.backend.entity.Product;
import com.example.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product productSave(Product product) {
        return productRepository.save(product); // 저장
    }

    public List<Product> productGetAll(){
        return productRepository.findAll(); // 전체 리스트
    }

    public Product productGetOne(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id를 확인해 주세요."));
    }

    @Transactional
    public String productDelete(Long id) {
        productRepository.deleteById(id);
        return "ok";
    }

    @Transactional
    public Product productUpdate(Long id, Product product) {
        Product dbProduct
                = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id를 확인해 주세요."));

        dbProduct.setProductName(product.getProductName());
        dbProduct.setProductCompany(product.getProductCompany());

        return dbProduct;
    }
}

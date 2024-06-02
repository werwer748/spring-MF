package com.example.repository;

import com.example.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateMapper {

    public List<Product> products();

    public void register(Product product);

    public int deleteById(int product_number);

    public Product getById(int product_number);

    public void update(Product product);
}


package com.spring.shop.service;

import com.spring.shop.domain.Product;

import java.util.Set;

public interface ProductService {
    Set<Product> findAllProducts();
    Set<Product> findAllProducts(String category);
    void increasePrice(Product product);
}
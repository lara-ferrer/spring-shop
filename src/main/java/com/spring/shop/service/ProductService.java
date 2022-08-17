package com.spring.shop.service;

import com.spring.shop.domain.Product;
import com.spring.shop.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();
    List<Product> findAllProducts(String category);
    Product findProduct(long id) throws ProductNotFoundException;
    List<Product> findProductsByName(String name);
}
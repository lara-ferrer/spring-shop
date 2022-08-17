package com.spring.shop.service;

import com.spring.shop.domain.Category;
import com.spring.shop.domain.Product;
import com.spring.shop.exception.ProductNotFoundException;
import com.spring.shop.repository.CategoryRepository;
import com.spring.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProduct(long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Product> findAllProducts(String category) {
        return null;
    }

    @Override
    public List<Product> findProductsByName(String name) {
        return productRepository.findByNameContains(name);
    }

    @Override
    public List<Product> findProductsByCategory(long categoryId) {
        Category category = categoryRepository.findByCategoryId(categoryId);
        return productRepository.findByCategory(category);
    }
}
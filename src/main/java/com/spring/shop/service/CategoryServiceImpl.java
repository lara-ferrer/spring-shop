package com.spring.shop.service;

import com.spring.shop.domain.Category;
import com.spring.shop.domain.Product;
import com.spring.shop.exception.ProductNotFoundException;
import com.spring.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByCategoryId(long categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }
}
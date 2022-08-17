package com.spring.shop.service;

import com.spring.shop.domain.Category;
import com.spring.shop.exception.ProductNotFoundException;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();
    Category findByCategoryId(long categoryId);
}
package com.spring.shop.repository;

import com.spring.shop.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findByNameContains(String name);
    List<Product> findByNameAndPrice(String name, float price);
}
package com.spring.shop.repository;

import com.spring.shop.domain.Order;
import com.spring.shop.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();
    List<Order> findByUser(User user);
}
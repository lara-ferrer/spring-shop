package com.spring.shop.service;

import com.spring.shop.domain.Order;
import com.spring.shop.domain.Product;
import com.spring.shop.domain.User;
import com.spring.shop.exception.NewOrderException;

import java.util.List;

public interface OrderService {
    boolean addOrder(int quantity, String shippingAddress, boolean expressShipping, Product product, User user) throws NewOrderException;
    List<Order> findOrders(User user);
    Order findOrder(long orderId);
}
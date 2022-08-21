package com.spring.shop.service;

import com.spring.shop.domain.Order;
import com.spring.shop.domain.Product;
import com.spring.shop.domain.User;
import com.spring.shop.exception.NewOrderException;
import com.spring.shop.repository.OrderRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public boolean addOrder(int quantity, String shippingAddress, boolean expressShipping, Product product, User user) throws NewOrderException {
        Order order = new Order();

        order.setQuantity(quantity);
        order.setShippingAddress(shippingAddress);
        order.setExpressShipping(expressShipping);
        order.setProduct(product);
        order.setUser(user);
        order.setDate(LocalDate.now());
        orderRepository.save(order);

        return true;
    }

    @Override
    public List<Order> findOrders(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public Order findOrder(long orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    public boolean removeOrder(long orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        orderRepository.delete(order);

        return true;
    }
}

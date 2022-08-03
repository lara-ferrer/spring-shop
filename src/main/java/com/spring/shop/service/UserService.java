package com.spring.shop.service;

import com.spring.shop.domain.User;

import java.util.Set;

public interface UserService {
    boolean add(User user);
    void remove(User user);
    Set<User> findAll();
    User findByUsername(String username);
    Set<User> findByCity(String city);
}

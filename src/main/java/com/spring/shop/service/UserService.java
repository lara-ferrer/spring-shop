package com.spring.shop.service;

import com.spring.shop.domain.User;
import com.spring.shop.exception.UserNotFoundException;

import java.util.Set;

public interface UserService {
    boolean add(User user) throws UserNotFoundException;
    boolean update(User user) throws UserNotFoundException;
    void remove(User user);
    Set<User> findAll();
    User findByUsername(String username);
    Set<User> findByCity(String city);
}

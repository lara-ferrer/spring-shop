package com.spring.shop.service;

import com.spring.shop.domain.Role;
import com.spring.shop.domain.User;
import com.spring.shop.exception.UserNotFoundException;
import com.spring.shop.repository.RoleRepository;
import com.spring.shop.repository.UserRepository;
import com.spring.shop.security.Constants;
import com.spring.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private boolean isUsernameRegistered(String username) {
        var isUsernameRegistered = userRepository.findByUsername(username);

        if (isUsernameRegistered != null) {
            return true;
        }

        return false;
    }

    private boolean isEmailRegistered(String email) {
        var isEmailRegistered = userRepository.findByEmail(email);

        if (isEmailRegistered != null) {
            return true;
        }

        return false;
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean add(User user) throws UserNotFoundException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        var isUsernameRegistered = isUsernameRegistered(user.getUsername());
        var isEmailRegistered = isEmailRegistered(user.getEmail());

        if (isUsernameRegistered) {
            throw new UserNotFoundException("El nombre de usuario ya existe. Por favor escoge otro.");
        }

        if (isEmailRegistered) {
            throw new UserNotFoundException("El email ya existe. Por favor escoge otro.");
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreationDate(LocalDate.now());
        user.setActive(true);
        Role userRole = roleRepository.findByName(Constants.USER_ROLE);
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);

        return true;

    }

    @Override
    public boolean update(User user) throws UserNotFoundException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User newUser = userRepository.findByEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.setName(user.getName());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setAddress(user.getAddress());
        newUser.setCity(user.getCity());

        userRepository.save(newUser);

        return true;
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }

    @Override
    public Set<User> findAll() {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Set<User> findByCity(String city) {
        return null;
    }
}

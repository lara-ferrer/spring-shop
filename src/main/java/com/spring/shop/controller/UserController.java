package com.spring.shop.controller;

import com.spring.shop.domain.User;
import com.spring.shop.exception.ProductNotFoundException;
import com.spring.shop.exception.UserNotFoundException;
import com.spring.shop.exception.UserRegistrationException;
import com.spring.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/registro")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "registro";
    }

    @GetMapping("/perfil-usuario")
    public String profile(Model model, HttpServletRequest request) {
        String remoteUsername = request.getRemoteUser();
        User remoteUser = userService.findByUsername(remoteUsername);
        model.addAttribute("user", remoteUser);
        return "perfil-usuario";
    }

    @PostMapping("/registro-completado")
    public String addUser(@ModelAttribute User user, Model model) throws UserRegistrationException, UserNotFoundException {
        boolean userAdded = userService.add(user);
        if (!userAdded)
            throw new UserRegistrationException("Error al registrar el usuario");

        logger.info("Usuario creado: " + user);
        model.addAttribute("user", user);
        return "registro-completado";
    }

    @PostMapping("/usuario-actualizado")
    public String updateUserInfo(@ModelAttribute User user, Model model) throws UserNotFoundException {
        boolean updateUser = userService.update(user);
        if (!updateUser)
            throw new UserNotFoundException("Error al actualizar la información del usuario. Por favor, vuelve a intentarlo.");

        logger.info("Usuario actualizado: " + user);
        model.addAttribute("user", user);
        return "usuario-actualizado";
    }

    @ExceptionHandler(UserRegistrationException.class)
    public ModelAndView handleUserRegistrationException(HttpServletRequest request, UserRegistrationException exception) {
        logger.error("Error: " + exception.getMessage(), exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "No se ha podido registrar el usuario. Por favor contacte con soporte técnico");
        mav.addObject("exception", exception);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserValidationException(HttpServletRequest request, UserNotFoundException exception) {
        return "error-registro";
    }

    @ExceptionHandler
    public ModelAndView handleException(HttpServletRequest request, Exception exception) {
        logger.error("Error: " + exception.getMessage(), exception);

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", exception.getMessage());
        mav.addObject("exception", exception);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}
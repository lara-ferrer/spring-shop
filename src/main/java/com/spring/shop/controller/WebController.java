package com.spring.shop.controller;

import com.spring.shop.domain.Product;
import com.spring.shop.exception.ProductNotFoundException;
import com.spring.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping(value = "/tienda")
    public String shop(Model model) {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "tienda";
    }

    @RequestMapping(value = "/productos/{id}")
    public String product(Model model, @PathVariable long id) throws ProductNotFoundException {
        Product product = productService.findProduct(id);
        model.addAttribute("product", product);
        return "product";
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public String handleException(HttpServletRequest request, ProductNotFoundException exception) {
        return "product_error";
    }

    @ExceptionHandler
    public String handleException(HttpServletRequest request, Exception exception) {
        return "error";
    }
}
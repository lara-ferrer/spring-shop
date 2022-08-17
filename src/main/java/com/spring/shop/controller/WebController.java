package com.spring.shop.controller;

import com.spring.shop.domain.Category;
import com.spring.shop.domain.Product;
import com.spring.shop.exception.ProductNotFoundException;
import com.spring.shop.service.CategoryService;
import com.spring.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping(value = "/tienda")
    public String shop(Model model) {
        List<Product> products = productService.findAllProducts();
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "tienda";
    }

    @RequestMapping(value = "/tienda/search")
    public String findProductsByName(@RequestParam String product, Model model) {
        List<Category> categories = categoryService.findAllCategories();
        List<Product> products = productService.findProductsByName(product);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "tienda";
    }

    @RequestMapping(value = "/tienda/filter")
    public String findProductsByCategory(@RequestParam long categoryId, Model model) {
        List<Product> products = productService.findProductsByCategory(categoryId);
        List<Category> categories = categoryService.findAllCategories();
        Category filteredCategory = categoryService.findByCategoryId(categoryId);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("filteredCategory", filteredCategory);
        return "tienda";
    }

    @RequestMapping(value = "/productos/{id}")
    public String product(Model model, @PathVariable long id) throws ProductNotFoundException {
        Product product = productService.findProduct(id);
        long categoryId = product.getCategory().getCategoryId();
        Category productCategory = categoryService.findByCategoryId(categoryId);
        model.addAttribute("product", product);
        model.addAttribute("productCategory", productCategory);
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
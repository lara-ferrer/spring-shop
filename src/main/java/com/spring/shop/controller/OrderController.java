package com.spring.shop.controller;

import com.spring.shop.domain.Order;
import com.spring.shop.domain.Product;
import com.spring.shop.domain.User;
import com.spring.shop.dto.CancelOrderDTO;
import com.spring.shop.dto.OrderDTO;
import com.spring.shop.exception.NewOrderException;
import com.spring.shop.exception.ProductNotFoundException;
import com.spring.shop.service.ProductService;
import com.spring.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.spring.shop.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping("/order")
    public String order(@ModelAttribute OrderDTO orderDTO, HttpServletRequest request) throws NewOrderException, ProductNotFoundException {
        int quantity = orderDTO.getQuantity();
        String shippingAddress = orderDTO.getShippingAddress();
        boolean expressShipping = orderDTO.isExpressShipping();

        String remoteUsername = request.getRemoteUser();
        User user = userService.findByUsername(remoteUsername);

        Product product = productService.findProduct(orderDTO.getProductId());

        boolean orderAdded = orderService.addOrder(quantity, shippingAddress, expressShipping, product, user);
        if (!orderAdded)
            throw new NewOrderException("Error al registrar el pedido.");

        return "pedido-completado";
    }

    @RequestMapping(value = "/pedidos/{id}")
    public String product(Model model, @PathVariable long id) throws ProductNotFoundException {
        Order order = orderService.findOrder(id);
        Product product = order.getProduct();

        LocalDate today = LocalDate.now();
        boolean hasDeadlineExpired = false;

        if (DAYS.between(today, order.getDate()) > 0) {
            hasDeadlineExpired = true;
        }

        model.addAttribute("order", order);
        model.addAttribute("product", product);
        model.addAttribute("hasDeadlineExpired", hasDeadlineExpired);
        return "order";
    }

    @PostMapping(value = "/cancelar-pedido")
    public String removeOrder(@ModelAttribute CancelOrderDTO cancelOrderDTO) throws NewOrderException {
        boolean orderRemoved = orderService.removeOrder(cancelOrderDTO.getOrderId());
        if (!orderRemoved)
            throw new NewOrderException("Error al eliminar el pedido.");

        return "pedido-cancelado";
    }
}

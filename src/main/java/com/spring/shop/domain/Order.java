package com.spring.shop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    @Column
    private LocalDate date;
    @Column
    private int quantity;
    @Column
    private String shippingAddress;
    @Column
    private boolean expressShipping;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
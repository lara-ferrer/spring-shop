package com.spring.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private long productId;
    private int quantity;
    private String shippingAddress;
    private boolean expressShipping;
}

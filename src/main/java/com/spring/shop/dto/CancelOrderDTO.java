package com.spring.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrderDTO {
    private long orderId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}

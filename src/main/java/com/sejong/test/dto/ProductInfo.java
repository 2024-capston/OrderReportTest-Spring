package com.sejong.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {
    private Long productId;
    private String productName;
    private BigDecimal price;
    private Long quantity;
    private BigDecimal totalAmount;

}

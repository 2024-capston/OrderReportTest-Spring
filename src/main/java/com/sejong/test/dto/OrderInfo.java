package com.sejong.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    private Long orderId;
    private LocalDateTime createdAt;
    private String status;
    private List<ProductInfo> products;
}

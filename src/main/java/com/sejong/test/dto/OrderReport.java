package com.sejong.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderReport {
    private Long userId;
    private String username;
    private String email;
    private List<OrderInfo> orders;
    private List<ShipmentInfo> shipments;
}

package com.sejong.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentInfo {
    private Long shipmentId;
    private LocalDateTime createdAt;
    private String shipmentStatus;
}

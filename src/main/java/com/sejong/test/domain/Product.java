package com.sejong.test.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String productName;

    @Column()
    private BigDecimal price;

    @Column(precision = 2, scale = 1) //소수점 한 자리 표현
    private BigDecimal rating;

    @Column()
    private Boolean isDiscount;

    @Column(length = 1)
    private String category;
}

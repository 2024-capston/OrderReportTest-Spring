package com.sejong.test.controller;

import com.sejong.test.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MockController {
    private final OrderDetailMockService orderDetailMockService;
    private final OrderMockService orderMockService;
    private final ProductMockService productMockService;
    private final UserMockService userMockService;
    private final ShipmentMockService shipmentMockService;
    private final ProductSearchMockService productSearchMockService;

    @GetMapping("/createUser")
    public boolean createUser() {
        userMockService.createMockUser();
        return true;
    }

    @GetMapping("/createProduct")
    public boolean createProduct() {
        productMockService.createMockProduct();
        return true;
    }

    @GetMapping("/createOrder")
    public boolean createOrderDetail() {
        orderMockService.createMockOrder();
        return true;
    }

    @GetMapping("/createOrderDetail")
    public boolean createOrder() {
        orderDetailMockService.createMockOrderDetails();
        return true;
    }

    @GetMapping("/createShipment")
    public boolean createShipment() {
        shipmentMockService.createMockShipment();
        return true;
    }

    @GetMapping("/createProductSearchHistory")
    public boolean createProductSearchHistory(){
        productSearchMockService.createMockProductSearchHistory();
        return true;
    }
}

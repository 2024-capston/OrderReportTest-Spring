package com.sejong.test.controller;

import com.sejong.test.dto.OrderReport;
import com.sejong.test.services.OrderReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderReportController {
    private final OrderReportService orderReportService;

    @GetMapping("/order-report/no-query-dsl/{id}")
    public OrderReport getOrderReportNoQueryDsl(@PathVariable Long id) {
        return orderReportService.getOrderReportNoQueryDsl(id);
    }

    @GetMapping("/order-report/query-dsl/{id}")
    public OrderReport getOrderReportQueryDsl(@PathVariable Long id) {
        return orderReportService.getOrderReportQueryDsl(id);
    }
}

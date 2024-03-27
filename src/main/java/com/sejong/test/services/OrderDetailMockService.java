package com.sejong.test.services;

import com.sejong.test.domain.OrderDetail;
import com.sejong.test.domain.Orders;
import com.sejong.test.domain.Product;
import com.sejong.test.repository.OrderDetailRepository;
import com.sejong.test.utils.RandomCreateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailMockService implements MockDataForeignService<OrderDetail> {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderMockService orderMockService;
    private final ProductMockService productMockService;

    @Override
    public OrderDetail create(Object... params) {
        if(params[0] instanceof Orders orders && params[1] instanceof Product product) {
            return OrderDetail.builder()
                    .orders(orders)
                    .product(product)
                    .quantity(Long.valueOf(RandomCreateUtil.createRandomNumber(100, 1)))
                    .build();
        }
        throw new IllegalArgumentException("params type error paramse[0] = Orders, params[1] = Product");
    }

    @Override
    public void saveAll(List<OrderDetail> entities) {
        orderDetailRepository.saveAll(entities);
    }

    public void createMockOrderDetails() {
        List<Orders> orders = orderMockService.findAll();
        List<Product> products = productMockService.findAll();
        List<OrderDetail> orderDetails = new ArrayList<>();
        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                Orders order = orders.get(i);
                Product product = products.get(j);
                OrderDetail orderDetail = create(order, product);
                orderDetails.add(orderDetail);

            }
        }
        saveAll(orderDetails);
    }
}

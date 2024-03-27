package com.sejong.test.services;

import com.sejong.test.domain.Orders;
import com.sejong.test.domain.User;
import com.sejong.test.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderMockService implements MockDataForeignService<Orders> {
    private final UserMockService userMockService;
    private final OrderRepository orderRepository;

    @Override
    public Orders create(Object... params) {
        Object param = params[0];
        if(param instanceof User user) {
            return Orders.builder()
                    .user(user)
                    .status("ORDERED")
                    .build();
        }
        throw new IllegalArgumentException("User 타입이 아닙니다.");
    }

    @Override
    public void saveAll(List<Orders> entities) {
        orderRepository.saveAll(entities);
    }

    public void createMockOrder() {
        List<User> users = userMockService.findAll();
        List<Orders> orders = new ArrayList<>();
        for (User user : users) {
            Orders order = create(user);
            orders.add(order);
        }

        saveAll(orders);
    }

    public List<Orders> findAll() {
        return orderRepository.findAll();
    }
}

package com.sejong.test.repository;

import com.sejong.test.domain.Orders;
import com.sejong.test.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUser(User user);
}

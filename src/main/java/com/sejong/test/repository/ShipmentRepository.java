package com.sejong.test.repository;

import com.sejong.test.domain.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Shipment findByOrdersId(Long orderId);
}

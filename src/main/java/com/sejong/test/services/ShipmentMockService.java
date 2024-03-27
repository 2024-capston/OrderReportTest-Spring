package com.sejong.test.services;

import com.sejong.test.domain.Orders;
import com.sejong.test.domain.Shipment;
import com.sejong.test.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ShipmentMockService implements MockDataForeignService<Shipment> {
    private final ShipmentRepository shipmentRepository;
    private final OrderMockService orderMockService;

    @Override
    public Shipment create(Object... params) {
        if(params[0] instanceof Orders orders) {
            return Shipment.builder()
                    .orders(orders)
                    .shipmentStatus("IN_TRANSIT")
                    .build();
        }
        throw new IllegalArgumentException("params type error paramse[0] = Orders");
    }

    @Override
    public void saveAll(List<Shipment> entities) {
        shipmentRepository.saveAll(entities);
    }

    public void createMockShipment() {
        List<Orders> orders = orderMockService.findAll();
        List<Shipment> shipments = new ArrayList<>();
        for (Orders order : orders) {
            Shipment shipment = create(order);
            shipments.add(shipment);
        }
        saveAll(shipments);
    }
}

package com.sejong.test.services;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sejong.test.domain.*;
import com.sejong.test.dto.OrderInfo;
import com.sejong.test.dto.OrderReport;
import com.sejong.test.dto.ProductInfo;
import com.sejong.test.dto.ShipmentInfo;
import com.sejong.test.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Repository
public class OrderReportService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ShipmentRepository shipmentRepository;
    private final JPAQueryFactory queryFactory;

    public OrderReport getOrderReportNoQueryDsl(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Orders> orders = orderRepository.findByUser(user);
        List<OrderInfo> orderInfos = new ArrayList<>();
        for (Orders order : orders) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderId(order.getId());
            orderInfo.setCreatedAt(order.getCreatedAt());
            orderInfo.setStatus(order.getStatus());

            // OrderDetail과 Product 정보 조회 및 치환
            List<ProductInfo> productInfos = new ArrayList<>();
            List<OrderDetail> orderDetails = orderDetailRepository.findByOrdersId(order.getId());
            for (OrderDetail orderDetail : orderDetails) {
                Product product = productRepository.findById(orderDetail.getProduct().getId()).orElseThrow();
                ProductInfo productInfo = new ProductInfo();
                productInfo.setProductId(product.getId());
                productInfo.setProductName(product.getProductName());
                productInfo.setPrice(product.getPrice());
                productInfo.setQuantity(orderDetail.getQuantity());
                productInfo.setTotalAmount(product.getPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity())));
                productInfos.add(productInfo);
            }
            orderInfo.setProducts(productInfos);
            orderInfos.add(orderInfo);
        }

        // Shipment 정보 조회 및 치환
        List<ShipmentInfo> shipmentInfos = new ArrayList<>();
        for (Orders order : orders) {
            Shipment shipment = shipmentRepository.findByOrdersId(order.getId());
            ShipmentInfo shipmentInfo = new ShipmentInfo();
            shipmentInfo.setShipmentId(shipment.getId());
            shipmentInfo.setCreatedAt(shipment.getCreatedAt());
            shipmentInfo.setShipmentStatus(shipment.getShipmentStatus());
            shipmentInfos.add(shipmentInfo);
        }

        // OrderReport 객체 생성 및 반환
        OrderReport report = new OrderReport();
        report.setUserId(user.getId());
        report.setUsername(user.getUsername());
        report.setEmail(user.getEmail());
        report.setOrders(orderInfos);
        report.setShipments(shipmentInfos);
        return report;
    }

    public OrderReport getOrderReportQueryDsl(Long userId) {
        QUser user = QUser.user;
        QOrders orders = QOrders.orders;
        QOrderDetail orderDetail = QOrderDetail.orderDetail;
        QProduct product = QProduct.product;
        QShipment shipment = QShipment.shipment;

        List<OrderInfo> orderInfos = queryFactory
                .selectFrom(orders)
                .join(orders.user, user).fetchJoin()
                .leftJoin(orders.orderDetails, orderDetail).fetchJoin()
                .leftJoin(orderDetail.product, product).fetchJoin()
                .where(user.id.eq(userId))
                .distinct()
                .fetch()
                .stream()
                .map(o -> new OrderInfo(
                        o.getId(),
                        o.getCreatedAt(),
                        o.getStatus(),
                        o.getOrderDetails().stream().map(od -> new ProductInfo(
                                od.getProduct().getId(),
                                od.getProduct().getProductName(),
                                od.getProduct().getPrice(),
                                od.getQuantity(),
                                od.getProduct().getPrice().multiply(BigDecimal.valueOf(od.getQuantity()))
                        )).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

        List<ShipmentInfo> shipmentInfos = queryFactory
                .selectFrom(shipment)
                .join(shipment.orders, orders).fetchJoin()
                .where(orders.user.id.eq(userId))
                .fetch()
                .stream()
                .map(s -> new ShipmentInfo(s.getId(), s.getCreatedAt(), s.getShipmentStatus()))
                .collect(Collectors.toList());

        User userInfo = queryFactory
                .selectFrom(user)
                .where(user.id.eq(userId))
                .fetchOne();

        if (userInfo == null) {
            throw new RuntimeException("User not found");
        }

        // Construct and return the OrderReport object
        OrderReport report = new OrderReport();
        report.setUserId(userInfo.getId());
        report.setUsername(userInfo.getUsername());
        report.setEmail(userInfo.getEmail());
        report.setOrders(orderInfos);
        report.setShipments(shipmentInfos);

        return report;
    }
}

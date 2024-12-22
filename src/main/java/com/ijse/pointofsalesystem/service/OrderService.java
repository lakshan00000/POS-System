package com.ijse.pointofsalesystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.pointofsalesystem.entity.Item;
import com.ijse.pointofsalesystem.entity.Order;



@Service
public interface OrderService {
    List<Order> getOrders();
    Order createOrder(Order order);
    void deleteOrder(Long id);
    Order getOrderById(Long id );
}

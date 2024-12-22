package com.ijse.pointofsalesystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.pointofsalesystem.entity.Item;
import com.ijse.pointofsalesystem.entity.Order;
import com.ijse.pointofsalesystem.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
    

     @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getOrders() {
       return orderRepository.findAll(); 
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
        
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

     @Override
    public Order getOrderById (Long id){
        return orderRepository.findById(id).orElse(null);
    }

    
}

package com.ijse.pointofsalesystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.pointofsalesystem.dto.OrderDto;
import com.ijse.pointofsalesystem.entity.Item;
import com.ijse.pointofsalesystem.entity.Order;
import com.ijse.pointofsalesystem.service.ItemService;
import com.ijse.pointofsalesystem.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class OrderController {
 
    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getOrders();

        return ResponseEntity.status(200).body(orders);
    }
    
    @PostMapping("/orders")
   
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto) {
        Order order = new Order();
        order.setTotalPrice(0.0);
        
        List<Long> ItemIds = orderDto.getItemIds();

        List<Item> orderedItems = new ArrayList<>();

        ItemIds.forEach(itemId -> {
            
            Item item = itemService.getItemById(itemId);

            if(item != null) {

                orderedItems.add(item);

                order.setTotalPrice(order.getTotalPrice() + item.getPrice());
            }            
        });
        
        order.setOrdereditems(orderedItems);
        
        orderService.createOrder(order);

        return ResponseEntity.status(201).body(order);

    }

}

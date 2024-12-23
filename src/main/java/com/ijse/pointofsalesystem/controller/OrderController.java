package com.ijse.pointofsalesystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.pointofsalesystem.dto.OrderDto;
import com.ijse.pointofsalesystem.entity.Item;
import com.ijse.pointofsalesystem.entity.Order;
import com.ijse.pointofsalesystem.service.ItemService;
import com.ijse.pointofsalesystem.service.OrderService;

@RestController
@CrossOrigin (origins = "*")
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

        List<Long> itemIds = orderDto.getItemIds();
        List<Item> orderedItems = new ArrayList<>();

        itemIds.forEach(itemId -> {
            Item item = itemService.getItemById(itemId);
            if (item != null) {
                orderedItems.add(item);
                order.setTotalPrice(order.getTotalPrice() + item.getPrice());
            }
        });

        order.setOrdereditems(orderedItems);
        orderService.createOrder(order);

        return ResponseEntity.status(201).body(order);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return ResponseEntity.status(404).body("Order not found");
        }

        orderService.deleteOrder(orderId);
        return ResponseEntity.status(200).body("Order deleted successfully");
    }
}

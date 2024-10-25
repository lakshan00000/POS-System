package com.ijse.pointofsalesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.pointofsalesystem.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
}

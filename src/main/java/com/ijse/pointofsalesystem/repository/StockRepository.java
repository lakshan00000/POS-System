package com.ijse.pointofsalesystem.repository;

import com.ijse.pointofsalesystem.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}

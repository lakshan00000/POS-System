package com.ijse.pointofsalesystem.service;

import com.ijse.pointofsalesystem.dto.StockDto;
import com.ijse.pointofsalesystem.entity.Stock;

import java.util.List;

public interface StockService {
    StockDto createStock(StockDto stockDto);
    StockDto getStockById(Long id);
}

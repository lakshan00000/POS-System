package com.ijse.pointofsalesystem.controller;

import com.ijse.pointofsalesystem.dto.StockDto;
import com.ijse.pointofsalesystem.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
@CrossOrigin(origins = "*")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<StockDto> createStock(@RequestBody StockDto stockDto) {
        return ResponseEntity.ok(stockService.createStock(stockDto));
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<StockDto> getStockById(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getStockById(id));
    }
}

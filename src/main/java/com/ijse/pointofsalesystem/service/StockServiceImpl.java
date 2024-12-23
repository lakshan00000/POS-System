package com.ijse.pointofsalesystem.service;

import com.ijse.pointofsalesystem.dto.StockDto;
import com.ijse.pointofsalesystem.entity.Stock;
import com.ijse.pointofsalesystem.entity.Item;
import com.ijse.pointofsalesystem.repository.StockRepository;
import com.ijse.pointofsalesystem.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public StockDto createStock(StockDto stockDto) {
        Item item = itemRepository.findById(stockDto.getItemId())
            .orElseThrow(() -> new RuntimeException("Item not found"));

        Stock stock = new Stock();
        stock.setItem(item);
        stock.setQuantity(stockDto.getQuantity());
        stock = stockRepository.save(stock);

        StockDto response = new StockDto();
        response.setId(stock.getId());
        response.setItemId(item.getId());
        response.setItemName(item.getName());
        response.setQuantity(stock.getQuantity());

        return response;
    }

    @Override
    public StockDto getStockById(Long id) {
        Stock stock = stockRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Stock not found"));

        StockDto response = new StockDto();
        response.setId(stock.getId());
        response.setItemId(stock.getItem().getId());
        response.setItemName(stock.getItem().getName());
        response.setQuantity(stock.getQuantity());

        return response;
    }
}

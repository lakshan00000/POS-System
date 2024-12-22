package com.ijse.pointofsalesystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.pointofsalesystem.dto.ItemReqDto;
import com.ijse.pointofsalesystem.entity.Category;
import com.ijse.pointofsalesystem.entity.Item;
import com.ijse.pointofsalesystem.service.CategoryService;
import com.ijse.pointofsalesystem.service.ItemService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin (origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/Item")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> itemsList = itemService.getItemsList();

        return ResponseEntity.status(200).body(itemsList);
    }

    @PostMapping("/Item")
    
    public ResponseEntity<String> createItem(@RequestBody ItemReqDto itemReqDto) {
        try {
            Item item = new Item();
            item.setName(itemReqDto.getName());
            item.setPrice(itemReqDto.getPrice());
            item.setDescription(itemReqDto.getDescription());
    
            Category category = categoryService.getCategoryById(itemReqDto.getCategoryId());
            if (category == null) {
                return ResponseEntity.status(422).body("Invalid category ID");
            }
            item.setCategory(category);
    
            if (item.getName() == null || item.getName().isEmpty()) {
                return ResponseEntity.status(422).body("Please enter a valid item name");
            }
            if (item.getPrice() <= 0.0) {
                return ResponseEntity.status(422).body("Enter a valid number as the price");
            }
            if (item.getDescription() == null || item.getDescription().isEmpty()) {
                return ResponseEntity.status(422).body("Enter a valid description");
            }
    
            itemService.createItem(item);
            return ResponseEntity.status(201).body("Item added successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }
    

     @GetMapping("/Item/{itemId}") 
    public ResponseEntity<Item> getItemById(@PathVariable Long itemId) {
        Item item = itemService.getItemById(itemId);

        if(item == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            return ResponseEntity.status(200).body(item);
        }
    }

    @PutMapping("/Item/{itemId}")
    public ResponseEntity<?> updateItem(@PathVariable Long itemId, @RequestBody ItemReqDto itemReqDto) {
        Item existingItem = itemService.getItemById(itemId);
        if (existingItem == null) {
            return ResponseEntity.status(404).body("Item not found");
        }
    
        existingItem.setName(itemReqDto.getName());
        existingItem.setPrice(itemReqDto.getPrice());
        existingItem.setDescription(itemReqDto.getDescription());
    
        
        if (itemReqDto.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(itemReqDto.getCategoryId());
            if (category == null) {
                return ResponseEntity.status(422).body("Invalid category ID");
            }
            existingItem.setCategory(category);
        } else {
            existingItem.setCategory(null); 
        }
    
        
        itemService.createItem(existingItem);
        return ResponseEntity.status(200).body("Item updated successfully");
    }
    

    @DeleteMapping("/Item/{itemId}")
public ResponseEntity<String> deleteItem(@PathVariable Long itemId) {
    Item existingItem = itemService.getItemById(itemId);
    if (existingItem == null) {
        return ResponseEntity.status(404).body("Item not found");
    }

    try {
        itemService.deleteItem(itemId);
        return ResponseEntity.status(200).body("Item deleted successfully");
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Error deleting item: " + e.getMessage());
    }
}

    
    
}

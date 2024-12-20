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
       
        Item item = new Item();
        item.setName(itemReqDto.getName());
        item.setPrice(itemReqDto.getPrice());
        item.setQty(itemReqDto.getQty());

        Category category = categoryService.getCategoryById(itemReqDto.getCategoryId());
        item.setCategory(category);


        if(item.getName() == null || item.getName() == "") {
           
            return ResponseEntity.status(422).body("Please enter a valid item name");
        }

        if(item.getPrice() == 0.0) {
            
            return ResponseEntity.status(422).body("Enter a valid number as the price");
        }
        
        if(item.getQty() == 0) {
            
            return ResponseEntity.status(422).body("Enter a valid number as the qty");
        }

        itemService.createItem(item);

        return ResponseEntity.status(201).body("Item added successfully");
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
    public ResponseEntity<Item> updateItem(@PathVariable Long itemId, @RequestBody Item item) {
        Item updatedItem = itemService.updateItem(itemId, item);

        if(updatedItem == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            return ResponseEntity.status(200).body(updatedItem);
        }
    }

    @DeleteMapping("Item/{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
    }
    
    
}

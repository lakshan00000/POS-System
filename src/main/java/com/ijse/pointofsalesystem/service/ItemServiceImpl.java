package com.ijse.pointofsalesystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.pointofsalesystem.entity.Item;
import com.ijse.pointofsalesystem.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getItemsList() {
       return itemRepository.findAll(); 
    }

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
        
    }

    @Override
    public Item getItemById (Long id){
        return itemRepository.findById(id).orElse(null);
    }

      @Override
    public Item updateItem(Long id, Item item) {
        Item existingItem = itemRepository.findById(id).orElse(null);

        if(existingItem == null) {
            return null;
        } else {
            existingItem.setName(item.getName());
            existingItem.setPrice(item.getPrice());
            existingItem.setQty(item.getQty());
            return itemRepository.save(existingItem);
        }
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
    
}

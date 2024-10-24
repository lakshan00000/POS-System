package com.ijse.pointofsalesystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.pointofsalesystem.entity.Item;

@Service
public interface ItemService {
    List<Item> getItemsList();
    Item createItem(Item item);
    Item getItemById(Long id );
    Item updateItem(Long id,Item item);
    void deleteItem(Long id);

}

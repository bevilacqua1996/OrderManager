package com.system.order.manager.controller;

import com.system.order.manager.api.ItemApi;
import com.system.order.manager.model.Item;
import com.system.order.manager.model.ItemRequest;
import com.system.order.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController implements ItemApi {

    @Autowired
    private ItemService itemService;

    @Override
    public ResponseEntity<Item> getItemById(Integer id) {
        return new ResponseEntity<>(itemService.getItemById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createItem(ItemRequest itemRequest) {
        itemService.createItem(itemRequest);
        return new ResponseEntity<>("Item created!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteItemById(Integer id) {
        itemService.deleteItemById(id);
        return new ResponseEntity<>("Item Deleted!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Item> updateItemById(Integer id, ItemRequest itemRequest) {
        Item item = itemService.updateItemById(id, itemRequest);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

}

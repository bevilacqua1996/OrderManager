package com.system.order.manager.controller;

import com.system.order.manager.api.ItemsApi;
import com.system.order.manager.model.Item;
import com.system.order.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemsController implements ItemsApi {

    @Autowired
    private ItemService itemService;

    @Override
    public ResponseEntity<List<Item>> listItems() {
        return new ResponseEntity<>(itemService.listItems(), HttpStatus.OK);
    }
}

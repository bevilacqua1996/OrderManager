package com.system.order.manager.service;

import com.system.order.manager.model.Item;
import com.system.order.manager.model.ItemRequest;

import java.util.List;

public interface ItemService {

    Item getItemById(Integer id);

    void createItem(ItemRequest itemRequest);

    void deleteItem(Integer id);

    Item updateItem(Integer id, ItemRequest itemRequest);

    List<Item> listItems();

}

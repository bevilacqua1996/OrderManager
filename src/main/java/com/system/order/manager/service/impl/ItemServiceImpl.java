package com.system.order.manager.service.impl;

import com.system.order.manager.model.Item;
import com.system.order.manager.model.ItemEntity;
import com.system.order.manager.model.ItemRequest;
import com.system.order.manager.repository.ItemRepository;
import com.system.order.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item getItemById(Integer id) {
        ItemEntity itemEntity = itemRepository.findById(id).get();

        Item item = new Item();
        item.setName(itemEntity.getName());
        item.setQuantity(itemEntity.getQuantity());

        return item;
    }

    @Override
    public void createItem(ItemRequest itemRequest) {
        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setName(itemRequest.getName());
        itemEntity.setQuantity(itemRequest.getQuantity());

        itemRepository.save(itemEntity);

    }

    @Override
    public void deleteItem(Integer id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Item updateItem(Integer id, ItemRequest itemRequest) {

        ItemEntity itemEntity = itemRepository.findById(id).get();

        itemEntity.setName(itemRequest.getName());
        itemEntity.setQuantity(itemRequest.getQuantity());

        itemRepository.save(itemEntity);

        Item item = new Item();

        item.setName(itemRequest.getName());
        item.setQuantity(itemRequest.getQuantity());

        return item;

    }

    @Override
    public List<Item> listItems() {
        List<ItemEntity> listItemEntity = itemRepository.findAll();
        List<Item> listItem = new ArrayList<>();

        for(ItemEntity itemEntity : listItemEntity) {
            Item item = new Item();

            item.setQuantity(itemEntity.getQuantity());
            item.setName(itemEntity.getName());

            listItem.add(item);
        }

        return listItem;
    }
}

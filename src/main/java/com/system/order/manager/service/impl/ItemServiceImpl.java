package com.system.order.manager.service.impl;

import com.system.order.manager.exception.NoDataFoundException;
import com.system.order.manager.model.Item;
import com.system.order.manager.model.ItemEntity;
import com.system.order.manager.model.ItemRequest;
import com.system.order.manager.repository.ItemRepository;
import com.system.order.manager.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Override
    public Item getItemById(Integer id) {
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(NoDataFoundException::new);

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

        logger.info("ITEM CREATED: " + itemEntity.getName());

    }

    @Override
    public void deleteItemById(Integer id) {
        itemRepository.deleteById(id);
        logger.warn("ITEM DELETED: Item ID " + id);
    }

    @Override
    public Item updateItemById(Integer id, ItemRequest itemRequest) {

        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(NoDataFoundException::new);

        itemEntity.setName(itemRequest.getName());
        itemEntity.setQuantity(itemRequest.getQuantity());

        itemRepository.save(itemEntity);

        logger.info("ITEM UPDATED: " + itemEntity.getName());

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

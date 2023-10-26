package com.system.order.manager.web.builders;

import com.system.order.manager.model.Item;

public class ItemBuilder {

    private Item item;

    private ItemBuilder() {

    }

    public static ItemBuilder item() {
        ItemBuilder itemBuilder = new ItemBuilder();
        defaultItemData(itemBuilder);
        return itemBuilder;
    }

    public static void defaultItemData(ItemBuilder builder) {
        builder.item = new Item();
        Item element = builder.item;

        element.setName("Notebook");
        element.setQuantity(10);
    }

    public ItemBuilder withName(String name) {
        item.setName(name);
        return this;
    }

    public ItemBuilder withQuantity(Integer quantity) {
        item.setQuantity(quantity);
        return this;
    }

    public Item now() {
        return item;
    }

}

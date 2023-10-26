package com.system.order.manager.web.builders;

import com.system.order.manager.model.Order;
import com.system.order.manager.model.OrderStatusEnum;

import java.time.LocalDate;

public class OrderBuilder {

    private Order order;

    private OrderBuilder() {

    }

    public static OrderBuilder order() {
        OrderBuilder orderBuilder = new OrderBuilder();
        defaultOrderData(orderBuilder);
        return orderBuilder;
    }

    public static void defaultOrderData(OrderBuilder builder) {
        builder.order = new Order();
        Order element = builder.order;

        element.setUser(UserBuilder.user().now().getName());
        element.setOrderStatus(OrderStatusEnum.OPEN.getStatus());
        element.setItem(ItemBuilder.item().now().getName());
        element.setQuantity(ItemBuilder.item().now().getQuantity());
        element.setCreationDate(LocalDate.now());

    }

    public Order now() {
        return order;
    }
}

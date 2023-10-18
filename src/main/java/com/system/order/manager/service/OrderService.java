package com.system.order.manager.service;

import com.system.order.manager.model.Order;
import com.system.order.manager.model.OrderRequest;

import java.util.List;

public interface OrderService {

    void createOrder(OrderRequest orderRequest);

    Order getOrderById(Integer id);

    Order updateOrderById(OrderRequest orderRequest, Integer id);

    void deleteOrderById(Integer id);

    List<Order> listOrders();

}

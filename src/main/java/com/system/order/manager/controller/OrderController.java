package com.system.order.manager.controller;

import com.system.order.manager.api.OrderApi;
import com.system.order.manager.model.Order;
import com.system.order.manager.model.OrderRequest;
import com.system.order.manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController implements OrderApi {

    @Autowired
    private OrderService orderService;

    @Override
    public ResponseEntity<String> createOrder(OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
        return new ResponseEntity<>("Order created!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Order> getOrderById(Integer id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Order> updateOrderById(Integer id, OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.updateOrderById(orderRequest, id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteOrderById(Integer id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>("Order deleted!", HttpStatus.OK);
    }
}

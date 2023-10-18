package com.system.order.manager.controller;

import com.system.order.manager.api.OrdersApi;
import com.system.order.manager.model.Order;
import com.system.order.manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersController implements OrdersApi {

    @Autowired
    private OrderService orderService;

    @Override
    public ResponseEntity<List<Order>> listOrders() {
        return new ResponseEntity<>(orderService.listOrders(), HttpStatus.OK);
    }
}

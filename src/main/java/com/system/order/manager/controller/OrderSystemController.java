package com.system.order.manager.controller;

import com.system.order.manager.api.OrderSystemApi;
import com.system.order.manager.model.*;
import com.system.order.manager.service.ItemService;
import com.system.order.manager.service.OrderService;
import com.system.order.manager.service.StockMovementService;
import com.system.order.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderSystemController implements OrderSystemApi {

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StockMovementService stockMovementService;

    @Autowired
    private UserService userService;

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

    @Override
    public ResponseEntity<List<Item>> listItems() {
        return new ResponseEntity<>(itemService.listItems(), HttpStatus.OK);
    }

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

    @Override
    public ResponseEntity<List<Order>> listOrders() {
        return new ResponseEntity<>(orderService.listOrders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StockMovement> getStockMovementById(Integer id) {
        return new ResponseEntity<>(stockMovementService.getStockMovementById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StockMovement> updateStockMovementById(Integer id, StockMovementRequest stockMovementRequest) {
        return new ResponseEntity<>(stockMovementService.updateStockMovementById(stockMovementRequest,id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteStockMovementById(Integer id) {
        stockMovementService.deleteStockMovementById(id);
        return new ResponseEntity<>("Stock Movement deleted!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createStockMovement(StockMovementRequest stockMovementRequest) {
        stockMovementService.createStockMovement(stockMovementRequest);
        return new ResponseEntity<>("Stock Movement created!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<StockMovement>> listStockMovements() {
        return new ResponseEntity<>(stockMovementService.listStockMovements(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StockMovement> createStockMovementNewStatusByOrderId(Integer status, Integer orderId) {
        return new ResponseEntity<>(stockMovementService.createStockMovementNewStatusByOrderId(orderId, status), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<StockMovement>> getStockMovementByOrderId(Integer orderId) {
        return new ResponseEntity<>(stockMovementService.getStockMovementByOrderId(orderId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> updateUserById(Integer id, UserRequest userRequest) {
        return new ResponseEntity<>(userService.updateUserById(id, userRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUserById(Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteUserById(Integer id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("User deleted!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createUser(UserRequest userRequest) {
        userService.createUser(userRequest);
        return new ResponseEntity<>("User created!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> listUsers() {
        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }
}

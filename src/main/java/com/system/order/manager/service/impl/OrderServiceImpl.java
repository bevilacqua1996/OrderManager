package com.system.order.manager.service.impl;

import com.system.order.manager.exception.InsufficientItemInStock;
import com.system.order.manager.exception.NoDataFoundException;
import com.system.order.manager.model.*;
import com.system.order.manager.repository.ItemRepository;
import com.system.order.manager.repository.OrderRepository;
import com.system.order.manager.repository.UserRepository;
import com.system.order.manager.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Override
    public void createOrder(OrderRequest orderRequest) {
        OrderEntity orderEntity = new OrderEntity();

        UserEntity user = userRepository.findByEmail(orderRequest.getUserEmail());
        ItemEntity item = itemRepository.findByName(orderRequest.getItem());

        if(orderRequest.getQuantity()>item.getQuantity()) {
            throw new InsufficientItemInStock();
        }

        orderEntity.setCreationDate(Date.from(Instant.now()));
        orderEntity.setItem(item);
        orderEntity.setQuantity(orderRequest.getQuantity());
        orderEntity.setOrderStatus(OrderStatusEnum.ofCodeNumber(orderRequest.getOrderStatus()));
        orderEntity.setUser(user);

        orderRepository.save(orderEntity);

        logger.info("ORDER CREATED");

    }

    @Override
    public Order getOrderById(Integer id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(NoDataFoundException::new);

        Order order = new Order();

        order.setOrderStatus(orderEntity.getOrderStatus().getStatus());
        order.setItem(orderEntity.getItem().getName());
        order.setUser(orderEntity.getUser().getName());
        order.setQuantity(orderEntity.getQuantity());
        order.setCreationDate(orderEntity.getCreationDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());

        return order;

    }

    @Override
    public Order updateOrderById(OrderRequest orderRequest, Integer id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(NoDataFoundException::new);

        UserEntity user = userRepository.findByEmail(orderRequest.getUserEmail());
        ItemEntity item = itemRepository.findByName(orderRequest.getItem());

        if(orderRequest.getQuantity()>item.getQuantity()) {
            throw new InsufficientItemInStock();
        }

        orderEntity.setCreationDate(Date.from(Instant.now()));
        orderEntity.setItem(item);
        orderEntity.setQuantity(orderRequest.getQuantity());
        orderEntity.setOrderStatus(OrderStatusEnum.ofCodeNumber(orderRequest.getOrderStatus()));
        orderEntity.setUser(user);

        orderRepository.save(orderEntity);

        Order order = new Order();

        order.setOrderStatus(orderEntity.getOrderStatus().getStatus());
        order.setItem(orderEntity.getItem().getName());
        order.setUser(orderEntity.getUser().getName());
        order.setQuantity(orderEntity.getQuantity());
        order.setCreationDate(orderEntity.getCreationDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());

        return order;

    }

    @Override
    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> listOrders() {
        List<OrderEntity> orders = orderRepository.findAll();
        List<Order> orderList = new ArrayList<>();

        for(OrderEntity orderEntity : orders) {
            Order order = new Order();

            order.setQuantity(orderEntity.getQuantity());
            order.setUser(orderEntity.getUser().getName());
            order.setItem(orderEntity.getItem().getName());
            order.setOrderStatus(orderEntity.getOrderStatus().getStatus());
            order.setCreationDate(orderEntity.getCreationDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());

            orderList.add(order);
        }

        return orderList;
    }

    @Override
    public void closeOrder(Integer orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(NoDataFoundException::new);

        orderEntity.setOrderStatus(OrderStatusEnum.ofCodeNumber(2));

        orderRepository.save(orderEntity);
    }


}

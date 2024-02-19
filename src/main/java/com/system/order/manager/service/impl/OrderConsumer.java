package com.system.order.manager.service.impl;

import com.system.order.manager.model.OrderRequest;
import com.system.order.manager.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderConsumer {

    Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    @Autowired
    private OrderService orderService;

    private static final String TOPIC_EXTERNAL_ORDERS = "external-orders";

    @KafkaListener(topics = TOPIC_EXTERNAL_ORDERS, groupId = "group-1")
    public void consume(List<OrderRequest> orderRequests, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitionList,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsetList) {
        try {
            logger.info("Received message count: " + (orderRequests != null ? orderRequests.size() : 0)
                    + ", offset start: " + offsetList.get(0) + ", end: " + offsetList.get(offsetList.size() - 1));
            if(orderRequests!=null) {
                orderRequests.forEach(order -> orderService.createOrder(order));
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }

}

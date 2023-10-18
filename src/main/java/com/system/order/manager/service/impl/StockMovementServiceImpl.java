package com.system.order.manager.service.impl;

import com.system.order.manager.exception.NoDataFoundException;
import com.system.order.manager.exception.OrderAttributeClosedException;
import com.system.order.manager.model.*;
import com.system.order.manager.repository.OrderRepository;
import com.system.order.manager.repository.StockMovementRepository;
import com.system.order.manager.service.StockMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StockMovementServiceImpl implements StockMovementService {

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public StockMovement getStockMovementById(Integer id) {
        StockMovementEntity stockMovementEntity = stockMovementRepository.findById(id).orElseThrow(NoDataFoundException::new);

        StockMovement stockMovement = new StockMovement();

        stockMovement.setMovementStatus(stockMovementEntity.getMovementStatus().getStatus());
        stockMovement.setQuantity(stockMovementEntity.getQuantity());
        stockMovement.setCreationDate(stockMovementEntity.getCreationDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        stockMovement.setOrder(stockMovementEntity.getOrder().getOrderId());

        return stockMovement;
    }

    @Override
    public StockMovement updateStockMovementById(StockMovementRequest stockMovementRequest, Integer id) {
        StockMovementEntity stockMovementEntity = stockMovementRepository.findById(id).orElseThrow(NoDataFoundException::new);

        OrderEntity orderEntity = orderRepository.findById(stockMovementRequest.getOrder()).orElseThrow(NoDataFoundException::new);

        if(orderEntity.getOrderStatus().getCode()==2) {
            throw new OrderAttributeClosedException("Order " + orderEntity.getOrderId() + " already closed");
        }

        stockMovementEntity.setOrder(orderEntity);
        stockMovementEntity.setMovementStatus(MovementStatusEnum.ofCodeNumber(stockMovementRequest.getMovementStatus()));
        stockMovementEntity.setQuantity(stockMovementRequest.getQuantity());
        stockMovementEntity.setCreationDate(Date.from(Instant.now()));

        stockMovementRepository.save(stockMovementEntity);

        StockMovement stockMovement = new StockMovement();

        stockMovement.setOrder(stockMovementEntity.getOrder().getOrderId());
        stockMovement.setMovementStatus(stockMovementEntity.getMovementStatus().getStatus());
        stockMovement.setQuantity(stockMovementEntity.getQuantity());
        stockMovement.setCreationDate(stockMovementEntity.getCreationDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());

        return stockMovement;
    }

    @Override
    public void deleteStockMovementById(Integer id) {
        stockMovementRepository.deleteById(id);
    }

    @Override
    public void createStockMovement(StockMovementRequest stockMovementRequest) {
        StockMovementEntity stockMovementEntity = new StockMovementEntity();

        OrderEntity orderEntity = orderRepository.findById(stockMovementRequest.getOrder()).orElseThrow(NoDataFoundException::new);

        if(orderEntity.getOrderStatus().getCode()==2) {
            throw new OrderAttributeClosedException("Order " + orderEntity.getOrderId() + " already closed");
        }

        stockMovementEntity.setMovementStatus(MovementStatusEnum.ofCodeNumber(stockMovementRequest.getMovementStatus()));
        stockMovementEntity.setQuantity(stockMovementRequest.getQuantity());
        stockMovementEntity.setOrder(orderEntity);
        stockMovementEntity.setCreationDate(Date.from(Instant.now()));

        stockMovementRepository.save(stockMovementEntity);
    }

    @Override
    public List<StockMovement> listStockMovements() {
        List<StockMovementEntity> stockMovementEntityList = stockMovementRepository.findAll();
        List<StockMovement> stockMovementList = new ArrayList<>();

        for(StockMovementEntity stockMovementEntity : stockMovementEntityList) {
            StockMovement stockMovement = new StockMovement();

            stockMovement.setMovementStatus(stockMovementEntity.getMovementStatus().getStatus());
            stockMovement.setQuantity(stockMovementEntity.getQuantity());
            stockMovement.setOrder(stockMovementEntity.getOrder().getOrderId());
            stockMovement.setCreationDate(stockMovementEntity.getCreationDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());

            stockMovementList.add(stockMovement);
        }

        return stockMovementList;

    }


}

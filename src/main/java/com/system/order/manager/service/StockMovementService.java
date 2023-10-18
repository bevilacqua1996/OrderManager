package com.system.order.manager.service;

import com.system.order.manager.model.StockMovement;
import com.system.order.manager.model.StockMovementRequest;

import java.util.List;

public interface StockMovementService {

    StockMovement getStockMovementById(Integer id);

    StockMovement updateStockMovementById(StockMovementRequest stockMovementRequest, Integer id);

    void deleteStockMovementById(Integer id);

    void createStockMovement(StockMovementRequest stockMovementRequest);

    List<StockMovement> listStockMovements();

}

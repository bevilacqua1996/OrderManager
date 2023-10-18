package com.system.order.manager.controller;

import com.system.order.manager.api.StockApi;
import com.system.order.manager.model.StockMovement;
import com.system.order.manager.model.StockMovementRequest;
import com.system.order.manager.service.StockMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockMovementController implements StockApi {

    @Autowired
    private StockMovementService stockMovementService;

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
}

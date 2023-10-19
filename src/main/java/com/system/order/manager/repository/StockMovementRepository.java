package com.system.order.manager.repository;

import com.system.order.manager.model.StockMovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockMovementRepository extends JpaRepository<StockMovementEntity, Integer> {

    List<StockMovementEntity> findByOrder_orderId(Integer orderId);
}

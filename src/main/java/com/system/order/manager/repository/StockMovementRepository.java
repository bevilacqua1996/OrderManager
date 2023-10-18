package com.system.order.manager.repository;

import com.system.order.manager.model.StockMovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovementEntity, Integer> {
}

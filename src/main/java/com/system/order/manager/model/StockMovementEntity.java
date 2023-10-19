package com.system.order.manager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stock_movement")
@Data
public class StockMovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_movement_id")
    private Integer stockMovementId;

    private Date creationDate;

    private Integer quantity;

    private MovementStatusEnum movementStatus;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private OrderEntity order;
}

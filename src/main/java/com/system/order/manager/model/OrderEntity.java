package com.system.order.manager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "order_")
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    private Date creationDate;

    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private ItemEntity item;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    private Integer quantity;

    private OrderStatusEnum orderStatus;

    @OneToMany(mappedBy="order")
    private Set<StockMovementEntity> stockMovements;

}

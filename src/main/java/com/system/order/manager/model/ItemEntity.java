package com.system.order.manager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "item_")
@Data
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId;

    private String name;

    private Integer quantity;

    @OneToMany(mappedBy="item")
    private Set<OrderEntity> orders;
}

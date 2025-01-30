package com.e_approval.modules.leave.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_entity")
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "status")
    private String status;

    @Column(name = "amount")
    private Double amount;
}

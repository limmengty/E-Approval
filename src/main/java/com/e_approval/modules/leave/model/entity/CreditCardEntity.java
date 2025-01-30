package com.e_approval.modules.leave.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "credit_cards")
@Getter
@Setter
public class CreditCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total")
    private Double total;

    @Column(name = "totalWithTax")
    private Double totalWithTax;
}

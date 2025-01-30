package com.e_approval.modules.leave.repository;

import com.e_approval.modules.leave.model.entity.CreditCardEntity;
import com.e_approval.modules.leave.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<CreditCardEntity, Long> {
//    OrderEntity findByOrderNumber(String orderNumber);
}

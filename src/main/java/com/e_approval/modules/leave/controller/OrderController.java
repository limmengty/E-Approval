package com.e_approval.modules.leave.controller;

import com.e_approval.modules.leave.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping("/orders/{total}")
    public void createOrder(@PathVariable Double total) {
        System.out.printf("Order created with total %.2f\n", total);
        this.orderService.save(total);
   }
}

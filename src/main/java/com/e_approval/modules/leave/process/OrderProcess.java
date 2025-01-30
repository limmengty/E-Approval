package com.e_approval.modules.leave.process;

import com.e_approval.modules.leave.model.entity.CreditCardEntity;
import com.e_approval.modules.leave.repository.OrderRepository;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class OrderProcess {

    private final OrderRepository orderRepository;
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderProcess.class);
    @JobWorker(type = "charge-credit-card")
    public CreditCardEntity chargeCreditCard(@Variable(name = "totalWithTax") Double totalWithTax){
        LOGGER.info("Charge credit card: {}", totalWithTax);
        CreditCardEntity creditCardEntity = new CreditCardEntity();
        creditCardEntity.setTotalWithTax(totalWithTax);
        return this.orderRepository.save(creditCardEntity);
    }

//    @PostConstruct
//    public String initializedMethod(){
//        LOGGER.info("Initialize method");
//        return "Hello World!";
//    }

}

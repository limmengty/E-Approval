//package com.e_approval.process;
//
//import io.camunda.zeebe.spring.client.annotation.JobWorker;
//import io.camunda.zeebe.spring.client.annotation.Variable;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//import org.slf4j.Logger;
//
//@Component
//public class ProcessPayments {
//    private final static Logger LOGGER = LoggerFactory.getLogger(ProcessPayments.class);
//    @JobWorker(type = "charge-credit-card", autoComplete = false)
//    public Map<String, Double> changeCreditCard(@Variable(name = "totalWithTax") Double totalWithTax){
//        LOGGER.info("Change credit card: {} ", totalWithTax);
//        return Map.of("amountChanged", totalWithTax);
//    }
//}

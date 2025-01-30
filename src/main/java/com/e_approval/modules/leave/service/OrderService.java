package com.e_approval.modules.leave.service;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ZeebeClient zeebeClient;

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    public void save(Double total) {
        var bpmnProcessId = "process-payments";
        var event = zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId(bpmnProcessId)
                .latestVersion()
                .variables(Map.of("total", total))
                .send()
                .join();

        LOGGER.info("started a process instance: {}", event.getBpmnProcessId());
    }
}

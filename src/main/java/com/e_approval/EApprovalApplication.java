package com.e_approval;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import org.slf4j.Logger;

@SpringBootApplication
@Deployment(resources = "classpath:process-payments.bpmn")
public class EApprovalApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(EApprovalApplication.class);

	@Autowired
	private ZeebeClient zeebeClient;

	public static void main(String[] args) {
		SpringApplication.run(EApprovalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var bpmnProdcessId = "process-payments";
		var event = zeebeClient.newCreateInstanceCommand()
				.bpmnProcessId(bpmnProdcessId)
				.latestVersion()
				.variables(Map.of("total", 100))
				.send()
				.join();
		LOG.info("started a process instance: {}", event.getBpmnProcessId());

	}
}

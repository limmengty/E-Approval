package com.e_approval;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Deployment(resources = "classpath:process-payments.bpmn")
public class EApprovalApplication {

	public static void main(String[] args) {
		SpringApplication.run(EApprovalApplication.class, args);
	}

}

package com.e_approval.modules.leave.service;

import com.e_approval.modules.leave.model.entity.LeaveRequestDTO;
import com.e_approval.modules.leave.model.entity.LeaveRequestEntity;
import com.e_approval.modules.leave.repository.LeaveRequestRepository;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.DeploymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkflowService {

    private final ZeebeClient zeebeClient;
    private final LeaveRequestRepository leaveRequestRepository;

    // Deploy the workflow
    public void deployWorkFlow() {
        DeploymentEvent deploymentEvent = zeebeClient.newDeployResourceCommand()
                .addResourceFromClasspath("leave-process.bpmn")
                .send()
                .join();

        System.out.printf("Workflow deployed with key: " + deploymentEvent.getKey());
    }

    // start workflow instance
    public void startWorkflowInstance(LeaveRequestDTO leaveRequestDTO) {
        try {

            // Convert DTO to variables map
            Map<String, Object> variables = Map.of(
                    "employeeName", leaveRequestDTO.getEmployeeName(),
                    "leaveType", leaveRequestDTO.getLeaveType(),
                    "startDate", leaveRequestDTO.getStartDate().toString(),
                    "endDate", leaveRequestDTO.getEndDate().toString(),
                    "status", leaveRequestDTO.getStatus().toString()
            );

            var event = zeebeClient.newCreateInstanceCommand()
                    .bpmnProcessId("Process_114go1p")
                    .latestVersion()
                    .variables(variables)
                    .send()
                    .join();

            System.out.printf("Workflow instance started" + event.getBpmnProcessId() + leaveRequestDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Handle user task
    public void completedUserTask(Long taskId, boolean approved) {
        zeebeClient.newCompleteCommand(taskId)
                .variables(Map.of("approved", approved))
                .send()
                .join();
    }

    public Optional<LeaveRequestEntity> findLeave(Long id){
        var leave = this.leaveRequestRepository.findById(id);
        return leave;
    }

}

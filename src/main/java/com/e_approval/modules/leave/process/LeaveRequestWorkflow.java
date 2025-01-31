package com.e_approval.modules.leave.process;

import com.e_approval.modules.leave.model.entity.LeaveRequestEntity;
import com.e_approval.modules.leave.repository.LeaveRequestRepository;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LeaveRequestWorkflow {

    private final LeaveRequestRepository leaveRequestRepository;

    // save leave request to db
    @JobWorker(type = "save-leave-request")
    public void saveLeaveRequest(final JobClient client, final ActivatedJob job) {
        // Extract workflow variables
        Map<String, Object> variables = job.getVariablesAsMap();

        // Map variables to LeaveRequestEntity
        LeaveRequestEntity leaveRequestEntity = new LeaveRequestEntity();
        leaveRequestEntity.setEmployeeName((String) variables.get("employeeName"));
        leaveRequestEntity.setLeaveType((String) variables.get("leaveType"));
        leaveRequestEntity.setStartDate(LocalDate.parse((String) variables.get("startDate")));
        leaveRequestEntity.setEndDate(LocalDate.parse((String) variables.get("endDate")));
        leaveRequestEntity.setStatus((String) variables.get("status")); // Default status

        // Save to database
        leaveRequestRepository.save(leaveRequestEntity);

        // Complete the job
        client.newCompleteCommand(job.getKey()).send().join();

    }

    @JobWorker(type = "approve-leave-request")
    public void handleUserTask(final JobClient client, final ActivatedJob job) {
        // Log the task details
        System.out.println("User task fetched:");
        System.out.println("Task ID: " + job.getKey());
        System.out.println("Variables: " + job.getVariables());

        // Simulate user interaction (e.g., approval or rejection)
        boolean approved = true; // Replace with actual user input

        // Complete the task
        Map<String, Object> variables = new HashMap<>();
        variables.put("approved", approved);

        client.newCompleteCommand(job.getKey())
                .variables(variables)
                .send()
                .join();

        System.out.println("User task completed with approval: " + approved);
    }
}

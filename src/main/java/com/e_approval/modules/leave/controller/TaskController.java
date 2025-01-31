package com.e_approval.modules.leave.controller;

import com.e_approval.modules.leave.service.WorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

   private final WorkflowService workflowService;

    @PostMapping("/complete")
    public String completeUserTask(@RequestParam long taskId, @RequestParam boolean approved) {
        try {
            this.workflowService.completedUserTask(taskId, approved);

            return "User task completed successfully!";
        } catch (Exception e) {
            return "Failed to complete user task: " + e.getMessage();
        }
    }
}
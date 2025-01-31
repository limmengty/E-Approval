package com.e_approval.modules.leave.controller;

import com.e_approval.modules.leave.model.entity.LeaveRequestDTO;
import com.e_approval.modules.leave.model.entity.LeaveRequestEntity;
import com.e_approval.modules.leave.repository.LeaveRequestRepository;
import com.e_approval.modules.leave.service.WorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/leave")
@RequiredArgsConstructor
public class LeaveRequestController {
    private final WorkflowService workflowService;
    private final LeaveRequestRepository leaveRequestRepository;

    @PostMapping("/request")
    public String submitLeaveRequest(@RequestBody LeaveRequestDTO leaveRequest){
        workflowService.startWorkflowInstance(leaveRequest);
        return "Leave Request Submitted";
    }

    @GetMapping("/status/{id}")
    public LeaveRequestEntity getLeaveRequestStatus(@PathVariable("id") Long id){
        return this.leaveRequestRepository.findById(id).orElseThrow();
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Optional<LeaveRequestEntity>> getLeave(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.workflowService.findLeave(id));
    }

}

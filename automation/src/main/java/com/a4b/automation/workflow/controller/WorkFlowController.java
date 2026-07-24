package com.a4b.automation.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a4b.automation.workflow.dto.CreateWorkflowRequest;
import com.a4b.automation.workflow.entity.WorkFlowRequests;
import com.a4b.automation.workflow.service.WorkFlowService;



@RestController
@RequestMapping("/api/workflow")
public class WorkFlowController {
    @Autowired
    private WorkFlowService workFlowService;
    @PostMapping("/register")
    public ResponseEntity<WorkFlowRequests> register(@RequestBody CreateWorkflowRequest request){
        return ResponseEntity.ok(workFlowService.submitRequests(request));
    }
    @PutMapping("/requests/{id}/approve")
    public ResponseEntity<WorkFlowRequests> approve(@PathVariable Long requestId,@RequestParam String remark){
        return ResponseEntity.ok(workFlowService.approve(requestId, remark));
    }
    @PutMapping("/requests/{id}/reject")
    public ResponseEntity<WorkFlowRequests> reject(@PathVariable Long requestId,@RequestParam String remark) {
        return ResponseEntity.ok(workFlowService.reject(requestId, remark));

    }
    @GetMapping("/requests/{id}")
    public ResponseEntity<WorkFlowRequests> getRequest(
        @PathVariable Long id){

}

}

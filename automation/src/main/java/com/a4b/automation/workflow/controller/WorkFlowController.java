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
import com.a4b.automation.workflow.dto.WorkFlowRequestResponse;

import com.a4b.automation.workflow.mapper.WorkFlowmapper;
import com.a4b.automation.workflow.service.WorkFlowService;



@RestController
@RequestMapping("/api/workflow")
public class WorkFlowController {
    @Autowired
    private WorkFlowService workFlowService;
    @Autowired
    private WorkFlowmapper mapper;
    @PostMapping("/register")
    public ResponseEntity<WorkFlowRequestResponse> register(@RequestBody CreateWorkflowRequest request){
        return ResponseEntity.ok(mapper.requestToWorkFlowRequestResponse(workFlowService.submitRequests(request)));
    }
    @PutMapping("/requests/{id}/approve")
    public ResponseEntity<WorkFlowRequestResponse> approve(@PathVariable Long requestId,@RequestParam String remark){
        return ResponseEntity.ok(mapper.requestToWorkFlowRequestResponse(workFlowService.approve(requestId, remark)));
    }
    @PutMapping("/requests/{id}/reject")
    public ResponseEntity<WorkFlowRequestResponse> reject(@PathVariable Long requestId,@RequestParam String remark) {
        return ResponseEntity.ok(mapper.requestToWorkFlowRequestResponse(workFlowService.reject(requestId, remark)));

    }
    @GetMapping("/requests/{id}")
    public ResponseEntity<WorkFlowRequestResponse> getRequest(
        @PathVariable Long id){

}

}

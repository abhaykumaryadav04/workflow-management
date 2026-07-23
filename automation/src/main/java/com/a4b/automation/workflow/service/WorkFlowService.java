package com.a4b.automation.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a4b.automation.workflow.dto.CreateWorkflowRequest;
import com.a4b.automation.workflow.entity.ApprovalHistory;
import com.a4b.automation.workflow.entity.WorkFlow;
import com.a4b.automation.workflow.entity.WorkFlowRequests;
import com.a4b.automation.workflow.entity.WorkflowsSteps;
import com.a4b.automation.workflow.repo.WorkFlowRepo;
import com.a4b.automation.workflow.repo.WorkFlowRequestRepo;
import com.a4b.automation.workflow.repo.WorkflowaStepsRepo;

@Service
public class WorkFlowService {
    @Autowired
    private WorkFlowRepo workFlowRepo;
    @Autowired
    private WorkFlowRequestRepo workFlowRequestRepo;
    @Autowired
    private WorkflowaStepsRepo workflowaStepsRepo;
    @Autowired
    private ApprovalHistory approvalHistory;
    
    public WorkFlowRequests submitRequests(CreateWorkflowRequest request){
     WorkFlow workFlow=workFlowRepo.findById(request.getWorkflowId()).orElseThrow(()-> new RuntimeException("Aorkflow doesnot exists !!"));
     WorkflowsSteps workflowsSteps=workflowaStepsRepo.findById(request.get)
    }

}

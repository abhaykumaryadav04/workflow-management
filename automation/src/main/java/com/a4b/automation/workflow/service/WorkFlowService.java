package com.a4b.automation.workflow.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.a4b.automation.user.entity.User;
import com.a4b.automation.user.repo.UserRepo;
import com.a4b.automation.workflow.dto.CreateWorkflowRequest;
import com.a4b.automation.workflow.entity.ApprovalHistory;
import com.a4b.automation.workflow.entity.WorkFlow;
import com.a4b.automation.workflow.entity.WorkFlowRequests;
import com.a4b.automation.workflow.entity.WorkflowsSteps;
import com.a4b.automation.workflow.enums.ApprovalAction;
import com.a4b.automation.workflow.enums.RequestStatus;
import com.a4b.automation.workflow.repo.ApprovalHistoryRepo;
import com.a4b.automation.workflow.repo.WorkFlowRepo;
import com.a4b.automation.workflow.repo.WorkFlowRequestRepo;
import com.a4b.automation.workflow.repo.WorkflowaStepsRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class WorkFlowService {
    @Autowired
    private WorkFlowRepo workFlowRepo;
    @Autowired
    private WorkFlowRequestRepo workFlowRequestRepo;
    @Autowired
    private WorkflowaStepsRepo workflowaStepsRepo;
    @Autowired
    private ApprovalHistoryRepo approvalHistoryRepo;
    @Autowired
    private UserRepo userRepo;
    
    
    public WorkFlowRequests submitRequests(CreateWorkflowRequest request){
     WorkFlow workFlow=workFlowRepo.findById(request.getWorkflowId()).orElseThrow(()-> new RuntimeException("Aorkflow doesnot exists !!"));
     User employee=userRepo.findById(request.getEmployeeId()).orElseGet(()-> new UsernameNotFoundException('Employee does not exists.'));
      WorkflowsSteps workflowsSteps=workflowaStepsRepo.findByWorkflowAndStepOrder(workFlow, 1).orElseThrow(()-> new RuntimeException("Problem in workFlow"));
      WorkFlowRequests workFlowRequests=WorkFlowRequests.builder()
                                                        .employee(employee)
                                                        .currentStep(workflowsSteps)
                                                        .description(request.getDescription())
                                                        .title(request.getTitle())
                                                        .submitedAt(LocalDateTime.now())
                                                        .requestStatus(RequestStatus.PENDING)
                                                        .build();
    workFlowRequestRepo.save(workFlowRequests);
    ApprovalHistory approvalHistory=ApprovalHistory.builder()
                                                .user(employee)
                                                .actionAt(LocalDateTime.now())
                                                .remark("Request submited")
                                                .workFlowRequests(workFlowRequests)
                                                .workflowsSteps(workflowsSteps)
                                                .action(ApprovalAction.SUBMITED)
                                                .build();
    approvalHistoryRepo.save(approvalHistory);
    return workFlowRequests;
    }   
    public WorkFlowRequests approve(Long requestId,String remark){

      
        WorkFlowRequests requests=workFlowRequestRepo.findById(requestId).orElseThrow(()->new RuntimeException("NO request found"));
        WorkflowsSteps currentSteps=requests.getCurrentStep();
        Optional<WorkflowsSteps> nextStep = workflowaStepsRepo
        .findByWorkflowAndStepOrder(
                requests.getWorkFlow(),
                currentSteps.getSteps() + 1
        );
        Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();
        User currUser=(User)authentication.getPrincipal();
    ApprovalHistory history=ApprovalHistory.builder()
                                           .action(ApprovalAction.APPROVAL)
                                           .actionAt(LocalDateTime.now())
                                           .remark(remark)
                                           .user(currUser)
                                           .workFlowRequests(requests)
                                           .workflowsSteps(currentSteps)
                                           .build();
        approvalHistoryRepo.save(history);
        if(nextStep.isPresent()){
         requests.setCurrentStep(nextStep.get());
         requests.setRequestStatus(RequestStatus.PENDING);
        }else{
            requests.setRequestStatus(RequestStatus.APPROVED);
        }
      return requests;

    } 
    public WorkFlowRequests reject(Long requestId ,String remark){
        WorkFlowRequests request=workFlowRequestRepo.findById(requestId).orElseThrow(()->new RuntimeException("request doesnot exist."));
        Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();
        User currUser=(User)authentication.getPrincipal();
        WorkflowsSteps currStep=request.getCurrentStep();
        ApprovalHistory history=ApprovalHistory.builder() 
                                                .action(ApprovalAction.REJECTED)
                                                .actionAt(LocalDateTime.now())
                                                .remark(remark)
                                                .user(currUser)
                                                .workFlowRequests(request)
                                                .workflowsSteps(currStep)
                                                .build();
        approvalHistoryRepo.save(history);
        
    }
}

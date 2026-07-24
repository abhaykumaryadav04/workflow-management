package com.a4b.automation.workflow.mapper;

import org.springframework.stereotype.Component;

import com.a4b.automation.workflow.dto.WorkFlowRequestResponse;
import com.a4b.automation.workflow.entity.WorkFlowRequests;

@Component
public class WorkFlowmapper {
    public WorkFlowRequestResponse requestToWorkFlowRequestResponse(WorkFlowRequests requests){
        WorkFlowRequestResponse dto=WorkFlowRequestResponse.builder()
                                                           .currentStep(requests.getCurrentStep().getRole().getName().toString())
                                                           .description(requests.getDescription())
                                                           .employeeName(requests.getEmployee().getFirstName())
                                                           .status(requests.getRequestStatus().toString())
                                                           .submittedAt(requests.getSubmitedAt())
                                                           .title(requests.getTitle())
                                                           .workflowName(requests.getWorkFlow().getName())
                                                           .build()
                        return dto;
    }

}

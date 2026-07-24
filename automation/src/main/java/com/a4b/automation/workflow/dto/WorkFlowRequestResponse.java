package com.a4b.automation.workflow.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkFlowRequestResponse {
    private Long id;

private String workflowName;

private String employeeName;

private String currentStep;

private String status;

private String title;

private String description;

private LocalDateTime submittedAt;

}

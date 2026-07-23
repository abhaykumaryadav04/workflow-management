package com.a4b.automation.workflow.dto;

import lombok.Data;

@Data
public class CreateWorkflowRequest {
    private Long workflowId;

private Long employeeId;

private String title;

private String description;

}

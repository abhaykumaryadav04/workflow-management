package com.a4b.automation.workflow.entity;

import java.time.LocalDateTime;

import com.a4b.automation.user.entity.User;
import com.a4b.automation.workflow.enums.ApprovalAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ApprovalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "approver_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "workflow_step_id")
    private WorkflowsSteps workflowsSteps;
    @ManyToOne
   @JoinColumn(name = "workflow_request_id")
    private WorkFlowRequests workFlowRequests;
    private ApprovalAction action;
    private String remark;
    private LocalDateTime actionAt;

}

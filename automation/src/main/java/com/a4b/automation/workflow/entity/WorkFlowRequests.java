package com.a4b.automation.workflow.entity;

import java.time.LocalDateTime;

import com.a4b.automation.user.entity.User;
import com.a4b.automation.workflow.enums.RequestStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class WorkFlowRequests{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workflow_id")
    private WorkFlow workFlow;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employee;

    @ManyToOne
    @JoinColumn(name = "current_step_id")
    private WorkflowsSteps currentStep;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    private LocalDateTime submitedAt;

}



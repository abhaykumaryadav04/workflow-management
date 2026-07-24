package com.a4b.automation.workflow.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a4b.automation.workflow.entity.WorkFlow;
import com.a4b.automation.workflow.entity.WorkflowsSteps;

@Repository
public interface WorkflowaStepsRepo extends JpaRepository<WorkflowsSteps,Long>{
    Optional<WorkflowsSteps>
findByWorkflowAndStepOrder(
        WorkFlow workflow,
        Integer stepOrder
);

   List<WorkflowsSteps>
findByWorkflowOrderByStepOrderAsc(
        WorkFlow workflow
); 

}

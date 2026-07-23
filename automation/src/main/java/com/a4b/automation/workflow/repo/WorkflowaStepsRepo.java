package com.a4b.automation.workflow.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a4b.automation.workflow.entity.WorkflowsSteps;

@Repository
public interface WorkflowaStepsRepo extends JpaRepository<WorkflowsSteps,Long>{
    

    
}

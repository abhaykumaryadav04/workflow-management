package com.a4b.automation.workflow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a4b.automation.workflow.entity.WorkFlowRequests;

public interface WorkFlowRequestRepo extends JpaRepository<WorkFlowRequests,Long> {
    

}

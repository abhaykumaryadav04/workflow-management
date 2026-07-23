package com.a4b.automation.workflow.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a4b.automation.workflow.entity.WorkFlow;

@Repository
public interface WorkFlowRepo extends JpaRepository<WorkFlow,Long>{
Optional<WorkFlow> findById(Long id);
}

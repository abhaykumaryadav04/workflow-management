package com.a4b.automation.department.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a4b.automation.department.entity.Department;
@Repository
public interface DepartmentRepo extends JpaRepository<Department,Long> {
    Optional<Department> findByName(String nmae);
    boolean existsByName(String name);

}

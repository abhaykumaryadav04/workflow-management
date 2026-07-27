package com.a4b.automation.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a4b.automation.role.entity.Role;
import com.a4b.automation.user.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);
     Optional<User> findByEmployeeCode(String employeeCode);
     boolean existsByEmail(String email);
     boolean existsByEmployeeCode(String emaployeeCode);
    Optional<User> findByRole(Role role);

}

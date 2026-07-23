package com.a4b.automation.auth.service;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.a4b.automation.auth.dto.AuthResponse;
import com.a4b.automation.auth.dto.LoginRequest;
import com.a4b.automation.auth.dto.RegisterRequest;
import com.a4b.automation.auth.jwt.JwtService;
import com.a4b.automation.department.entity.Department;
import com.a4b.automation.department.repo.DepartmentRepo;
import com.a4b.automation.role.entity.Role;
import com.a4b.automation.role.repo.RoleRepo;
import com.a4b.automation.user.entity.User;
import com.a4b.automation.user.enums.UserStatus;
import com.a4b.automation.user.repo.UserRepo;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder; 
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtService jwtService;

    public AuthResponse login(LoginRequest request) {
      
    }

    public AuthResponse register(RegisterRequest request) {
    if(userRepo.existsByEmail(request.getEmail())){
        throw new RuntimeException("Email already exists!!");

    }
    if(userRepo.existsByEmaplyeeCode(request.getEmployeeCode())){
        throw new RuntimeException("EmployeeCode alredy exists!!");
    }
   Department department = departmentRepo.findById(request.getDepartment())
        .orElseThrow(() -> new RuntimeException("Department not found"));

Role role = roleRepo.findById(request.getRole())
        .orElseThrow(() -> new RuntimeException("Role not found"));
        User user =User.builder().employeeCode(request.getEmployeeCode())
                      .department(department)
                      .firstName(request.getFirstName())
                      .deginagation(request.getDeginagation())
                      .password(passwordEncoder.encode(request.getPassword()))
                      .phone(request.getPhone())
                      .role(role)
                      .secondName(request.getSecondName())
                      .status(UserStatus.ACTIVE)
                      .updaedAt(LocalDateTime.now())
                      .createdAt(LocalDateTime.now())
                      .build();
        userRepo.save(user);
        String token=jwtService.

     
      
    }

}

package com.a4b.automation.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.a4b.automation.department.repo.DepartmentRepo;
import com.a4b.automation.role.repo.RoleRepo;
import com.a4b.automation.user.repo.UserRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    


}

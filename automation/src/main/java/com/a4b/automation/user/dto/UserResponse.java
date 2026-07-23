package com.a4b.automation.user.dto;

import com.a4b.automation.department.entity.Department;
import com.a4b.automation.role.entity.Role;

import lombok.Data;

@Data
public class UserResponse {
  private  Long id;
  private String firstName;
  private String secondName;
  private String employeeCode;
  private String email;
  private String desigination;
  private Department department;
  private Role role;

}

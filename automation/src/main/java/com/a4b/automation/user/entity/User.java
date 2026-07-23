package com.a4b.automation.user.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.a4b.automation.department.entity.Department;
import com.a4b.automation.role.entity.Role;
import com.a4b.automation.user.enums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
@Table(name = "users")
public class User  implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String employeeCode;
    @Column(nullable = false)
    private String firstName;
    private String secondName;
    @Column(nullable = false)
    private String password;
    private String email;
    @Column(nullable = false,unique = true)
    private String phone;
    private String deginagation;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updaedAt;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return List.of(new SimpleGrantedAuthority(role.getName()));
    }
    @Override
    public String getUsername() {
       return email;
    }



}

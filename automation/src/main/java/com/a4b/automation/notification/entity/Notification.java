package com.a4b.automation.notification.entity;

import java.time.LocalDateTime;

import com.a4b.automation.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private User receiver;
    private String title;
    private String message;
    private boolean isRead;
    private LocalDateTime sendAt;

}

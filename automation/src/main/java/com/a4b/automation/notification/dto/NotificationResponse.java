package com.a4b.automation.notification.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class NotificationResponse {
private String title;
    private String message;
    private boolean isRead;
    private LocalDateTime sendAt;
}

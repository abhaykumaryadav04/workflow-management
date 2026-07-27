package com.a4b.automation.notification.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class NotificationDto {
    private String title;
    private String message;
    private LocalDateTime timeStamp;
    private String type;

}

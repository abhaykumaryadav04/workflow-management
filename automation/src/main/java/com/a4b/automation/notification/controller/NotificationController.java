package com.a4b.automation.notification.controller;

import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a4b.automation.notification.dto.NotificationDto;
import com.a4b.automation.notification.service.NotificationService;
import com.a4b.automation.user.entity.User;


@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
  
    @PostMapping("/test")
public String testNotification(){

   Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();

User user =
        (User) authentication.getPrincipal();

    notificationService.notifyUser(
            user,
            NotificationDto.builder()
                    .title("Testing")
                    .message("WebSocket is working!")
                    .type("INFO")
                    .timeStamp(LocalDateTime.now())
                    .build()
    );

    return "Notification Sent";
}

}

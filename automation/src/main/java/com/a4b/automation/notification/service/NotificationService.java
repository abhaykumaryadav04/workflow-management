package com.a4b.automation.notification.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.a4b.automation.notification.dto.NotificationDto;
import com.a4b.automation.notification.repo.NotificationRepo;
import com.a4b.automation.user.entity.User;

@Service
public class NotificationService {
  @Autowired
  private NotificationRepo notificationRepo;
  @Autowired
  private SimpMessagingTemplate messagingTemplate;
    

  public void notifyUser(User reciever,NotificationDto dto){
      messagingTemplate.convertAndSendToUser(reciever.getPhone(),"/queue/notifications",dto);    
  }
}

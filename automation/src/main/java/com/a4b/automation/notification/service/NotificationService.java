package com.a4b.automation.notification.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.a4b.automation.notification.dto.NotificationDto;
import com.a4b.automation.notification.entity.Notification;
import com.a4b.automation.notification.repo.NotificationRepo;
import com.a4b.automation.user.entity.User;

@Service
public class NotificationService {
 
  @Autowired
  private SimpMessagingTemplate messagingTemplate;
  @Autowired
  private NotificationRepo notificationRepo;
    

  public void notifyUser(User reciever,NotificationDto dto){
      messagingTemplate.convertAndSendToUser(reciever.getPhone(),"/queue/notifications",dto);
      Notification notification=Notification.builder().message(dto.getMessage()).isRead(false).title(dto.getTitle()).reciver(reciever).build();    
      notificationRepo.save(notification);
  }
  public List<Notification> getNotifications(User user){
    return notificationRepo.findByRecieverOrderBySendAtDisc(user);
  }
  public List<Notification> getUnreadMessage(User user){
    return notificationRepo.findByReceiverAndIsReadFalseOrderBySendAtDesc(user);
  }
  public Long getNumberOfUnreadMessage(User user){
    return notificationRepo.countByReceiverAndIsReadFalse(user);
  }
  public void markRead(User user){
    List<Notification> notifications=notificationRepo.findByReceiverAndIsReadFalseOrderBySendAtDesc(user);
    notifications.forEach(n->n.setRead(true));
    notificationRepo.saveAll(notifications);
  }
}

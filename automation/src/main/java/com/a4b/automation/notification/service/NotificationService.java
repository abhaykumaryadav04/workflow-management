package com.a4b.automation.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a4b.automation.notification.repo.NotificationRepo;

@Service
public class NotificationService {
  @Autowired
  private NotificationRepo notificationRepo;
    

  public void notifyUser(){
    
  }
}

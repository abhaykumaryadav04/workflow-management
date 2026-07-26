package com.a4b.automation.notification.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a4b.automation.notification.entity.Notification;
@Repository
public interface NotificationRepo extends JpaRepository<Notification,Long> {

    
} 
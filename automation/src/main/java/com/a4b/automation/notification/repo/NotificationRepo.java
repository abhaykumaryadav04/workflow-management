package com.a4b.automation.notification.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a4b.automation.notification.entity.Notification;
import com.a4b.automation.user.entity.User;
@Repository
public interface NotificationRepo extends JpaRepository<Notification,Long> {
List<Notification> findByRecieverOrderBySendAtDisc(User reciever);
List<Notification>  findByReceiverAndIsReadFalseOrderBySendAtDesc(User receiver);
Long countByReceiverAndIsReadFalse(User receiver);
    
} 
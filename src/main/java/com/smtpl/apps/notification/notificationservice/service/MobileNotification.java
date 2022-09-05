package com.smtpl.apps.notification.notificationservice.service;

import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;
import org.springframework.stereotype.Service;


public interface MobileNotification {
    public void sendNotification(String userId, PushNotificationPayload event);
}

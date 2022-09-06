package com.smtpl.apps.notification.notificationservice.service;

import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface NotificationModelService {

    public void save(String userId, PushNotificationPayload event,String eventId);

    public Map<String,Object> loadNotification(String userId);

    public void deleteNotification(String userId, String eventId);

}

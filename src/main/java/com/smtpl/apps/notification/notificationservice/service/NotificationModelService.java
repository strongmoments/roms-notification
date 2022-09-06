package com.smtpl.apps.notification.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface NotificationModelService {

    public void save(String userId, PushNotificationPayload event,String eventId) throws JsonProcessingException;

    public String loadNotification(String userId) throws JsonProcessingException;

    public void deleteNotification(String userId, String eventId);

}

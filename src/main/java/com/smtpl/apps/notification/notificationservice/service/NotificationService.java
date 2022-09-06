package com.smtpl.apps.notification.notificationservice.service;


import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;

public interface NotificationService {

    void sendNotification(String userId, PushNotificationPayload event,String eventId);
}

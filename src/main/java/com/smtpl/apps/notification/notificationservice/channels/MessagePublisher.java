package com.smtpl.apps.notification.notificationservice.channels;
import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;
public interface MessagePublisher {
    void publish(final PushNotificationPayload message);
}

package com.smtpl.apps.notification.notificationservice.channels;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;
import com.smtpl.apps.notification.notificationservice.service.MobileNotification;
import com.smtpl.apps.notification.notificationservice.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PushMessageSubscriber implements MessageListener{

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private MobileNotification mobileNotification;

    ObjectMapper objectMapper = new ObjectMapper();

    public void onMessage(final Message message, final byte[] pattern) {
        try {
            var notificationPayload = objectMapper.readValue(message.toString(), PushNotificationPayload.class);

            notificationService.sendNotification(notificationPayload.getUsername(),notificationPayload);
            mobileNotification.sendNotification(notificationPayload.getUsername(),notificationPayload);
        } catch (JsonProcessingException e) {
            log.error("unable to deserialize message ", e);
        }
    }
}

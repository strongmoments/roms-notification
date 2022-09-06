package com.smtpl.apps.notification.notificationservice.channels;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;

import com.smtpl.apps.notification.notificationservice.service.MobileNotification;
import com.smtpl.apps.notification.notificationservice.service.NotificationModelService;
import com.smtpl.apps.notification.notificationservice.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class PushMessageSubscriber implements MessageListener{

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationModelService notificationModelService;

    @Autowired
    private MobileNotification mobileNotification;

    ObjectMapper objectMapper = new ObjectMapper();

    public void onMessage(final Message message, final byte[] pattern) {
        try {
            var notificationPayload = objectMapper.readValue(message.toString(), PushNotificationPayload.class);
            String notificationEventId  = RandomStringUtils.randomAlphanumeric(12);
            notificationPayload.setEventId(notificationEventId);
//            List<Object> alldevice = (List<Object>) notificationPayload.getBody().get("devices");

            notificationService.sendNotification(notificationPayload.getUsername(),notificationPayload, notificationEventId);

            mobileNotification.sendNotification(notificationPayload.getUsername(),notificationPayload, notificationEventId);
            notificationModelService.save(notificationPayload.getUsername(),notificationPayload, notificationEventId);

        } catch (JsonProcessingException e) {
            log.error("unable to deserialize message ", e);
        }
    }
}

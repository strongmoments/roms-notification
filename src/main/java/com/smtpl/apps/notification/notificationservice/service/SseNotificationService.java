package com.smtpl.apps.notification.notificationservice.service;

import com.smtpl.apps.notification.notificationservice.mapper.EventMapper;
import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;
import com.smtpl.apps.notification.notificationservice.repository.EmitterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Primary
@AllArgsConstructor
@Slf4j
public class SseNotificationService implements NotificationService {

    private final EmitterRepository emitterRepository;
    private final EventMapper eventMapper;

    @Override
    public void sendNotification(String userId, PushNotificationPayload event) {
        if (event == null) {
            log.info("No server event to send to device.");
            return;
        }
        doSendNotification(userId, event);
    }

    private void doSendNotification(String userId, PushNotificationPayload event) {
        emitterRepository.get(userId).ifPresentOrElse(sseEmitter -> {
            try {
                log.info("Sending event: {} for member: {}", event, userId);
                sseEmitter.send(eventMapper.toSseEventBuilder(event));

            } catch (IOException | IllegalStateException e) {
                log.info("Error while sending event: {} for member: {} - exception: {}", event, userId, e);
                emitterRepository.remove(userId);
            }
        }, () -> log.info("No emitter for member {}", userId));
    }
}

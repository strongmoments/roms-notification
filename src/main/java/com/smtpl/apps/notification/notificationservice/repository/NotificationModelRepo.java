package com.smtpl.apps.notification.notificationservice.repository;

import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;
import com.smtpl.apps.notification.notificationservice.service.NotificationModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class NotificationModelRepo implements NotificationModelService {

    private final String hashReference= "notification";

    @Resource(name="redisTemplate2")          // 'redisTemplate' is defined as a Bean in AppConfig.java
    private HashOperations<String, String, Map<String,Object>> hashOperations;


    @Override
    public void save(String userId, PushNotificationPayload event, String eventId) {
        Map<String, Object> notificationData = new HashMap<>();
        notificationData.put(eventId,event);
        hashOperations.put(hashReference, userId,notificationData);

    }

    public Map<String,Object> loadNotification(String userId){
        return hashOperations.get(hashReference,userId);
    }

    @Override
    public void deleteNotification(String userId, String eventId) {

        Map<String,Object> allNotification = hashOperations.get(hashReference,userId);
        allNotification.remove(eventId);
        hashOperations.put(hashReference,userId,allNotification);

    }
}

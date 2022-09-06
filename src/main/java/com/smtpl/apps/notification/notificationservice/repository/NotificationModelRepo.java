package com.smtpl.apps.notification.notificationservice.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;
import com.smtpl.apps.notification.notificationservice.service.NotificationModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class NotificationModelRepo implements NotificationModelService {

    private final String hashReference= "notification";

    @Resource(name="redisTemplate2")          // 'redisTemplate' is defined as a Bean in AppConfig.java
    private HashOperations<String, String, Map<String, Object>> hashOperations;


    @Override
    public void save(String userId, PushNotificationPayload event, String eventId) throws JsonProcessingException {
        Map<String,Object> obj = new HashMap<>();

      obj =  hashOperations.get(hashReference,userId);
        obj.put(eventId,event);

        hashOperations.put(hashReference, userId,obj);

    }

    public String loadNotification(String userId) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();

        return obj.writeValueAsString(hashOperations.get(hashReference,userId));
    }

    @Override
    public void deleteNotification(String userId, String eventId) {
        Map<String,Object> obj = new HashMap<>();

        obj =  hashOperations.get(hashReference,userId);
        obj.remove(eventId);
        hashOperations.put(hashReference, userId,obj);



    }
}

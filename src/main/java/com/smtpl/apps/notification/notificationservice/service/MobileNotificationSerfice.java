package com.smtpl.apps.notification.notificationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Primary
@AllArgsConstructor
@Slf4j
public class MobileNotificationSerfice implements MobileNotification {

    @Override
    public void sendNotification(String userId, PushNotificationPayload event, String eventId) {

        try {
            String androidFcmKey="AAAA9NVCo9M:APA91bGg33I9LobWDncfvYtUdG-ruRA0GnZNq6eDL8-4w5BIsyuqrMzkE7YtCeff7bD1xsiJf2kIqc0ZBa-m3U85iyo2KO0WQGmd3v6LWdkH00G3G6cVWaASDEf-NxCiOimAqqK1x-Xt";
            String androidFcmUrl="https://fcm.googleapis.com/fcm/send";

            ArrayList<String>  deviceTokenArray = new ArrayList<String>();
            deviceTokenArray = (ArrayList<String>) event.getBody().get("devices");
            log.info("notification device token {}", deviceTokenArray);
            if(!deviceTokenArray.isEmpty()){

                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.set("Authorization", "key=" + androidFcmKey);
                httpHeaders.set("Content-Type", "application/json");
                Map<String, Object> msg = new HashMap<>();
                Map<String, Object> json = new HashMap<>();



                msg.put("title", event.getMessage());
                msg.put("body", event.getBody());
                msg.put("notificationType", event.getType());
                msg.put("eventId", eventId);

                json.put("data", msg);
                json.put("registration_ids", deviceTokenArray);


                ObjectMapper obj = new ObjectMapper();
                String s= obj.writeValueAsString(json);

                HttpEntity<String > httpEntity = new HttpEntity<String>(s,httpHeaders);
                String response = restTemplate.postForObject(androidFcmUrl,httpEntity,String.class);
            }else{

            }

            //
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

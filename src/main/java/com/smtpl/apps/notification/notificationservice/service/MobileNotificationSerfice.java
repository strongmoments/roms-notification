package com.smtpl.apps.notification.notificationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
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
@RequiredArgsConstructor
@Slf4j
public class MobileNotificationSerfice implements MobileNotification {

    //@Value("")
    @Value("${twilio.account_sid}")
    String ACCOUNT_SID;

   // @Value("twilio.auth_token")
   @Value("${twilio.auth_token}")
    String AUTH_TOKEN;

    @Value("${twilio.message_service_sid}")
    String MESSAGE_SERVICE_SID;

    @Override
    public void sendNotification(String userId, PushNotificationPayload event, String eventId) {

        try {
            String androidFcmKey="AAAA9NVCo9M:APA91bGg33I9LobWDncfvYtUdG-ruRA0GnZNq6eDL8-4w5BIsyuqrMzkE7YtCeff7bD1xsiJf2kIqc0ZBa-m3U85iyo2KO0WQGmd3v6LWdkH00G3G6cVWaASDEf-NxCiOimAqqK1x-Xt";
            String androidFcmUrl="https://fcm.googleapis.com/fcm/send";

            ArrayList<String>  deviceTokenArray = new ArrayList<String>();
            deviceTokenArray = (ArrayList<String>) event.getBody().get("devices");
           // deviceTokenArray.add("cLVFviTYR4ywKyMdoverRX:APA91bE--LlOwTFWfT8UaRFWXNqWGm6T5KAU9uxToQK16NsH4AquUhD7ttmd8RlrL0ptq0Tca7i37KzrakDDXXgW8DBkv5RJ13tRX6cC7Y9A6otf2_AJorI5W7ehQsj2Bc8Ezg4t92DJ");
            log.info("notification device token {}", deviceTokenArray);
            if(!deviceTokenArray.isEmpty()){

                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.set("Authorization", "key=" + androidFcmKey);
                httpHeaders.set("Content-Type", "application/json");
                Map<String, Object> msg = new HashMap<>();
                Map<String, Object> json = new HashMap<>();

                event.getBody().remove("profileImage");
                event.getBody().put("profileImage","");

                msg.put("title", event.getMessage());
                msg.put("body", event.getBody());
                msg.put("notificationType", event.getType());
                msg.put("eventId", eventId);

                json.put("data", msg);
               // json.put("to",deviceTokenArray.get(0));
               json.put("registration_ids", deviceTokenArray);


                ObjectMapper obj = new ObjectMapper();
                String s= obj.writeValueAsString(json);

                HttpEntity<String > httpEntity = new HttpEntity<String>(s,httpHeaders);
                String response = restTemplate.postForObject(androidFcmUrl,httpEntity,String.class);
                log.info(response);
            }else{

            }

            //
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String sendsms(PushNotificationPayload event) {
        try{
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(event.getUsername()),
                            MESSAGE_SERVICE_SID,
                            event.getMessage())
                    .create();
            log.info("SMS-SUCCESS "+message.getSid());
            log.info("SMS-detail "+message);
        return  message.getSid();
        }catch (Exception e){
            log.info("SMS-ERROR "+e.getMessage());
            return "fail";
        }

    }
}

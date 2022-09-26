package com.smtpl.apps.notification.notificationservice.contoller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;
import com.smtpl.apps.notification.notificationservice.channels.PushMessagePublisher;
import com.smtpl.apps.notification.notificationservice.service.EmitterService2;
import com.smtpl.apps.notification.notificationservice.service.MobileNotification;
import com.smtpl.apps.notification.notificationservice.service.NotificationModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

@RestController
@Slf4j
@CrossOrigin("*")
public class NotificationController {
    public static final String USER_ID = "userId";
    @Autowired
    private EmitterService2 emitterService;

    @Autowired
    private PushMessagePublisher pushMessagePublisher;

    @Autowired
    private NotificationModelService notificationModelService;

    @Autowired
    private MobileNotification mobileNotification;

    @GetMapping("/subscription/{userId}")
    public SseEmitter subsribe(@PathVariable(value ="userId") String memberId) {
        log.info("subscribed member with id {}", memberId);
        return emitterService.createEmitter(memberId);
    }

    @PostMapping("/sendNotification")
    public ResponseEntity<?> send(@RequestBody PushNotificationPayload request) {
        pushMessagePublisher.publish(request);
        return ResponseEntity.ok().body("message pushed to user " + request.getUsername());
    }

    @PostMapping("/sendsms")
    public ResponseEntity<?> sendsms(@RequestBody PushNotificationPayload request) {
        String response  = mobileNotification.sendsms(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/loadNotification")
    public ResponseEntity<?> load(@RequestBody PushNotificationPayload request) throws JsonProcessingException {
        String response = notificationModelService.loadNotification(request.getUsername());
        if(response.isEmpty()){
            response = "empty";
        }
        log.info("notification loaded for id {}", request.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/deleteNotification")
    public ResponseEntity<?> delete(@RequestBody PushNotificationPayload request) {
         notificationModelService.deleteNotification(request.getUsername(), request.getEventId());
        log.info("notification delete for id {} with event id {}", request.getUsername(), request.getEventId());
        return ResponseEntity.ok().body("Notification deleted successfully" + request.getUsername());
    }
}

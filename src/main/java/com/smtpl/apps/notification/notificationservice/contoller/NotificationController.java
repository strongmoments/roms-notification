package com.smtpl.apps.notification.notificationservice.contoller;
import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;
import com.smtpl.apps.notification.notificationservice.channels.PushMessagePublisher;
import com.smtpl.apps.notification.notificationservice.service.EmitterService2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
@RestController
@Slf4j
@CrossOrigin("*")
public class NotificationController {
    public static final String USER_ID = "userId";
    @Autowired
    private EmitterService2 emitterService;

    @Autowired
    private PushMessagePublisher pushMessagePublisher;

    @GetMapping("/subscription")
    public SseEmitter subsribe(@RequestHeader(name = USER_ID) String memberId) {
        log.info("subscribed member with id {}", memberId);
        return emitterService.createEmitter(memberId);
    }

    @PostMapping("/notification")
    public ResponseEntity<?> send(@RequestBody PushNotificationPayload request) {
        pushMessagePublisher.publish(request);

        return ResponseEntity.ok().body("message pushed to user " + request.getUsername());
    }
}

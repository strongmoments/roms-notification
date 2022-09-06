package com.smtpl.apps.notification.notificationservice.payload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PushNotificationPayload {
    private String username;
    private String from;
    private String message;
    private String type;
    private String eventId;
    private Map<String, Object> body;
}

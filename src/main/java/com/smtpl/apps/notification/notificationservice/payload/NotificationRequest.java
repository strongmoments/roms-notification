package com.smtpl.apps.notification.notificationservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    private String from;
    private String message;
    private String type;
    private Map<String, Object> body;

}

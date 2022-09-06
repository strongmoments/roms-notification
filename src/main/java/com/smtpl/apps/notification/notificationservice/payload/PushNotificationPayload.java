package com.smtpl.apps.notification.notificationservice.payload;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties
public class PushNotificationPayload /*extends JdkSerializationRedisSerializer*/ implements Serializable {
    private String username;
    private String from;
    private String message;
    private String type;
    private String eventId;
    private Map<String, Object> body;
}

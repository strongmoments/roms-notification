package com.smtpl.apps.notification.notificationservice.channels;
import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
@Service
public class PushMessagePublisher implements MessagePublisher{
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ChannelTopic topic;

    public PushMessagePublisher() {
    }

    public PushMessagePublisher(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public void publish(final PushNotificationPayload message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}

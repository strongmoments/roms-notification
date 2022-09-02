package com.smtpl.apps.notification.notificationservice.config;
import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;
import com.smtpl.apps.notification.notificationservice.channels.PushMessagePublisher;
import com.smtpl.apps.notification.notificationservice.channels.PushMessageSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import static org.apache.naming.SelectorContext.prefix;

@Configuration
public class RedisConfig {

    @Autowired
    private PushMessageSubscriber pushMessageSubscriber;
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(PushNotificationPayload.class));
        return template;
    }

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(pushMessageSubscriber);
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(), topic());
        return container;
    }

    @Bean
    PushMessagePublisher redisPublisher() {
        return new PushMessagePublisher(redisTemplate(), topic());
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("pubsub:queue");
    }
}

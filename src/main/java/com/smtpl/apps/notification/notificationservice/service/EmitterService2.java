package com.smtpl.apps.notification.notificationservice.service;

import com.smtpl.apps.notification.notificationservice.repository.EmitterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@Slf4j
public class EmitterService2 {

    private long eventsTimeout;

    @Autowired
    private EmitterRepository repository;

    public EmitterService2(@Value("${events.connection.timeout}") long eventsTimeout,
                          EmitterRepository repository) {
        this.eventsTimeout = eventsTimeout;
        this.repository = repository;
    }

    public SseEmitter createEmitter(String memberId) {
        SseEmitter emitter = new SseEmitter(eventsTimeout);
        emitter.onCompletion(() -> repository.remove(memberId));
        emitter.onTimeout(() -> repository.remove(memberId));
        emitter.onError(e -> {
            log.error("Create SseEmitter exception", e);
            repository.remove(memberId);
        });
        repository.addOrReplaceEmitter(memberId, emitter);
        return emitter;
    }


}

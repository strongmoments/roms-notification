package com.smtpl.apps.notification.notificationservice.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.Optional;

public interface EmitterRepository {

    void addOrReplaceEmitter(String memberId, SseEmitter emitter);

    void remove(String memberId);

    Optional<SseEmitter> get(String memberId);
}

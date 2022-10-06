package com.smtpl.apps.notification.notificationservice.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OnboardingFeedBack implements Serializable {
    private String id;
    private String step;
    private String completionProgress;
    private String rating;
    private String outOf;
    private String comments;
}

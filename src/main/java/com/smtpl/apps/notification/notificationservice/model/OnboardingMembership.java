package com.smtpl.apps.notification.notificationservice.model;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OnboardingMembership implements Serializable {

    private String id;
    private String step;
    private String completionProgress;
    private boolean longServiceLeaveScheme;
    private boolean redundancyScheme;
    private String redundancySchemeName;
    private String redundancySchemeMemberShipNo;
    private String longServiceSchemeName;
    private String longServiceSchemeMemberShipNo;

}

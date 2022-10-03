package com.smtpl.apps.notification.notificationservice.model;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OnboardingEmergencyContact implements Serializable {

    private String id;
    private String step;
    private String relationship;
    private String completionProgress;
    public String firstName;
    public String lastName;
    private String middleName;
    private String salut;
    public String email;
    public String phone;
    public String mobile;
    public AddressInput address;

}

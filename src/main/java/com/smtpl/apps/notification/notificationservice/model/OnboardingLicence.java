package com.smtpl.apps.notification.notificationservice.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OnboardingLicence implements Serializable {
    private String id;
    private String step;
    private String completionProgress;
    private String licenceImageId;
    private String licenceImageURL;
    private String licenceBackImageId;
    private String licenceBackImageURL;
    private String issuedIn;
    private String licenceNumber;
    private String expiryDate;
    private int declarationStatus;
    private  String signatureImageId;
    private  String signatureImageURL;

}

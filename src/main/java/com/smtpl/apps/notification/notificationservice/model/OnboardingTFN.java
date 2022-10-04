package com.smtpl.apps.notification.notificationservice.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OnboardingTFN implements Serializable {
    private String id;
    private Integer taxPayerType;
    private String TFNNumber;
    private boolean haveTFN;
    private boolean claimTaxfreeThreshold;
    private boolean haveanyDebt;
    private boolean tncAcceptance;


}
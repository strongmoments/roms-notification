package com.smtpl.apps.notification.notificationservice.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OnboardingSuperannuation implements Serializable {

    private String id;
    private boolean fillSuperFundNow;
    private String step;
    private String completionProgress;
    private boolean paidAsperMychoice;
    private String signatureId;
    private String signatureURL;
    private  String date ;
    private CurrentFundInput currentFund;
    private SelfFundInput selfManagedFund;
}

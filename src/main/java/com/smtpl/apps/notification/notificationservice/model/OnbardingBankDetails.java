package com.smtpl.apps.notification.notificationservice.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OnbardingBankDetails implements Serializable {

    private String id;
    private Integer secondaryAccount;
    private Integer payslipByEmail;
    private BankDetailsInput defaultBank;
    private BankDetailsInput secondaryBank;
    private String step;
    private String completionProgress;
}

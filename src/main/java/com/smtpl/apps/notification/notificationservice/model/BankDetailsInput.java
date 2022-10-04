package com.smtpl.apps.notification.notificationservice.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class BankDetailsInput implements Serializable {

    private String accountHolderName;
    private String accountNumber;
    private String bankName;
    private String bsbNumber;
    private Integer fixedAmount;

}

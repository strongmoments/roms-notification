package com.smtpl.apps.notification.notificationservice.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class EmployeePayLoad implements Serializable {

    String id;
    String employeeNo;
    String firstName;
    String lastName;
    String email;
    String registrationDate;
    String phone;
    String appliedOn;
    String orgId;
    int status;
}

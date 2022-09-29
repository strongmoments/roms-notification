package com.smtpl.apps.notification.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smtpl.apps.notification.notificationservice.model.EmployeePersonalDetails;

public interface OnboardingService {

     public String onboardPersonalDetails(EmployeePersonalDetails employeePersonalDetails,String onboardingType);

     public String loadOnboardedStatus(String userid,String onboardingType) throws JsonProcessingException;
}

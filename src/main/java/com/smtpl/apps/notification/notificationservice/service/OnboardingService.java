package com.smtpl.apps.notification.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smtpl.apps.notification.notificationservice.model.EmployeePersonalDetails;
import com.smtpl.apps.notification.notificationservice.model.OnboardingEmergencyContact;
import com.smtpl.apps.notification.notificationservice.model.OnboardingLicence;

public interface OnboardingService {

     public String onboardPersonalDetails(EmployeePersonalDetails employeePersonalDetails,String onboardingType);
     public String onboardPersonalDetails(OnboardingEmergencyContact employeePersonalDetails, String onboardingType);

     public String onboardLicence(OnboardingLicence employeePersonalDetails, String onboardingType);


     public String loadOnboardedStatus(String userid,String onboardingType) throws JsonProcessingException;

     public String getAllEmployeeOnboardingStatus() throws JsonProcessingException;
}

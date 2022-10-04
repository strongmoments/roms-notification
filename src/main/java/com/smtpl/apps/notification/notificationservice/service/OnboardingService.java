package com.smtpl.apps.notification.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smtpl.apps.notification.notificationservice.model.*;

public interface OnboardingService {

     public String onboardPersonalDetails(EmployeePersonalDetails employeePersonalDetails,String onboardingType);
     public String onboardPersonalDetails(OnboardingEmergencyContact employeePersonalDetails, String onboardingType);

     public String onboardLicence(OnboardingLicence employeePersonalDetails, String onboardingType);

     public String onboardBanking(OnbardingBankDetails employeePersonalDetails, String onboardingType);

     public String onboardTFN(OnboardingTFN employeePersonalDetails, String onboardingType);

     public String onboardSupreannution(OnboardingSuperannuation employeePersonalDetails, String onboardingType);


     public String loadOnboardedStatus(String userid,String onboardingType) throws JsonProcessingException;

     public String getAllEmployeeOnboardingStatus() throws JsonProcessingException;
}

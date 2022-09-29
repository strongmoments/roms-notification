package com.smtpl.apps.notification.notificationservice.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smtpl.apps.notification.notificationservice.model.EmployeePayLoad;
import com.smtpl.apps.notification.notificationservice.model.EmployeePersonalDetails;
import com.smtpl.apps.notification.notificationservice.service.OnboardingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OnboardingServiceRepo implements OnboardingService {

    private final String hashReference= "onborading";
    @Resource(name="redisTemplate2")          // 'redisTemplate' is defined as a Bean in AppConfig.java
    private HashOperations<String, String, Map<String, Object>> hashOperations;


    @Override
    public String onboardPersonalDetails(EmployeePersonalDetails employeePersonalDetails,String onboardingType) {

            Map<String,Object> obj = new HashMap<>();
            obj.put(employeePersonalDetails.getId(),employeePersonalDetails);
            hashOperations.put(hashReference, onboardingType,obj);
            return "success";

        }

    @Override
    public String loadOnboardedStatus(String userid, String onboardingType) throws JsonProcessingException {
        if(null != hashOperations.get(hashReference,onboardingType)){
            Map<String, Object>  data = (Map<String, Object>)hashOperations.get(hashReference,onboardingType);
            ObjectMapper obj = new ObjectMapper();
            return obj.writeValueAsString(data.get(userid));
        }
        return "";
    }
}

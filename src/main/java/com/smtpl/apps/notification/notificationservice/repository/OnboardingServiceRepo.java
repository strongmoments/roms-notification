package com.smtpl.apps.notification.notificationservice.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smtpl.apps.notification.notificationservice.model.*;
import com.smtpl.apps.notification.notificationservice.service.OnboardingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.Instant;
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
            obj = hashOperations.get(hashReference,employeePersonalDetails.getId());
            if(obj== null){
                obj = new HashMap<>();
            }
            if(obj.get("startdDate") == null){
                obj.put("startdDate",String.valueOf(Instant.now().toEpochMilli()));

            }
                obj.put("firstName",employeePersonalDetails.getFirstName());
                obj.put("lastName",employeePersonalDetails.getLastName());
                obj.put("id",employeePersonalDetails.getId());
            obj.put(onboardingType,employeePersonalDetails);
            hashOperations.put(hashReference,employeePersonalDetails.getId() ,obj);
            return "success";

        }

    @Override
    public String onboardPersonalDetails(OnboardingEmergencyContact employeePersonalDetails, String onboardingType) {
        Map<String,Object> obj = new HashMap<>();

        obj = hashOperations.get(hashReference,employeePersonalDetails.getId());
        if(obj== null){
            obj = new HashMap<>();
        }
        if(obj.get("startdDate") == null){
            obj.put("startdDate",String.valueOf(Instant.now().toEpochMilli()));
        }
        obj.put(onboardingType,employeePersonalDetails);
        hashOperations.put(hashReference,employeePersonalDetails.getId() ,obj);
        return "success";
    }

    @Override
    public String onboardLicence(OnboardingLicence employeePersonalDetails, String onboardingType) {
        Map<String,Object> obj = new HashMap<>();

        obj = hashOperations.get(hashReference,employeePersonalDetails.getId());
        if(obj== null){
            obj = new HashMap<>();
        }
        if(obj.get("startdDate") == null){
            obj.put("startdDate",String.valueOf(Instant.now().toEpochMilli()));
        }
        obj.put(onboardingType,employeePersonalDetails);
        hashOperations.put(hashReference,employeePersonalDetails.getId() ,obj);
        return "success";
    }
    @Override
    public String onboardBanking(OnbardingBankDetails employeePersonalDetails, String onboardingType) {
        Map<String,Object> obj = new HashMap<>();

        obj = hashOperations.get(hashReference,employeePersonalDetails.getId());
        if(obj== null){
            obj = new HashMap<>();
        }
        if(obj.get("startdDate") == null){
            obj.put("startdDate",String.valueOf(Instant.now().toEpochMilli()));
        }
        obj.put(onboardingType,employeePersonalDetails);
        hashOperations.put(hashReference,employeePersonalDetails.getId() ,obj);
        return "success";
    }

    @Override
    public String onboardTFN(OnboardingTFN employeePersonalDetails, String onboardingType) {
        Map<String,Object> obj = new HashMap<>();

        obj = hashOperations.get(hashReference,employeePersonalDetails.getId());
        if(obj== null){
            obj = new HashMap<>();
        }
        if(obj.get("startdDate") == null){
            obj.put("startdDate",String.valueOf(Instant.now().toEpochMilli()));
        }
        obj.put(onboardingType,employeePersonalDetails);
        hashOperations.put(hashReference,employeePersonalDetails.getId() ,obj);
        return "success";
    }

    @Override
    public String onboardSupreannution(OnboardingSuperannuation employeePersonalDetails, String onboardingType) {
        Map<String,Object> obj = new HashMap<>();

        obj = hashOperations.get(hashReference,employeePersonalDetails.getId());
        if(obj== null){
            obj = new HashMap<>();
        }
        if(obj.get("startdDate") == null){
            obj.put("startdDate",String.valueOf(Instant.now().toEpochMilli()));
        }
        obj.put(onboardingType,employeePersonalDetails);
        hashOperations.put(hashReference,employeePersonalDetails.getId() ,obj);
        return "success";
    }

    @Override
    public String onboardMemberShip(OnboardingMembership employeePersonalDetails, String onboardingType) {
        Map<String,Object> obj = new HashMap<>();

        obj = hashOperations.get(hashReference,employeePersonalDetails.getId());
        if(obj== null){
            obj = new HashMap<>();
        }
        if(obj.get("startdDate") == null){
            obj.put("startdDate",String.valueOf(Instant.now().toEpochMilli()));
        }
        obj.put(onboardingType,employeePersonalDetails);
        hashOperations.put(hashReference,employeePersonalDetails.getId() ,obj);
        return "success";
    }

    @Override
    public String onboardFeedBack(OnboardingFeedBack employeePersonalDetails, String onboardingType) {
        Map<String,Object> obj = new HashMap<>();

        obj = hashOperations.get(hashReference,employeePersonalDetails.getId());
        if(obj== null){
            obj = new HashMap<>();
        }
        if(obj.get("startdDate") == null){
            obj.put("startdDate",String.valueOf(Instant.now().toEpochMilli()));
        }
        obj.put(onboardingType,employeePersonalDetails);
        hashOperations.put(hashReference,employeePersonalDetails.getId() ,obj);
        return "success";
    }

    @Override
    public String onboarComplete(EmployeePayLoad employeePersonalDetails, String onboardingType) {
        Map<String,Object> obj = new HashMap<>();

        obj = hashOperations.get(hashReference,employeePersonalDetails.getId());
        if(obj.get("startdDate") == null){
            obj.put("startdDate",String.valueOf(Instant.now().toEpochMilli()));
        }
        if(obj.get("endDate") == null){
            obj.put("endDate",String.valueOf(Instant.now().toEpochMilli()));
        }

      //  obj.put(onboardingType,employeePersonalDetails);
        hashOperations.put(hashReference,employeePersonalDetails.getId() ,obj);
        return "success";
    }

    @Override
    public String setRegistratinDate(String empId, EmployeePayLoad employeePayLoad) {
       Map<String,Object> obj = new HashMap<>();
        if(null == hashOperations.get(hashReference,empId)){
            Map<String,Object> startDateEndDate = new HashMap<>();
            // startDateEndDate.put("startdDate", String.valueOf(Instant.now().toEpochMilli()));
            startDateEndDate.put("registrationDate", employeePayLoad.getRegistrationDate());
            startDateEndDate.put("firstName",employeePayLoad.getFirstName());
            startDateEndDate.put("lastName",employeePayLoad.getLastName());
            startDateEndDate.put("id",employeePayLoad.getId());
            startDateEndDate.put("empNo",employeePayLoad.getEmployeeNo());

            hashOperations.put(hashReference,empId ,startDateEndDate);
        }
        return  "";
    }

    @Override
    public String loadOnboardedStatus(String userid, String onboardingType) throws JsonProcessingException {
        if(null != hashOperations.get(hashReference,userid)){
            Map<String, Object>  data = (Map<String, Object>)hashOperations.get(hashReference,userid);
            ObjectMapper obj = new ObjectMapper();
            return obj.writeValueAsString(data.get(onboardingType));
        }
        return "";
    }

    @Override
    public String getOnboardStatus(String userid) throws JsonProcessingException {
        Map<String, Object>  data  =  hashOperations.get(hashReference, userid);
        ObjectMapper obj = new ObjectMapper();
        if(data == null){
            return  "";
        }else {
            return obj.writeValueAsString(data);
        }

    }

    @Override
    public String getAllEmployeeOnboardingStatus() throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        return obj.writeValueAsString(hashOperations.entries(hashReference));

    }
}

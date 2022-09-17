package com.smtpl.apps.notification.notificationservice.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smtpl.apps.notification.notificationservice.model.EmployeePayLoad;
import com.smtpl.apps.notification.notificationservice.service.AddTempEmployeeService;
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
public class AddTempEmployeeRepository implements AddTempEmployeeService {

    private final String hashReference= "addemployee";

    @Resource(name="redisTemplate2")          // 'redisTemplate' is defined as a Bean in AppConfig.java
    private HashOperations<String, String, Map<String, Object>> hashOperations;

    @Override
    public String save(EmployeePayLoad employeePayLoad) {

        Map<String,Object> obj = new HashMap<>();

        obj =  hashOperations.get(hashReference,employeePayLoad.getOrgId());
        if(obj == null){
            obj = new HashMap<>();
        }else {
            if(null != obj.get(employeePayLoad.getEmail())){
                return "already_requested";
            }
        }
        obj.put(employeePayLoad.getEmail(),employeePayLoad);

        hashOperations.put(hashReference, employeePayLoad.getOrgId(),obj);
        return "success";

    }

    @Override
    public String update(EmployeePayLoad employeePayLoad) {
        Map<String,Object> obj = new HashMap<>();

        obj =  hashOperations.get(hashReference,employeePayLoad.getOrgId());
        if(obj == null){
            return "user_not_found";
        }else {
                EmployeePayLoad payLoad =  (EmployeePayLoad) obj.get(employeePayLoad.getEmail());
                if(payLoad.getEmail().equalsIgnoreCase(employeePayLoad.getEmail())){
                    payLoad.setStatus(employeePayLoad.getStatus());
                    obj.put(employeePayLoad.getEmail(),payLoad);
                }else{
                    return "user_not_found";
                }


        }

        hashOperations.put(hashReference, employeePayLoad.getOrgId(),obj);
        return "success";
    }

    @Override
    public void delete(EmployeePayLoad employeePayLoad) {

    }

    @Override
    public String load(EmployeePayLoad employeePayLoad) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        Map<String,Object> mapObj = new HashMap<>();
        mapObj = hashOperations.get(hashReference,employeePayLoad.getOrgId());
        if(mapObj == null){
            return "empty";
        }

        return obj.writeValueAsString(mapObj);
    }
}

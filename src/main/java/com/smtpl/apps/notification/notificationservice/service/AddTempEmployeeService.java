package com.smtpl.apps.notification.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smtpl.apps.notification.notificationservice.model.EmployeePayLoad;

public interface AddTempEmployeeService {

    public String save(EmployeePayLoad employeePayLoad);

    public String update(EmployeePayLoad employeePayLoad);

    public void delete(EmployeePayLoad employeePayLoad);

    public String load(EmployeePayLoad employeePayLoad) throws JsonProcessingException;
}

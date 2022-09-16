package com.smtpl.apps.notification.notificationservice.contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smtpl.apps.notification.notificationservice.model.EmployeePayLoad;
import com.smtpl.apps.notification.notificationservice.payload.PushNotificationPayload;
import com.smtpl.apps.notification.notificationservice.service.AddTempEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin("*")
public class AddUserController {

    @Autowired
    private AddTempEmployeeService addTempEmployeeService;

    @PostMapping("/addUser")
    public ResponseEntity<?> save(@RequestBody EmployeePayLoad request) throws JsonProcessingException {
        log.info("adding user  {}", request.getEmail());
        String response = addTempEmployeeService.save(request);
        log.info("status {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/loadUser")
    public ResponseEntity<?> loadUser(@RequestBody EmployeePayLoad request) throws JsonProcessingException {
        log.info("loading users {}", request.getOrgId());
        String response = addTempEmployeeService.load(request);
        log.info("loaded ");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

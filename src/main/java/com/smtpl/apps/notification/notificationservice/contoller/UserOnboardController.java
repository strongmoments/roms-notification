package com.smtpl.apps.notification.notificationservice.contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smtpl.apps.notification.notificationservice.model.EmployeePayLoad;
import com.smtpl.apps.notification.notificationservice.model.EmployeePersonalDetails;
import com.smtpl.apps.notification.notificationservice.service.OnboardingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/v1/employee/onboard")
public class UserOnboardController {

    @Autowired
    private OnboardingService onboardingService;

    @PostMapping("/personal")
    public ResponseEntity<?> save(@RequestBody EmployeePersonalDetails request) throws JsonProcessingException {
        log.info("adding user  {}", request.getEmail());
        String response = onboardingService.onboardPersonalDetails(request, "personal");
        log.info("status {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/personal/{userId}")
    public ResponseEntity<?> loadPersonal(@PathVariable(value ="userId") String id) throws JsonProcessingException {
        String response = onboardingService.loadOnboardedStatus(id, "personal");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/allOnboardingStatus")
    public ResponseEntity<?> allOnboardingStatus() throws JsonProcessingException {
        String response = onboardingService.getAllEmployeeOnboardingStatus();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

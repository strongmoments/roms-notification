package com.smtpl.apps.notification.notificationservice.contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smtpl.apps.notification.notificationservice.model.*;
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

    @PostMapping("/emergencycontact")
    public ResponseEntity<?> save(@RequestBody OnboardingEmergencyContact request) throws JsonProcessingException {
        log.info("adding user  {}", request.getEmail());
        String response = onboardingService.onboardPersonalDetails(request, "emergency");
        log.info("status {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/licence")
    public ResponseEntity<?> save(@RequestBody OnboardingLicence request) throws JsonProcessingException {
        //log.info("adding user  {}", request.getEmail());
        String response = onboardingService.onboardLicence(request, "licence");
        log.info("status {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/banking")
    public ResponseEntity<?> save(@RequestBody OnbardingBankDetails request) throws JsonProcessingException {
        //log.info("adding user  {}", request.getEmail());
        String response = onboardingService.onboardBanking(request, "banking");
        log.info("status {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/tfn")
    public ResponseEntity<?> save(@RequestBody OnboardingTFN request) throws JsonProcessingException {
        //log.info("adding user  {}", request.getEmail());
        String response = onboardingService.onboardTFN(request, "tfn");
        log.info("status {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/superannuation")
    public ResponseEntity<?> save(@RequestBody OnboardingSuperannuation request) throws JsonProcessingException {
        //log.info("adding user  {}", request.getEmail());
        String response = onboardingService.onboardSupreannution(request, "superannuation");
        log.info("status {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/membership")
    public ResponseEntity<?> save(@RequestBody OnboardingMembership request) throws JsonProcessingException {
        //log.info("adding user  {}", request.getEmail());
        String response = onboardingService.onboardMemberShip(request, "membership");
        log.info("status {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/feedback")
    public ResponseEntity<?> save(@RequestBody OnboardingFeedBack request) throws JsonProcessingException {
        //log.info("adding user  {}", request.getEmail());
        String response = onboardingService.onboardFeedBack(request, "feedback");
        log.info("status {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/complete")
    public ResponseEntity<?> complete(@RequestBody EmployeePayLoad request) throws JsonProcessingException {
        //log.info("adding user  {}", request.getEmail());
        String response = onboardingService.onboarComplete(request, "complete");
        log.info("status {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping("/personal/{userId}")
    public ResponseEntity<?> loadPersonal(@PathVariable(value ="userId") String id) throws JsonProcessingException {
        String response = onboardingService.loadOnboardedStatus(id, "personal");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/licence/{userId}")
    public ResponseEntity<?> loademLicence(@PathVariable(value ="userId") String id) throws JsonProcessingException {
        String response = onboardingService.loadOnboardedStatus(id, "licence");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/emergency/{userId}")
    public ResponseEntity<?> loademergencycontact(@PathVariable(value ="userId") String id) throws JsonProcessingException {
        String response = onboardingService.loadOnboardedStatus(id, "emergency");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/banking/{userId}")
    public ResponseEntity<?> loadbanking(@PathVariable(value ="userId") String id) throws JsonProcessingException {
        String response = onboardingService.loadOnboardedStatus(id, "banking");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/tfn/{userId}")
    public ResponseEntity<?> loadTFN(@PathVariable(value ="userId") String id) throws JsonProcessingException {
        String response = onboardingService.loadOnboardedStatus(id, "tfn");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/superannuation/{userId}")
    public ResponseEntity<?> loadsuperannuation(@PathVariable(value ="userId") String id) throws JsonProcessingException {
        String response = onboardingService.loadOnboardedStatus(id, "superannuation");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/membership/{userId}")
    public ResponseEntity<?> loadmembership(@PathVariable(value ="userId") String id) throws JsonProcessingException {
        String response = onboardingService.loadOnboardedStatus(id, "membership");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/feedback/{userId}")
    public ResponseEntity<?> loadfeedback(@PathVariable(value ="userId") String id) throws JsonProcessingException {
        String response = onboardingService.loadOnboardedStatus(id, "feedback");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/status/{userId}")
    public ResponseEntity<?> getonboardStatus(@PathVariable(value ="userId") String id) throws JsonProcessingException {
        String response = onboardingService.getOnboardStatus(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/allOnboardingStatus")
    public ResponseEntity<?> allOnboardingStatus() throws JsonProcessingException {
        String response = onboardingService.getAllEmployeeOnboardingStatus();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

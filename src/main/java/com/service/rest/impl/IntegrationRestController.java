package com.service.rest.impl;

import java.util.*;
import com.service.domain.Employee;
import com.service.business.EmployeeBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import  com.service.domain.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/integrationservice")

public class IntegrationRestController {
    Logger logger  = LoggerFactory.getLogger(IntegrationRestController.class);

    @Autowired
    EmployeeBO employeeBO;


    @PostMapping("/jd-upload")
    public ResponseEntity<FileUploadResponse> jdUpload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("payload") String jsonstr)
            throws Exception {
        logger.info("Json payload for jdUpload :   {} ",jsonstr);
        FileUploadResponse response = this.employeeBO.jdUpload(multipartFile,jsonstr);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @PostMapping("/jd-submit")
    public ResponseEntity<FileUploadResponse>  jdSubmit(@RequestParam("file") MultipartFile multipartFile, @RequestParam("payload") String jsonstr)
            throws Exception {
        logger.info("Json payload for jdSubmit :   {} ",jsonstr);
        FileUploadResponse response = this.employeeBO.jdSubmit(multipartFile,jsonstr);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @GetMapping("/jd-list")
    public  @ResponseBody ResponseEntity<List<JDDetail>> jdList()
            throws Exception {
        List<JDDetail> list = this.employeeBO.jdList();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/talent-results")
    public  @ResponseBody ResponseEntity<List<TalentResult>> talentResult(@RequestParam("jd-id") String jdId, @RequestParam("jd-title") String jdTitle)
            throws Exception {
        logger.info("Json payload for jdId :   {} ",jdId);
        logger.info("Json payload for jdTitle :   {} ",jdTitle);
        List<TalentResult> list = this.employeeBO.talentResult(jdId,jdTitle);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/candidate-info")
    public  @ResponseBody ResponseEntity<Employee>  candidtateInfo(@RequestParam("candidateName") String candidateName, @RequestParam("jd-title") String jdTitle)
            throws Exception {
        logger.info("Json payload for candidateName :   {} ",candidateName);
        logger.info("Json payload for jdTitle :   {} ",jdTitle);
        Employee employee = this.employeeBO.candidtateInfo(candidateName,jdTitle);
        return ResponseEntity.ok(employee);
    }


//  Unused one

    @PostMapping("/uploadCV")
    public ResponseEntity<FileUploadResponse> uploadCV(@RequestParam("file") MultipartFile multipartFile, @RequestParam("payload") String jsonstr)
            throws Exception {
        logger.info("Json payload for CV :   {} ",jsonstr);
        FileUploadResponse response = this.employeeBO.uploadCV(multipartFile,jsonstr);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(value = "/employee")
    public @ResponseBody ResponseEntity<List<Employee>> getEmployees() {
        try {
            return ResponseEntity.ok(this.employeeBO.getEmployees());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/employee/{employeeId}")
    public @ResponseBody ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId) {
        try {
            return ResponseEntity.ok(this.employeeBO.getEmployeeById(employeeId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
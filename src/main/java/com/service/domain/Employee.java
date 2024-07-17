package com.service.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.*;
import org.springframework.core.io.Resource;

public class Employee {

    private long id;
    private String firstName;
    private String lastName;
    private String corporateTitle;
    private String location;
    private double percentage;
    List<String> skills = new ArrayList<String>();
    MultipartFile multipartFile;
    FileInputStream resource;
    byte[] bytes;

    public void setBytes(byte[]  bytes) {
        this.bytes = bytes;
    }
    public byte[]  getBytes() {
        return bytes;
    }

    public void setResource(FileInputStream resource) {
        this.resource = resource;
    }

    public FileInputStream getResource() {
        return resource;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setCorporateTitle(String corporateTitle) {
        this.corporateTitle = corporateTitle;
    }

    public String getCorporateTitle() {
        return corporateTitle;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

}
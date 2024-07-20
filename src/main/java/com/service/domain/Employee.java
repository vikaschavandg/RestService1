package com.service.domain;



public class Employee {

    private String employee_id;
    byte[] bytes;
    String cvPath;

    public void setCvPath(String cvPath) {
        this.cvPath = cvPath;
    }
    public String getCvPath() {
        return cvPath;
    }

    public void setBytes(byte[]  bytes) {
        this.bytes = bytes;
    }
    public byte[]  getBytes() {
        return bytes;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }
    public String getEmployee_id() {
        return employee_id;
    }


}
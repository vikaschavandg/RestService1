package com.service.domain;

public class TalentResult {

    private long id;
    private String firstName;
    private String lastName;
    private String corporateTitle;
    private String location;
    private double percentage;

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
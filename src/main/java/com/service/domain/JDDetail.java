package com.service.domain;

public class JDDetail {

    private int id;
    private String jobTitle;
    private String hiringManager;
    private String jdFilePath;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setHiringManager(String hiringManager) {
        this.hiringManager = hiringManager;
    }

    public String getHiringManager() {
        return hiringManager;
    }

    public void setJdFilePath(String jdFilePath) {
        this.jdFilePath = jdFilePath;
    }

    public String getJdFilePath() {
        return jdFilePath;
    }

}
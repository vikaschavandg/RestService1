package com.service.domain;
 
public class FileUploadResponse {
    private String fileName;
    private String downloadUri;
    private long fileSize;
    private String message;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
    public long getFileSize() {
        return fileSize;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
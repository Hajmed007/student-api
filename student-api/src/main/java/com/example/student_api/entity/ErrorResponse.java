package com.example.student_api.entity;

public class ErrorResponse {
    private String error;
    private int status;
    private String code;
    private String timestamp;
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    
}
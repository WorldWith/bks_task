package com.sample.app.web.bean;

public class ApiResponse {

    boolean success;
    Object data;
    String error;
    
    public boolean getSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }

    public String getError() {
        return error;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setError(String error) {
        this.error = error;
    }    
	
}

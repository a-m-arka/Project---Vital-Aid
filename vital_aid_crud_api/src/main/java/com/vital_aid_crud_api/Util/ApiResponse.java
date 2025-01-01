package com.vital_aid_crud_api.Util;

public class ApiResponse {
    private Boolean successStatus;
    private String message;

    public ApiResponse(String message, Boolean successStatus) {
        this.message = message;
        this.successStatus = successStatus;
    }


    public ApiResponse() {
    }


    public Boolean issuccessStatus() {
        return this.successStatus;
    }

    public Boolean getsuccessStatus() {
        return this.successStatus;
    }

    public void setsuccessStatus(Boolean successStatus) {
        this.successStatus = successStatus;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

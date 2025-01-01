package com.vital_aid_crud_api.Exception;

public class OtpExpirationException extends IllegalArgumentException {
    public OtpExpirationException(String message) {
        super(message);
    }

}

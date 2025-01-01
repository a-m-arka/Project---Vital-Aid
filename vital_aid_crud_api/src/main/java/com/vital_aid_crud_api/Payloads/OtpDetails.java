package com.vital_aid_crud_api.Payloads;

import java.time.LocalDateTime;



// for storing otp and expiry time
public class OtpDetails {
    private String otp;
    private LocalDateTime expiryTime;


    public OtpDetails() {
    }

    public OtpDetails(String otp, LocalDateTime expiryTime) {
        this.otp = otp;
        this.expiryTime = expiryTime;
    }

    


    public String getOtp() {
        return this.otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getExpiryTime() {
        return this.expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

}

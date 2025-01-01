package com.vital_aid_crud_api.service.OTPService;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.vital_aid_crud_api.Payloads.OtpDetails;

@Service
public class OTPService {
    public final Map<String, OtpDetails> otpStorage = new ConcurrentHashMap<>();
    // private final Map<String, String> validatedOtpStorage = new ConcurrentHashMap<>();

    public String generateOtp(String email, long expiryMinutes) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        otpStorage.put(email, new OtpDetails(otp, LocalDateTime.now().plusMinutes(expiryMinutes)));
        return otp;
    }

    public OtpDetails validateOtp(String otp) {
        return otpStorage.values().stream()
                .filter(details -> details.getOtp().equals(otp))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid OTP"));
    }

    public String getEmailForOtp(String validatedOtp) {
        return otpStorage.entrySet().stream()
                .filter(entry -> entry.getValue().getOtp().equals(validatedOtp)) 
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No email found for the validated OTP"));
    }

    public void clearOtp(String email) {
        otpStorage.remove(email);
    }

}

package com.example.ogbuehi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OtpService {
    private final Logger LOGGER = LoggerFactory.getLogger(OtpService.class);
    private final OtpGenerator otpGenerator;
    private final EmailService emailService;

    public void generateOtpAndSendToEmail(String key) {
        // generate otp
        Integer otpValue = otpGenerator.generateOtp(key);
        if (otpValue == -1) {
            LOGGER.error("OTP generator is not working...");
            return;
        }
        LOGGER.info("Generated OTP: {}", otpValue);
        EmailDetails details = new EmailDetails();
        details.setRecipient(key);
        details.setSubject("VENTRIPAY EMAIL VERIFICATION");
        details.setMsgBody("N/B: This code will be valid for 5 minutes.Your verification code is :" + otpValue);
        emailService.sendSimpleMail(details);

        validateOTP(key,otpValue);
    }

    /**
     * Method for validating provided OTP
     *
     * @param key       - provided key
     * @param otpNumber - provided OTP number
     */
    public void validateOTP(String key, Integer otpNumber) {
        // get OTP from cache
        Integer cacheOTP = otpGenerator.getOtpByKey(key);
        if (cacheOTP != null && cacheOTP.equals(otpNumber)) {
            otpGenerator.clearOTPFromCache(key);
        }
    }
}

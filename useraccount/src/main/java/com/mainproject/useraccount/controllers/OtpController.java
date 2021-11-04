package com.mainproject.useraccount.controllers;

import com.mainproject.useraccount.entity.Login;
import com.mainproject.useraccount.entity.Otp;
import com.mainproject.useraccount.entity.UserAuthentication;
import com.mainproject.useraccount.services.OtpMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins= "http://a277-203-81-240-173.ngrok.io/")
public class OtpController {

    @Autowired
    private OtpMailService otpMailService;


    @PostMapping("/user/generateOtp")
    public String generateOtp(@RequestBody UserAuthentication details) {
      return this.otpMailService.signUp(details);
    }

    @PostMapping("/user/validate")
    public String validateOtp(@RequestBody Otp givenOtpDetails) {
        return this.otpMailService.validateSignUp(givenOtpDetails);
    }


    @PostMapping("/user/forgot")
    public String forgotPass(@RequestBody Login forgotdetails)
    {
        return this.otpMailService.forgotPass(forgotdetails);
    }

    @PostMapping("/user/forgotValidate")
    public String validateForgotOtp(@RequestBody Otp forgotPassDetails){
        return this.otpMailService.validateForgotOtp(forgotPassDetails);
    }

}




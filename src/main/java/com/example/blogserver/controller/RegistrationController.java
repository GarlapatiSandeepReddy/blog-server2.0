/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.blogserver.controller;

import com.example.blogserver.model.RegistrationRequest;
import com.example.blogserver.service.LoginRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author garla
 */
@RestController
public class RegistrationController {
    
    @Autowired
    LoginRegService loginRegService;
    
    @PostMapping("/register")
    public ResponseEntity register(RegistrationRequest registerRequest){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(loginRegService.registerUser(registerRequest));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
}

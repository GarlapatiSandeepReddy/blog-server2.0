/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.blogserver.controller;

import com.example.blogserver.service.LoginRegService;
import com.example.blogserver.model.LoginRequest;
import com.example.blogserver.model.LoginResponse;
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
public class LoginController {
    
    @Autowired
    LoginRegService loginRegService;
    
    @PostMapping("/login")
    public ResponseEntity login(LoginRequest loginRequest){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(loginRegService.login(loginRequest));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

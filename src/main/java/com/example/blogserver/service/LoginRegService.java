/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.blogserver.service;

import com.example.blogserver.model.LoginRequest;
import com.example.blogserver.model.LoginResponse;
import com.example.blogserver.model.RegistrationRequest;
import com.example.blogserver.model.RegistrationResponse;
import com.example.blogserver.model.User;
import com.example.blogserver.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author garla
 */
@Service
public class LoginRegService {
    
    @Autowired
    UserRepository userRepository;
    
    public LoginResponse login(LoginRequest loginRequest){
        LoginResponse loginResponse = new LoginResponse();
        try{
            Optional<User> optional = userRepository.findById(loginRequest.getUserName());
            if(optional.isPresent()){
                User user = optional.get();
                if(user.getPassword().equals(loginRequest.getPassword())){
                    loginResponse.setStatus(200);
                    loginResponse.setMessage(user.getRole() + " LOGIN SUCCESSFUL");
                }else{
                    loginResponse.setStatus(400);
                    loginResponse.setMessage("Incorrect Password ");
                }
            }else{
                loginResponse.setStatus(400);
                loginResponse.setMessage("Cannot find user - " + loginRequest.getUserName());
            }
        }catch(Exception e){
            loginResponse.setStatus(500);
            loginResponse.setMessage(e.getMessage());
            return loginResponse;
        }
        return loginResponse;
    }
    
     public RegistrationResponse registerUser(RegistrationRequest registrationRequest){
        RegistrationResponse registrationResponse = new RegistrationResponse();
        try{
            if(!registrationRequest.isAdmin() && 
                (registrationRequest.getAdminCode() == null || registrationRequest.getAdminCode().equals(""))){
                User user = new User(registrationRequest.getFirstName(),
                                        registrationRequest.getLastName(),
                                        registrationRequest.getEmail(),
                                        registrationRequest.getUserName(),
                                        registrationRequest.getPassword(),
                                        "USER");
                userRepository.save(user);
                registrationResponse.setStatus(200);
                registrationResponse.setMessage("User Registration Successful");
            }else{
                registrationResponse.setStatus(400);
                registrationResponse.setMessage("Bad Request");
            }
        }catch(Exception e){
            registrationResponse.setStatus(500);
            registrationResponse.setMessage(e.getMessage());
            return registrationResponse;
        }
        return registrationResponse;
    }
     
    public RegistrationResponse registerAdmin(RegistrationRequest registrationRequest){
        RegistrationResponse registrationResponse = new RegistrationResponse();
        try{
            if(registrationRequest.isAdmin() && 
                (registrationRequest.getAdminCode() != null && registrationRequest.getAdminCode().equals("sample"))){
                User user = new User(registrationRequest.getFirstName(),
                                        registrationRequest.getLastName(),
                                        registrationRequest.getEmail(),
                                        registrationRequest.getUserName(),
                                        registrationRequest.getPassword(),
                                        "ADMIN");
                userRepository.save(user);
                registrationResponse.setStatus(200);
                registrationResponse.setMessage("Admin Registration Successful");
            }else{
                registrationResponse.setStatus(400);
                registrationResponse.setMessage("Bad Request");
            }
        }catch(Exception e){
            registrationResponse.setMessage(e.getMessage());
            return registrationResponse;
        }
        return registrationResponse;
    }
    
}

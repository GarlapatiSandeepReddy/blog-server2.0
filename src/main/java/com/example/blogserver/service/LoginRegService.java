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
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author garla
 */
@Service
public class LoginRegService {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    JwtService jwtService;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public LoginResponse login(LoginRequest loginRequest){
        LoginResponse loginResponse = new LoginResponse();
        try{
//            loginRequest.setPassword(bCryptPasswordEncoder.encode(loginRequest.getPassword()));
            Optional<User> optional = userRepository.findById(loginRequest.getUserName());
            if(optional.isPresent()){
                User user = optional.get();
//                System.out.println(loginRequest.getPassword());
//                loginRequest.setPassword(bCryptPasswordEncoder.(loginRequest.getPassword()));
//                System.out.println(loginRequest.getPassword());
//                System.out.println(user.getPassword());
                
                if(BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())){
                    String jwtToken = jwtService.generateToken(user.getUserName());
                    loginResponse.setJwtToken(jwtToken);
                    loginResponse.setStatus(200);
                    
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
                System.out.println(registrationRequest.getPassword());
                registrationRequest.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
                System.out.println(registrationRequest.getPassword());
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
                System.out.println(registrationRequest.getPassword());
                registrationRequest.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
                System.out.println(registrationRequest.getPassword());
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

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
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 *
 * @author garla
 */
@WebMvcTest(LoginRegService.class)
public class LoginRegServiceTest {
    
    @MockBean
    UserRepository userRepository;
    
    @InjectMocks
    LoginRegService loginRegService;
    
    private User user;
    
    private RegistrationRequest registrationRequest;
    
    private LoginRequest loginRequest;
    
    @BeforeEach
    public void setup(){
        
        MockitoAnnotations.openMocks(this);
        user = new User("Sandeep", "Garlapati", "garlapsr@gmail.com", "garlapsr", "garlapsr", null);
        
        registrationRequest = new RegistrationRequest("Sandeep", "Garlapati", "garlapsr@gmail.com", "garlapsr", "garlapsr", null, false);
    
        loginRequest = new LoginRequest("garlapsr", "garlapsr");
    }
    
    @Test
    public void registerUserSuccessful() throws Exception{
        
        user.setRole("USER");
        when(userRepository.save(user)).thenReturn(user);
        
        
        RegistrationResponse registrationResponse = loginRegService.registerUser(registrationRequest);
        
        verify(userRepository, times(1)).save(any(User.class));
        
        assertEquals("User Registration Successful", registrationResponse.getMessage());
        assertEquals(200, registrationResponse.getStatus());           
           
    }
    
    @Test
    public void registerUserFailedCase1() throws Exception{
        user.setRole("USER");
        when(userRepository.save(user)).thenReturn(user);
        registrationRequest.setAdmin(true);
        
        
        RegistrationResponse registrationResponse = loginRegService.registerUser(registrationRequest);
        
        verify(userRepository, times(0)).save(any(User.class));
        
        assertEquals("Bad Request", registrationResponse.getMessage());
        assertEquals(400, registrationResponse.getStatus());       
    }
    
    @Test
    public void registerUserFailedCase2() throws Exception{
        user.setRole("USER");
        when(userRepository.save(user)).thenReturn(user);
        registrationRequest.setAdminCode("sample");
        
        
        RegistrationResponse registrationResponse = loginRegService.registerUser(registrationRequest);
        
        verify(userRepository, times(0)).save(any(User.class));
        
        assertEquals("Bad Request", registrationResponse.getMessage());
        assertEquals(400, registrationResponse.getStatus());       
    }
    
    @Test
    public void registerUserFailedCase3() throws Exception{
        user.setRole("USER");
        when(userRepository.save(user)).thenThrow(new RuntimeException("Simulated RunTime Exception"));
        
        RegistrationResponse registrationResponse = loginRegService.registerUser(registrationRequest);
        
        verify(userRepository, times(1)).save(any(User.class));
        
        assertEquals("Simulated RunTime Exception", registrationResponse.getMessage());
    }
    
    @Test
    public void registerAdminSuccessful() throws Exception{
        
        user.setRole("ADMIN");
        when(userRepository.save(user)).thenReturn(user);
        registrationRequest.setAdmin(true);
        registrationRequest.setAdminCode("sample");
        
        
        RegistrationResponse registrationResponse = loginRegService.registerAdmin(registrationRequest);
        
        verify(userRepository, times(1)).save(any(User.class));
        
        assertEquals("Admin Registration Successful", registrationResponse.getMessage());
        assertEquals(200, registrationResponse.getStatus());           
           
    }
    
    @Test
    public void registerAdminFailedCase1() throws Exception{
        user.setRole("ADMIN");
        when(userRepository.save(user)).thenReturn(user);
        
        RegistrationResponse registrationResponse = loginRegService.registerAdmin(registrationRequest);
        
        verify(userRepository, times(0)).save(any(User.class));
        
        assertEquals("Bad Request", registrationResponse.getMessage());
        assertEquals(400, registrationResponse.getStatus());       
    }
    
    @Test
    public void registerAdminFailedCase2() throws Exception{
        user.setRole("ADMIN");
        when(userRepository.save(user)).thenReturn(user);
        registrationRequest.setAdmin(true);        
        
        RegistrationResponse registrationResponse = loginRegService.registerAdmin(registrationRequest);
        
        verify(userRepository, times(0)).save(any(User.class));
        
        assertEquals("Bad Request", registrationResponse.getMessage());
        assertEquals(400, registrationResponse.getStatus());       
    }
    
    @Test
    public void registerAdminFailedCase3() throws Exception{
        user.setRole("ADMIN");
        when(userRepository.save(user)).thenThrow(new RuntimeException("Simulated RunTime Exception"));
        registrationRequest.setAdmin(true);
        registrationRequest.setAdminCode("sample");
        
        RegistrationResponse registrationResponse = loginRegService.registerAdmin(registrationRequest);
        
        verify(userRepository, times(1)).save(any(User.class));
        
        assertEquals("Simulated RunTime Exception", registrationResponse.getMessage());
        assertEquals(500, registrationResponse.getStatus());
    }
    
    @Test
    public void registerAdminFailedCase4() throws Exception{
        user.setRole("ADMIN");
        when(userRepository.save(user)).thenReturn(user);
        registrationRequest.setAdmin(true);
        registrationRequest.setAdminCode("sample2");
        
        RegistrationResponse registrationResponse = loginRegService.registerAdmin(registrationRequest);
        
        verify(userRepository, times(0)).save(any(User.class));
        
        assertEquals("Bad Request", registrationResponse.getMessage());
        assertEquals(400, registrationResponse.getStatus());       
    }
    
    @Test
    public void loginSuccesfulCase1() throws Exception{
        user.setRole("ADMIN");
        when(userRepository.findById("garlapsr")).thenReturn(Optional.of(user));
        
        LoginResponse loginResponse = loginRegService.login(loginRequest);
        
        verify(userRepository, times(1)).findById(any(String.class));
        assertEquals(user.getPassword(), loginRequest.getPassword());
        assertEquals(200, loginResponse.getStatus());
        assertEquals("ADMIN LOGIN SUCCESSFUL", loginResponse.getMessage());
        
        
    }
    
    @Test
    public void loginSuccesfulCase2() throws Exception{
        user.setRole("USER");
        when(userRepository.findById("garlapsr")).thenReturn(Optional.of(user));
        
        LoginResponse loginResponse = loginRegService.login(loginRequest);
        
        verify(userRepository, times(1)).findById(any(String.class));
        assertEquals(user.getPassword(), loginRequest.getPassword());
        assertEquals(200, loginResponse.getStatus());
        assertEquals("USER LOGIN SUCCESSFUL", loginResponse.getMessage());
        
        
    }
    
    @Test
    public void loginFailureCase1() throws Exception{
        user.setRole("USER");
        when(userRepository.findById("garlapsr")).thenReturn(Optional.of(user));
        loginRequest.setPassword("garlapsr2");
        
        LoginResponse loginResponse = loginRegService.login(loginRequest);
        
        verify(userRepository, times(1)).findById(any(String.class));
        assertEquals(400, loginResponse.getStatus());
        assertEquals("Incorrect Password ", loginResponse.getMessage()); 
    }
    
    @Test
    public void loginFailureCase2() throws Exception{
        user.setRole("USER");
        when(userRepository.findById("garlapsr")).thenReturn(Optional.empty());
        
        LoginResponse loginResponse = loginRegService.login(loginRequest);
        
        verify(userRepository, times(1)).findById(any(String.class));
        assertEquals(400, loginResponse.getStatus());
        assertEquals("Cannot find user - " + loginRequest.getUserName(), loginResponse.getMessage()); 
    }
    
    @Test
    public void loginFailureCase3() throws Exception{
        user.setRole("USER");
        when(userRepository.findById("garlapsr")).thenThrow(new RuntimeException("Simulated Runtime Exception"));
        
        LoginResponse loginResponse = loginRegService.login(loginRequest);
        
        verify(userRepository, times(1)).findById(any(String.class));
        assertEquals(500, loginResponse.getStatus());
        assertEquals("Simulated Runtime Exception", loginResponse.getMessage()); 
    }
}

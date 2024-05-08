/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.blogserver.controller;

import com.example.blogserver.model.RegistrationRequest;
import com.example.blogserver.model.RegistrationResponse;
import com.example.blogserver.service.LoginRegService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author garla
 */
@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    LoginRegService loginRegService;
    
    @Autowired
    ObjectMapper objectMapper;
    
    
    @Test
    public void userRegistrationSuccessful() throws Exception{
        
        when(loginRegService.registerUser(any(RegistrationRequest.class))).thenReturn(new RegistrationResponse());
        
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/register")
                                    .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new RegistrationRequest())))
                .andExpect(status().isOk())
                .andReturn();
        
        assertEquals(200, result.getResponse().getStatus());
    }
    
    @Test
    public void userRegistrationFailure() throws Exception{
        
        when(loginRegService.registerUser(any(RegistrationRequest.class))).thenThrow(new RuntimeException("Simulated Runtime Exception"));
        
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                                    .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new RegistrationRequest())))
                .andExpect(status().isInternalServerError());
    }
    
     @Test
    public void adminRegistrationSuccessful() throws Exception{
        
        when(loginRegService.registerAdmin(any(RegistrationRequest.class))).thenReturn(new RegistrationResponse());
        
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/register/admin")
                                    .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new RegistrationRequest())))
                .andExpect(status().isOk())
                .andReturn();
        
        assertEquals(200, result.getResponse().getStatus());
    }
    
    @Test
    public void adminRegistrationFailure() throws Exception{
        
        when(loginRegService.registerAdmin(any(RegistrationRequest.class))).thenThrow(new RuntimeException("Simulated Runtime Exception"));
        
        mockMvc.perform(MockMvcRequestBuilders.post("/register/admin")
                                    .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new RegistrationRequest())))
                .andExpect(status().isInternalServerError());
    }
    
}

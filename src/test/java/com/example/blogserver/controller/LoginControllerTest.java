/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.blogserver.controller;

import com.example.blogserver.model.LoginRequest;
import com.example.blogserver.model.LoginResponse;
import com.example.blogserver.service.LoginRegService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import org.junit.jupiter.api.Test;
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
@WebMvcTest(LoginController.class)
public class LoginControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    LoginRegService loginRegService;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Test
    public void loginSuccessful() throws Exception{
        
        when(loginRegService.login(any(LoginRequest.class))).thenReturn(new LoginResponse());
        
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                                    .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new LoginRequest())))
                .andExpect(status().isOk())
                .andReturn();
        
        assertEquals(200, result.getResponse().getStatus());
    }
    
    @Test
    public void loginFailure() throws Exception{
        
        when(loginRegService.login(any(LoginRequest.class))).thenThrow(new RuntimeException("Simulated Runtime Exception"));
        
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                                    .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new LoginRequest())))
                .andExpect(status().isInternalServerError());
    }
}

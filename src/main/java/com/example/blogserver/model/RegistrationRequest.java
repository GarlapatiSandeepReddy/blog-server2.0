/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.blogserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author garla
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String adminCode;
    @JsonProperty
    private boolean isAdmin;
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.blogserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author garla
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegistrationRequest {
    @Getter@Setter private String firstName;
    @Getter@Setter private String lastName;
    @Getter@Setter private String userName;
    @Getter@Setter private String password;
    @Getter@Setter private String email;
}

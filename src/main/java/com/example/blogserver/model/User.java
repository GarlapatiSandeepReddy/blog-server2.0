/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.blogserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author garla
 */
@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Getter@Setter@Column(name = "firstname") private String firstName;
    @Getter@Setter@Column(name = "lastname") private String lastName;
    @Getter@Setter@Column(name = "email") private String email;
    @Getter@Setter@Id@Column(name = "username") private String userName;
    @Getter@Setter@Column(name = "password") private String password;
    @Getter@Setter@Column(name = "role") private String role;
    
}

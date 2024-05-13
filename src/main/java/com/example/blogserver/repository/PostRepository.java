/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.blogserver.repository;

import com.example.blogserver.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author garla
 */
public interface PostRepository extends JpaRepository<Post, Integer> {
    
}

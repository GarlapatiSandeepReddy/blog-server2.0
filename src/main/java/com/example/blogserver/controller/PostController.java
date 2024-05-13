/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.blogserver.controller;

import com.example.blogserver.model.Post;
import com.example.blogserver.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author garla
 */
@RestController
public class PostController {
    
    @Autowired
    PostService postService;
    
    @GetMapping("/posts")
    public ResponseEntity getPosts(){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPosts());
    }
    
    @PostMapping("/post")
    public ResponseEntity addPost(@RequestBody Post post){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(postService.addPost(post));
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @GetMapping("/post/{id}")
    public ResponseEntity getPost(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(id));
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @DeleteMapping("/post/{id}")
    public ResponseEntity deletePost(@PathVariable int id){
        try{
            postService.deletePost(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Post Succesfully Deleted");
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @PutMapping("/post")
    public ResponseEntity updatePost(@RequestBody Post post){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(postService.updatePost(post));
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    } 
}

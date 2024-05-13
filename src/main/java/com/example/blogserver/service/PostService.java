/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.blogserver.service;

import com.example.blogserver.repository.PostRepository;
import com.example.blogserver.model.Post;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author garla
 */
@Service
public class PostService {
    
    @Autowired
    PostRepository postRepository;
    
    public List<Post> getPosts() throws RuntimeException{
        return postRepository.findAll();
    }
    
    public Post addPost(Post post) throws RuntimeException{
        return postRepository.save(post);
    }
    
    public Post getPost(int id) throws RuntimeException{
        return postRepository.findById(id).get();
    }
    
    public void deletePost(int id) throws RuntimeException{
        postRepository.deleteById(id);
    }
    
    public Post updatePost(Post post) throws RuntimeException{
        return postRepository.save(post);
    }
}

package com.example.jjblues86.codefellowship.controllers;

import com.example.jjblues86.codefellowship.models.ApplicationUser;
import com.example.jjblues86.codefellowship.models.ApplicationUserRepository;
import com.example.jjblues86.codefellowship.models.CreatePost;
import com.example.jjblues86.codefellowship.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PostController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;

    @PostMapping("/postDetails")
        public RedirectView createUserProfile(long id, String color, String body, String gender, String hobby, String createdAt){
        ApplicationUser userProfile = applicationUserRepository.findById(id).get();

        CreatePost createProfile = new CreatePost(userProfile, color, body, gender, hobby, createdAt);
        postRepository.save(createProfile);
        return new RedirectView("/users/" + id);
        }
}
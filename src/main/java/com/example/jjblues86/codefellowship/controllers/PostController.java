package com.example.jjblues86.codefellowship.controllers;

import com.example.jjblues86.codefellowship.models.ApplicationUser;
import com.example.jjblues86.codefellowship.models.ApplicationUserRepository;
import com.example.jjblues86.codefellowship.models.CreatePost;
import com.example.jjblues86.codefellowship.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class PostController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;

    @PostMapping("/myProfile")
        public RedirectView createUserProfile(String color, String body, Principal p, Model m){
        ApplicationUser userProfile = applicationUserRepository.findByUsername(p.getName());

        CreatePost post = new CreatePost(userProfile, color, body);
        postRepository.save(post);
        return new RedirectView("myProfile");
        }

    @PostMapping("post")
        public RedirectView canFollowEachOther(long id, long followerId, long followingId){
        CreatePost follower = postRepository.findById(followerId).get();
        CreatePost following = postRepository.findById(followingId).get();
        follower.haveMoreFollows(following);
        postRepository.save(follower);

        return new RedirectView("/users/" + id);

    }

}

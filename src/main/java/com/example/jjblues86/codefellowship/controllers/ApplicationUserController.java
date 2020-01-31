package com.example.jjblues86.codefellowship.controllers;


import com.example.jjblues86.codefellowship.models.ApplicationUser;
import com.example.jjblues86.codefellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public RedirectView createNewApplication(String username, String password, String firstName, String lastName, String bio, String dob){

        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password), firstName, lastName, bio, dob);

        applicationUserRepository.save(newUser);

        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String showLoginForm(){

        return "login";
    }

    @GetMapping("/myProfile")
    public String showMyProfile(Principal p, Model m){
        ApplicationUser logedInUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("loggedInUser", logedInUser);
        m.addAttribute("userIdWeAreVisiting", logedInUser.id);
        m.addAttribute("principalTheAndroid", p.getName());

        return "myProfile";
    }

    @GetMapping("/users/{id}")
    public String showUserDetails(@PathVariable long id, Principal p, Model m){
        if(p != null) {
            ApplicationUser userWeAreVisiting = applicationUserRepository.findById(id).get();
            ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
            m.addAttribute("userWeAreVisiting", userWeAreVisiting);
            m.addAttribute("loggedInUser", loggedInUser);
        }
        return "postDetails";
    }

    @GetMapping("/users")
    public String getAllUsers(Principal p, Model m){
        if(p != null){
            m.addAttribute("users", p);
        }
        m.addAttribute("allUsers", applicationUserRepository.findAll());
        return "allUsers";
    }

    @GetMapping("/feed")
    public String getAUserFeed(Principal p, Model m){
        if(p != null){
            m.addAttribute("users", p);
        }
        m.addAttribute("loggedInUser", applicationUserRepository.findByUsername(p.getName()));
        return "feed";
    }

    @PostMapping("/follow/{id}")
    public RedirectView followFRequest(@PathVariable long id, Principal p){
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        ApplicationUser followUser = applicationUserRepository.findById(id).get();
        loggedInUser.haveMoreFollows(followUser);
        applicationUserRepository.save(loggedInUser);

        return new RedirectView("/users/" + id);
    }
}

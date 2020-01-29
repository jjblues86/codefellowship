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
    public RedirectView createNewApplication(String username, String password, String firstName, String lastName, String bio, int dob){

        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password), firstName, lastName, bio, dob);

        applicationUserRepository.save(newUser);

        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String showLoginForm(){

        return "login";
    }

    @GetMapping("/users/{id}")
    public String showUserDetails(@PathVariable long id, Principal p, Model m){
        ApplicationUser currentUser = applicationUserRepository.findById(id).get();
        m.addAttribute("usernameWeAreVisiting", currentUser.getUsername());
        m.addAttribute("userIdWeAreVisiting", currentUser.id);
        m.addAttribute("userWeAreVisiting", currentUser);
        m.addAttribute("principalTheAndroid", p.getName());

        return "postDetails";
    }
}

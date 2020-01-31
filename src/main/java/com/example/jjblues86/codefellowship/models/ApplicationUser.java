package com.example.jjblues86.codefellowship.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @OneToMany(mappedBy = "applicationUser")
    List<CreatePost> posts;




    @ManyToMany
    @JoinTable(name="follow",
            joinColumns = {@JoinColumn(name="follower")},
            inverseJoinColumns = {@JoinColumn(name = "following")})
    Set<ApplicationUser> usersThatIFollow;

    @ManyToMany(mappedBy = "usersThatIFollow")
    Set<ApplicationUser> usersThatFollowMe;

    String username;
    String password;
    String firstName;
    String lastName;
    String bio;
    String dob;



    public ApplicationUser(String username, String password, String firstName, String lastName, String bio, String dob){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.dob = dob;
    }
    public ApplicationUser(){};

    public List<CreatePost> getPosts(){
        return this.posts;
    }

    public Set<ApplicationUser> getUsersThatFollowMe() {
        return usersThatFollowMe;
    }

    public Set<ApplicationUser> getUsersThatIFollow() {
        return usersThatIFollow;
    }

    public void haveMoreFollows(ApplicationUser following){
        this.usersThatIFollow.add(following);
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }

    public String getDob() {
        return dob;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

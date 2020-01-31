package com.example.jjblues86.codefellowship.models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
public class CreatePost {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long Id;


    @ManyToOne
    ApplicationUser applicationUser;


    String color;
    String body;
    String createdAt;


    public CreatePost(ApplicationUser applicationUser, String color, String body) {
        this.applicationUser = applicationUser;
        this.color = color;
        this.body = body;
        //https://www.javatpoint.com/java-simpledateformat
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.createdAt = formatter.format(date);
    }

    public CreatePost(){}



    public long getId() {
        return Id;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public String getColor() {
        return color;
    }


    public void haveMoreFollows(CreatePost following) {

    }
}



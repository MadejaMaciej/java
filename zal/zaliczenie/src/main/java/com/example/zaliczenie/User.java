package com.example.zaliczenie;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private String id;

    private String username;
    private String email;
    private String password;
    private ArrayList<String> viewers = new ArrayList<>();
    private ArrayList<String> posts = new ArrayList<>();
    private ArrayList<LocalDateTime> timestamp = new ArrayList<>();         
    
    public void setUser(String uname, String mail, String pass) {
      this.username = uname;
      this.email = mail;
      this.password = pass;
      this.viewers.add(mail);
    }

    public void addViewer(String viewer){
      viewers.add(viewer);
    }

    public void addPost(String post){
      posts.add(post);
    }

    public void addTimestamp(){
      timestamp.add(java.time.LocalDateTime.now());
    }

    public ArrayList<String> getPosts(){
      return posts;
    }

    public ArrayList<LocalDateTime> getTimes(){
      return timestamp;
    }

    public ArrayList<String> getViewers(){
      return viewers;
    }

    public String getUsername(){
      return username;
    }

    public String getEmail(){
      return email;
    }

    public String getPassword(){
      return password;
    }

    @Override
    public String toString() {
      return String.format(
          "User[id=%s, username='%s', email='%s', password='%s']",
          id, username, email, password);
    }
}

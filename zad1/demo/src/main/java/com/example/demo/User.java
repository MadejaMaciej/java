package com.example.demo;

public class User {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String login;
    private String email;

    public void setUser(int uid, String uname, String username, int uage, String ulogin, String umail){
        this.id = uid;
        this.name = uname;
        this.surname = username;
        this.age = uage;
        this.login = ulogin;
        this.email = umail;
    }

    public void setID(int uid){
        this.id = uid;
    }

    public void setName(String data){
        this.name = data;
    }

    public void setSurname(String data){
        this.surname = data;
    }

    public void setLogin(String data){
        this.login = data;
    }

    public void setEmail(String data){
        this.email = data;
    }

    public void setAge(int data){
        this.age = data;
    }

    public int getID(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
    }

    public String getLogin(){
        return this.login;
    }

    public String getEmail(){
        return this.email;
    }

    public int getAge(){
        return this.age;
    }
}

package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/")
public class Controller {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayUser(){
        User user = new User();
        user.setUser(1, "Maciej", "Madejczyk", 22, "mmadejczyk", "mmadejczyk@onet.eu");

        return "Hello " + user.getName() + " " + user.getSurname() + ". Your age is: " + user.getAge() + ", login: " + user.getLogin() + ", email: " + user.getEmail();
    }

    @RequestMapping(value = "/lotto", method = RequestMethod.GET)
    public String displayLotto(){
        Lotto lotto = new Lotto();
        int scores[] = lotto.chooseNumbers();

        return "Hello! Your numbers are: " + scores[0] + " " + scores[1] + " " + scores[2] + " " + scores[3] + " " + scores[4] + " " + scores[5];
    }

    @RequestMapping(value = "/date", method = RequestMethod.GET)
    public String displayCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        return "Date is: " + dtf.format(now);
    }
}

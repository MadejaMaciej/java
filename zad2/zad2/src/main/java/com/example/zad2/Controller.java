package com.example.zad2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import java.lang.*;

@RestController
@RequestMapping(value = "/")
public class Controller {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String displayUser(@RequestParam String fname){
        return "Hello " + fname;
    }

    @RequestMapping(value = "/count-bmi", method = RequestMethod.GET)
    public String calculateBMI(@RequestParam int weight, @RequestParam int height){
        double bmi = weight/Math.pow(height, 2);
        bmi = bmi * 10000;
        return "You're BMI is: " + bmi + ". It is considered as: " + bmiConsideration(bmi);
    }

    @RequestMapping(value = "/login-user", method = RequestMethod.GET)
    public String loginUser(@RequestParam String login, @RequestParam String pass){
        User user = new User();
        if(user.getLogin().equals(login) && user.getPassword().equals(pass)){
            return "You have been logged in";
        }else{
            return "Bad data given";
        }
    }

    public String bmiConsideration(double bmi){
        if(bmi < 16){
            return "starvation";
        }else if(bmi < 16.99){
            return "emaciation";
        }else if(bmi < 18.49){
            return "underweight";
        }else if(bmi < 24.99){
            return "correct value";
        }else if(bmi < 29.99){
            return "overweight";
        }else if(bmi < 34.99){
            return "1 degree of obesity";
        }else if(bmi < 39.99){
            return "2 degree of obesity";
        }else{
            return "extreme obesity";
        }
    }
}

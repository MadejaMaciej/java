package zad1.lab3.zad1;

import zad1.lab3.zad1.classes.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/")
public class Controller {
    User user = new User();
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String addUs(@RequestParam String name){
        boolean check = user.checkIfEntered(name);
        if(check){
            String lastDate = user.checkUserLastEntrance(name);
            user.addUser(name);
            return "Hello " + name + " . Your last entrance was: " + lastDate;
        }
        user.addUser(name);
        return "Hello " + name + " for the first time in this world";
    }

    @RequestMapping(value = "/calculator", method = RequestMethod.GET)
    public String addNums(@RequestParam int a, @RequestParam int b){
        int score = a + b;
        return " " + score;
    }

    @RequestMapping(value = "/bmi", method = RequestMethod.GET)
    public String checkBMI(@RequestParam int weight, @RequestParam int height){
        BMI bmi = new BMI();
        double b = bmi.calculateBMI(weight, height);
        String an = bmi.bmiConsideration(b);
        return " BMI= " + b + " It is considered as: " + an;
    }
}

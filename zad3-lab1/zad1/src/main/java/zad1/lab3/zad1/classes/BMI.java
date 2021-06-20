package zad1.lab3.zad1.classes;

import java.lang.Math;

public class BMI {
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

    public double calculateBMI(int weight, int height){
        double bmi = weight/Math.pow(height, 2);
        bmi = bmi * 10000;
        return bmi;
    }
}

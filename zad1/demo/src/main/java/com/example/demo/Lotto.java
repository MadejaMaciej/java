package com.example.demo;

import java.util.Random;

public class Lotto {
   public int[] chooseNumbers(){
        int[] numbers = new int [6];
        Random r = new Random();
        int low = 1;
        int high = 49;
        boolean checker = false;
        int result;
        for(int i = 0; i < 6; i++){
            do{
                result = r.nextInt(high-low) + low;
                for(int j = 0; j < i; j++){
                    if(numbers[j] == result){
                        checker = true;
                    }
                }
            }while(checker);
            numbers[i] = result;
            checker = false;
        }
        return numbers;
   } 
}

package zad1.lab3.zad1.classes;

import java.util.ArrayList;
import java.util.Date;

public class User {
    ArrayList<String> users = new ArrayList<String>();
    ArrayList<String> dates = new ArrayList<String>();

    public void addUser(String username){
        users.add(username);
        Date date = new Date();
        dates.add(date.toString());
    }

    public boolean checkIfEntered(String username){
        for(int i = 0; i < users.size(); i++){
            if(username.equals(users.get(i))){
                return true;
            }
        }
        return false;
    }

    public String checkUserLastEntrance(String username) {
        for(int i = users.size() - 1; i > -1; i--){
            if(username.equals(users.get(i))){
                return dates.get(i);
            }
        }
        return "Error";
    }
}

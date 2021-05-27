package com.example.zaliczenie;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value = "/")
public class MainController{
    @Autowired
  	private UserRepository repository;

    public boolean saveNewUser(String name, String mail, String pass){
        //Wiem że hasła trzeba hashować oraz przydzielać tokeny użytkownikom do autoryzacji
        //lecz dla uproszczenia - duży nakład pracy w mojej pracy związanej zresztą z tą dziedziną informatyki 
        //tylko na trochę innym stacku - i w związku z tym bardzo małą ilością czasu  
        //Zdecydowałem się na nie hashowanie hasła oraz brak tokenów i uwierzytelnianie za pomocą email + hasła 
        //(użytkownik będzie wysyłał za każdym razem z każdym swoim "requestem" te dwie rzeczy i na podstawie tego będę sprawdzał czy mógł on wykonać tę działanie) 
		User user = new User();
		user.setUser(name, mail, pass);
		for (User users : repository.findAll()) {
			if(users.getEmail().equals(mail)){
				return false;
			}
		}
		repository.save(user);
		return true;
	}

    @RequestMapping(value = "/add-watcher", method = RequestMethod.POST)
    public boolean addWatcher(@RequestBody User userData){
        User user = authenticate(userData);
        if(user.getEmail() == null){
            return false;
        }
        ArrayList<String> views = userData.getViewers();
        for(int i = 0; i < views.size(); i ++){
            if(views.get(i).equals(userData.getUsername())){
                return false;
            }
        }
        user.addViewer(userData.getUsername());
        repository.save(user);
        return true;
    }

    @RequestMapping(value = "/add-post", method = RequestMethod.POST)
    public boolean addPost(@RequestBody User userData){
        User user = authenticate(userData);
        if(user.getEmail() == null){
            return false;
        }
        user.addPost(userData.getUsername());
        user.addTimestamp();
        repository.save(user);
        return true;
    }

    @RequestMapping(value = "/get-posts", method = RequestMethod.POST)
    public ArrayList<String> getPosts(@RequestBody User userData){
        User user = authenticate(userData);
        ArrayList<String> response = new ArrayList<>(); 
        if(user.getEmail() == null){
            return response;
        }
        ArrayList<String> combined = new ArrayList<>();
        ArrayList<String> viewers = user.getViewers();
        for(int i = 0; i < viewers.size(); i++){
            User nextUser = repository.findByEmail(viewers.get(i));
            ArrayList<String> userPosts = nextUser.getPosts();
            ArrayList<LocalDateTime> userTimes = nextUser.getTimes();
            for(int j = 0; j < nextUser.getPosts().size(); j++){
                combined.add(nextUser.getUsername());
                combined.add(userPosts.get(j));
                combined.add(userTimes.get(j).toString());
            }
        } 
        return combined;
    }

    @RequestMapping(value = "/check-if-valid", method = RequestMethod.POST)
    public boolean checkValid(@RequestBody User userData){
        User user = authenticate(userData);
        if(user.getEmail() == null){
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/users-not-viewers", method = RequestMethod.POST)
    public String[] listUsers(@RequestBody User userData){
        User userCheck = authenticate(userData);
        if(userCheck.getEmail() == null){
            String [] users = new String [0];
            return users;
        }
        int count = 0;

        ArrayList<String> views = userCheck.getViewers();

        for (User user : repository.findAll()) {
            boolean switcher = false;
            for(int i = 0; i < views.size(); i ++){
                if(views.get(i).equals(user.getEmail())){
                    switcher = true;
                }
            }
            if(switcher == false){
                count++;
            }
        }

        String [] users = new String [count];
         int secondCounter = 0;
        for (User user : repository.findAll()) {
            boolean switcher = false;
            for(int i = 0; i < views.size(); i ++){
                if(views.get(i).equals(user.getEmail())){
                    switcher = true;
                }
            }
            if(switcher == false){
                users[secondCounter] = user.getEmail();
                secondCounter++;
            }
        }

        return users;
    }

    @RequestMapping(value = "/register-user", method = RequestMethod.POST)
    public Boolean registerUser(@RequestBody User userData){
        boolean created = saveNewUser(userData.getUsername(), userData.getEmail(), userData.getPassword());
        return created;
    }

    @RequestMapping(value = "/login-user", method = RequestMethod.POST)
    public User loginUser(@RequestBody User userData){
        return authenticate(userData);
    }

    public User authenticate(User userData){
        User user = repository.findByEmail(userData.getEmail());
        User userBad = new User();
        userBad.setUser(null, null, null);
        if(user == null){    
            return userBad;
        }
        if(user.getPassword().equals(userData.getPassword())){
            return user;
        }

        return userBad;
    } 
}

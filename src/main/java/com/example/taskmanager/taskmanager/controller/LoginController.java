package com.example.taskmanager.taskmanager.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.taskmanager.taskmanager.model.Developer;
import com.example.taskmanager.taskmanager.model.Manager;
import com.example.taskmanager.taskmanager.model.Stakeholder;
import com.example.taskmanager.taskmanager.model.User;
import com.example.taskmanager.taskmanager.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

    @Autowired
    private UserService service;
    
    @GetMapping
    public String init(Model model){
        model.addAttribute("user",new User());
        return "index";
    }

    @PostMapping("/signUp")
    public String login(Model model, @ModelAttribute User user, @ModelAttribute Developer dev, @ModelAttribute Manager manager, @ModelAttribute Stakeholder stake){
        System.out.println(user.toString());
        List<User> objects = new ArrayList<User>();
        objects.add(dev);
        objects.add(manager);
        objects.add(stake);
        
        service.saveUser(objects);

        return "index";
    }

    @GetMapping("/signUp")
    public String signUp(Model model , @ModelAttribute User user){
        model.addAttribute("dev", new Developer());
        model.addAttribute("stake", new Stakeholder());
        model.addAttribute("manager", new Manager());
        return "/signUp/signUp";
    }

    @PostMapping("/login")
    public String postMethodName(@ModelAttribute User user) {
        //TODO: process POST request
        System.out.println(user.toString());
        User repoUser = service.findUser(user.getEmail(), user.getPassword());
        if(repoUser == null){
            return "index";
        }else{
            
            return "/mainMenu/menu";
        }
    }
    

}

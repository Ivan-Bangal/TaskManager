package com.example.taskmanager.taskmanager.controller;


import java.util.ArrayList;
import java.util.List;

import com.example.taskmanager.taskmanager.model.Developer;
import com.example.taskmanager.taskmanager.model.Manager;
import com.example.taskmanager.taskmanager.model.Stakeholder;
import com.example.taskmanager.taskmanager.model.User;
import com.example.taskmanager.taskmanager.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/signUpD")
    public String login(Model model,  @ModelAttribute Developer dev){
        List<User> objects = new ArrayList<User>();
        objects.add(dev);
        service.saveUser(objects);

        model.addAttribute("user", (User)dev);

        return "index";
    }

    @PostMapping("/signUpM")
    public String login(Model model, @ModelAttribute Manager manager){
        List<User> objects = new ArrayList<User>();
        objects.add(manager);

        
        service.saveUser(objects);

        model.addAttribute("user", (User)manager);
        return "index";
    }

    @PostMapping("/signUpS")
    public String login(Model model, @ModelAttribute Stakeholder stakeholder){
        List<User> objects = new ArrayList<User>();
        System.out.println(stakeholder);
        objects.add(stakeholder);
        
        service.saveUser(objects);

        model.addAttribute("user", (User)stakeholder);
        return "index";
    }


    @GetMapping("/signUp")
    public String signUp(Model model , @ModelAttribute User user){
        model.addAttribute("dev", new Developer());
        model.addAttribute("stakeholder", new Stakeholder());
        model.addAttribute("manager", new Manager());
        return "/signUp/signUp";
    }

    
    

}

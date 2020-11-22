package com.example.taskmanager.taskmanager.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
    public String init(Model model, @ModelAttribute User user){
        model.addAttribute("user",user);
        return "index";
    }

    @PostMapping("/signUp")
    public String login(Model model, @ModelAttribute User user){
        System.out.println(user.toString());

        service.saveUser(user);

        return "index";
    }

    @GetMapping("/signUp")
    public String signUp(Model model , @ModelAttribute User user){
        model.addAttribute("user", user);
        return "/signUp/signUp";
    }

    @PostMapping("/login")
    public String postMethodName(@ModelAttribute User user) {
        //TODO: process POST request
        User repoUser = service.findUser(user.getEmail(), user.getPassword());
        if(repoUser == null){
            return "index";
        }else
            return "/mainMenu/menu";
    }
    

}

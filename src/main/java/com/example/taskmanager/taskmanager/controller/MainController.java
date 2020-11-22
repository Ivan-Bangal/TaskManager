package com.example.taskmanager.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/main")
public class MainController {
    
    @GetMapping()
    public String getMethodName(@RequestParam String param) {
        return "";
    }
    

}

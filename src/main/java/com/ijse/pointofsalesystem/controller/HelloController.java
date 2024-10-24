package com.ijse.pointofsalesystem.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return " Helloooooo";
    }

    @PostMapping("/hello")
    public String postRequest() {
    
        
        return " post end point";
    } 
    
    
    
}

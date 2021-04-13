package com.csrohit.springsecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class AppController {
    @GetMapping("")
    public String getIndex(){
        return "index.html";
    }
}

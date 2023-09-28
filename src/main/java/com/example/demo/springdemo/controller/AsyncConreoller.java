package com.example.demo.springdemo.controller;


import com.example.demo.springdemo.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async")
public class AsyncConreoller {
    @Autowired
    AsyncService asyncService;
    @GetMapping("/test")
    public void testAsync(){
        asyncService.testAsync();
    }
}

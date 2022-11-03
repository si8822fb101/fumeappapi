package edu.ics499.fumeappapi.controllers;

import edu.ics499.fumeappapi.services.NodeListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private NodeListService service;

    @RequestMapping("/test")
    public String test(){
        return "Hello";
    }

    @RequestMapping("/sendFile")
    public String uploadFile(){
        return "Hello";
    }

    @RequestMapping("/sendMessage")
    public String sendMessage(){
        return "Hello";
    }

    @RequestMapping("/receiveMessage")
    public String receiveMessage(){
        return "Hello";
    }

}

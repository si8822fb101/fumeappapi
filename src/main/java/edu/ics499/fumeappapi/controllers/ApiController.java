package edu.ics499.fumeappapi.controllers;

import edu.ics499.fumeappapi.domain.Node;
import edu.ics499.fumeappapi.domain.User;
import edu.ics499.fumeappapi.requests.LoginForm;
import edu.ics499.fumeappapi.services.NodeListService;
import edu.ics499.fumeappapi.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/test")
    public String test(){
        return "Hello";
    }

    @PostMapping("/registerUser")
    @ResponseBody
    public Node registerUser(@RequestBody LoginForm loginForm){
        try{
            return transactionService.createAccount(loginForm.getUsername(), loginForm.getPin());
        }catch(Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Foo Not Found", e);
        }
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

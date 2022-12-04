package edu.ics499.fumeappapi.controllers;

import edu.ics499.fumeappapi.domain.Node;
import edu.ics499.fumeappapi.requests.LoginForm;
import edu.ics499.fumeappapi.requests.LogoutForm;
import edu.ics499.fumeappapi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:1212", maxAge = 3600)
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/userLogon")
    public ResponseEntity userLogon(@RequestBody LoginForm login){
        try{
            var success = transactionService.userLogon(login.getUsername(), login.getPin());
            if(success){
                ResponseEntity.ok().build();
                return ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body("User Logon Completed  \n  Welcome to Fume!");
            }else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Invalid credentials");
            }
        }catch(Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Login failed with error", e);
        }
    }

    @PostMapping("/userLogout")
    public ResponseEntity userLogout(@RequestBody LogoutForm form){
        try{
            var success = transactionService.userLogout(form.getUsername());
            if(success){
                ResponseEntity.ok().build();
                return ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body("User Logout Successful");
            }else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Invalid credentials");
            }
        }catch(Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Login failed with error", e);
        }
    }
}

package edu.ics499.fumeappapi.controllers;

import edu.ics499.fumeappapi.domain.Message;
import edu.ics499.fumeappapi.domain.Node;
import edu.ics499.fumeappapi.requests.NewChatForm;
import edu.ics499.fumeappapi.requests.RecieveMessageForm;
import edu.ics499.fumeappapi.requests.SendMessageForm;
import edu.ics499.fumeappapi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:1212", maxAge = 3600)
@RestController
@RequestMapping("/messaging")
public class MessagingController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity sendMessage(@RequestBody SendMessageForm message){
        try{
            transactionService.sendMessage(message);
            ResponseEntity.ok().build();
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body("Message sent");
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/receive")
    @ResponseBody
    public ArrayList<Message> receiveMessages(){
        try{
            return transactionService.receiveMessages();
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/newChat")
    @ResponseBody
    public boolean receiveMessages(@RequestBody NewChatForm form){
        try{
            return transactionService.newChat(form);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}

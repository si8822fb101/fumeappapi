package edu.ics499.fumeappapi.controllers;

import edu.ics499.fumeappapi.domain.Message;
import edu.ics499.fumeappapi.requests.NewChatForm;
import edu.ics499.fumeappapi.requests.SendMessageForm;
import edu.ics499.fumeappapi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;


@Controller
public class FileUploadController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity sendFile(@RequestBody SendMessageForm file){
        try{
            transactionService.sendMessage(file);
            ResponseEntity.ok().build();
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body("File sent");
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
    public boolean receiveFile(@RequestBody NewChatForm form){
        try{
            return transactionService.newChat(form);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }



}

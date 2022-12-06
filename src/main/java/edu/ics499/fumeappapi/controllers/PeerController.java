package edu.ics499.fumeappapi.controllers;

import edu.ics499.fumeappapi.domain.Block;
import edu.ics499.fumeappapi.domain.Node;
import edu.ics499.fumeappapi.requests.LoginForm;
import edu.ics499.fumeappapi.requests.LogoutForm;
import edu.ics499.fumeappapi.services.NodeListService;
import edu.ics499.fumeappapi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:1212", maxAge = 3600)
@RestController
@RequestMapping("/peer")
public class PeerController {

    @Autowired
    private NodeListService nodeListService;

    @PostMapping("/syncBlockChain")
    public Block syncBlockChain(@RequestBody Block block){
        try{
            return nodeListService.syncBlockchain(block);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/getBlockchain")
    public Block syncBlockChain(){
        try{
            return nodeListService.getBlocks();
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

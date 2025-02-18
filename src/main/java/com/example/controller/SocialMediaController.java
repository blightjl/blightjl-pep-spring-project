package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    // injecting the relevant services that may be required
   
    AccountService accountService;

    MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> postAccount(@RequestBody Account account) {
        if (false){
            return ResponseEntity.status(400).body(account);
        }
        {
            // return ResponseEntity.status(409).body(account);    
        }
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account, @RequestParam String password) {
        if (false){
            return ResponseEntity.status(401).body(account);
        }
        {
            // return ResponseEntity.status(409).body(account);    
        }
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message) {
        if (false){
            return ResponseEntity.status(400).body(message);
        }
        {
            // return ResponseEntity.status(409).body(account);    
        }
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages() {
        return ResponseEntity.status(HttpStatus.OK).body();
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessage(@PathVariable String message_id) {
        if (false){
            return ResponseEntity.status(OK).body();
        }
        {
            // return ResponseEntity.status(409).body(account);    
        }
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessagesByAccount(@PathVariable String account_id) {
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }

    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Message> updateMessage(@PathVariable String message_id, @RequestBody Message message) {
        if (false){
            return ResponseEntity.status(400).body(message);
        }
        {
            // return ResponseEntity.status(409).body(account);    
        }
        return ResponseEntity.status(HttpStatus.OK).body(num rows updated);
    }

    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Message> deleteMessage(@PathVariable String message_id) {
        if (false){
            return ResponseEntity.status(200).body();
        }
        {
            // return ResponseEntity.status(409).body(account);    
        }
        return ResponseEntity.status(200).body(num of rows deleted);
    }
}

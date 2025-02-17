package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Void> postAccount() {
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginAccount() {
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> postMessage() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessage() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessagesByAccount() {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Message> updateMessage() {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Message> deleteMessage() {
        return ResponseEntity.noContent().build();
    }
}

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
 * TODO: You will need to write your own endpoints and handlers for your
 * controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use
 * the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations.
 * You should
 * refer to prior mini-project labs and lecture materials for guidance on how a
 * controller may be built.
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
        if (this.accountService.usernameExists(account.getUsername())) {
            return ResponseEntity.status(409).body(account);

        }
        Account createdAccount = this.accountService.registerAccount(account);
        if (createdAccount == null) {
            return ResponseEntity.status(400).body(account);
        }
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account) {
        Account loggedAccount = accountService.logIntoAccount(account);
        if (loggedAccount == null) {
            return ResponseEntity.status(401).body(account);

        }
        return ResponseEntity.status(HttpStatus.OK).body(loggedAccount);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message) {
        int posted_by = message.getPostedBy();
        if (this.accountService.accountExists(posted_by)) {

            Message addedMessage = this.messageService.addMessage(message);
            if (addedMessage != null) {
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }
        }
        return ResponseEntity.status(400).body(message);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages() {
        List<Message> loMessages = this.messageService.getAllMessages();
        return ResponseEntity.status(HttpStatus.OK).body(loMessages);
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessage(@PathVariable String message_id) {
        Optional<Message> message = this.messageService.getMessageByID(Integer.parseInt(message_id));
        if (!message.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(message.get());
    }

    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessagesByAccount(@PathVariable String account_id) {
        List<Message> loMessagesByAccountID = this.messageService.getMessagesByAccountID(Integer.parseInt(account_id));
        return ResponseEntity.status(HttpStatus.OK).body(loMessagesByAccountID);
    }

    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable String message_id, @RequestBody Message message) {
        if (this.messageService.updateMessage(Integer.parseInt(message_id), message)) {
            return ResponseEntity.status(HttpStatus.OK).body(1);
        } else {
            return ResponseEntity.status(400).body(0);
        }
    }

    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable String message_id) {
        try {
            this.messageService.deleteMessage(Integer.parseInt(message_id));
            return ResponseEntity.status(200).body(1);
        } catch (Exception e) {
            return ResponseEntity.status(200).build();
        }

    }
}

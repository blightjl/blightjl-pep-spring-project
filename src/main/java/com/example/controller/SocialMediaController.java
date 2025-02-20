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
    // Injecting the Account and Message services that are required via Autowired

    AccountService accountService;
    MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    /**
     * The Post mapping at endpoint /register using the accountService to attempt a registering of an account.
     * @param account the account to be registered
     * @return the ResponseEntity<Account> object that returns status {OK if success, 409 if username exists, 400 or other errors} and the body to be registered
     */
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

    /**
     * The Post mapping at endpoint /login using the accountService to attempt a login with an account.
     * @param account the account with username and password to be used for logging in
     * @return the ResponseEntity<Account> object that returns status {OK if successful, 401 for failure} and the body of logged in account if successful else the account sent
     */
    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account) {
        Account loggedAccount = accountService.logIntoAccount(account);
        if (loggedAccount == null) {
            return ResponseEntity.status(401).body(account);
        }
        return ResponseEntity.status(HttpStatus.OK).body(loggedAccount);
    }

    /**
     * The Post mapping at endpoint /messages using the messageService to attempt to post a message
     * @param message the message to be posted
     * @return the ResponseEntity<Message> object that returns status {OK for failure, 400 for failure} and the body of message to be posted
     */
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

    /**
     * The Get mapping at endpoint /messages using the messageService to retrieve all messages
     * @return the ResponseEntity<List<Message>> object that returns status {OK} and the list of messages returned
     */
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages() {
        List<Message> loMessages = this.messageService.getAllMessages();
        return ResponseEntity.status(HttpStatus.OK).body(loMessages);
    }

    /**
     * The Get mapping at endpoint /messages/{message_id} using the messageService to retrieve a message by message id
     * @param message_id the message id to look up message by
     * @return the ResponseEntity<Message> object that returns status {OK} and the list of messages returned
     */
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessage(@PathVariable String message_id) {
        Optional<Message> message = this.messageService.getMessageByID(Integer.parseInt(message_id));
        if (message.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(message.get());
    }

    /**
     * The Get mapping at endpoint /messages/{account_id}/messages using the messageService to retrieve all message by account id
     * @param account_id the account id to look up messages from
     * @return the ResponseEntity<Account> object that returns status {OK} and the list of messages returned
     */
    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessagesByAccount(@PathVariable String account_id) {
        List<Message> loMessagesByAccountID = this.messageService.getMessagesByAccountID(Integer.parseInt(account_id));
        return ResponseEntity.status(HttpStatus.OK).body(loMessagesByAccountID);
    }

    /**
     * The Patch mapping at endpoint /messages/{message_id} using the messageService to update a message
     * @param message_id the message id of the message to be udpated
     * @param message the contents to replace the current message with
     * @return the ResponseEntity<Integer> object that returns status {OK if successful, 400 if failure} and in the body either 1 if successful, 0 otherwise
     */
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable String message_id, @RequestBody Message message) {
        if (this.messageService.updateMessage(Integer.parseInt(message_id), message)) {
            return ResponseEntity.status(HttpStatus.OK).body(1);
        } else {
            return ResponseEntity.status(400).body(0);
        }
    }

    /**
     * The Delete mapping at endpoint /messages/{message_id} using the messageService to delete a message
     * @param message_id the message id of the message to be deleted
     * @return the ResponseEntity<Integer> object that returns status {OK} and in the body either 1 if successful, nothing otherwise
     */
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable String message_id) {
        if (this.messageService.deleteMessage(Integer.parseInt(message_id))) {
            return ResponseEntity.status(200).body(1);
        }
        return ResponseEntity.status(200).build();
    }
}

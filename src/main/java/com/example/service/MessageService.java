package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

/** 
 * The Message Service that calls on the Message Repository to use business logic for the app.
 */
@Service
public class MessageService {
    private MessageRepository messageRepository;
    @Autowired
    private AccountService accountService;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * This method conducts input checks on message to be added such that it is in the accepted
     * range and the account id that it is associated with exists in the database. If the message
     * passes these checks the method calls the insert message onto the message repository.
     *
     * @param message the message to be added to the database
     * @return the added message
     */
    public Message addMessage(Message message) {
        String messageContent = message.getMessageText();
        if (messageContent.length() == 0 || messageContent.length() >= 255) {
            return null;
        }
        int posted_by = message.getPostedBy();
        if (!this.accountService.accountExists(posted_by)) {
            return null;
        }
        return this.messageRepository.save(message);
    }

    /**
     * This method calls the findAll() method of the message repository to retrieve all messages.
     *
     * @return the list of messages returned by the message repository.
     */
    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }

    /**
     * This method calls the findById() to retrieve the message that is associated with the message id.
     *
     * @param message the message to be added to the message repository
     * @return the added message if successful, null otherwise
     */
    public Optional<Message> getMessageByID(int message_id) {
        return this.messageRepository.findById(message_id);
    }

    /**
     * This makes sure that the message text that is contained in the new message object is 
     * within the acceptable range and that the message to be updated exists. If it passes
     * these checks, message is saved on the message repository.
     *
     * @param message_id the id of the message to be updated
     * @param message the message object that contains the new message replacement
     * @return true if successful update, false otherwise
     */
    public boolean updateMessage(int message_id, Message message) {
        String messageContent = message.getMessageText();

        if (messageContent.length() == 0 || messageContent.length() >= 255) {
            return false;
        }
        Optional<Message> optionalMessage = this.getMessageByID(message_id);
        if (optionalMessage.isEmpty()) {
            return false;
        }
        Message updatedMessage = message;
        updatedMessage.setMessageId(message_id);
        this.messageRepository.save(updatedMessage);
        return true;
    }

    /**
     * This method calls the deleteById() on the message repository to delete a message identified by
     * the message id parameter.
     *
     * @param message_id the message id of the message to be deleted
     * @return true if the deleted message is successful, false otherwise
     */
    public boolean deleteMessage(int message_id) {
        if (!this.messageRepository.existsById(message_id)) {
            return false;
        }
        this.messageRepository.deleteById(message_id);
        return true;
    }

    /**
     * This method calls the findAllByPostedBy() on the message repository to retrieve all
     * messages posted by the account id.
     *
     * @param account_id the account it to filter the list of messages by
     * @return a list of messages made by the account id
     */
    public List<Message> getMessagesByAccountID(int account_id) {
        return this.messageRepository.findAllByPostedBy(account_id);
    }
}

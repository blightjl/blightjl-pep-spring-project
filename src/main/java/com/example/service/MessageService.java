package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    @Autowired
    private AccountService accountService;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

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

    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }

    public Optional<Message> getMessageByID(int message_id) {
        return this.messageRepository.findById(message_id);
    }

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

    public boolean deleteMessage(int message_id) {
        if (!this.messageRepository.existsById(message_id)) {
            return false;
        }
        this.messageRepository.deleteById(message_id);
        return true;
    }

    public List<Message> getMessagesByAccountID(int account_id) {
        return this.messageRepository.findAllByPostedBy(account_id);
    }
}

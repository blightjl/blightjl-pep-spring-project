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
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    public Message addMessage(Message message) {
        return this.messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }

    public Optional<Message> getMessageByID(int message_id) {
        return this.messageRepository.findById(message_id);
    }

    public void updateMessage(int message_id, Message message) {
        Message updatedMessage = message;
        updatedMessage.setMessageId(message_id);
        this.messageRepository.save(updatedMessage);
    }

    public void deleteMessage(int message_id) {
        this.messageRepository.deleteById(message_id);
    }

    public List<Message> getMessagesByAccountID(int account_id) {
        return this.messageRepository.findAllByPostedBy(account_id);
    }
}

package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Message;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class MessageService {
    public Message addMessage(Message message) {
        return null;
    }

    public Optional<List<Message>> getAllMessages() {
        return Optional.empty();
    }

    public Optional<Message> getMessageByID(int message_id) {
        return Optional.empty();
    }

    public Optional<Message> updateMessage(int message_id, Message message) {
        return Optional.empty();
    }

    public Optional<Message> deleteMessage(int message_id) {
        return Optional.empty();
    }

    public Optional<List<Message>> getMessagesByAccountID(int account_id) {
        return Optional.empty();
    }
}

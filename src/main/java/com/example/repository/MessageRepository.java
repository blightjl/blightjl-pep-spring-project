package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

/**
 * The Message Repository to handle CRUD operations for the Message entity.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    /**
     * Custom method find all messages by the posted by attribute
     * @param postedBy the int value of the account id that made messages
     * @return a list of messages by the account identified by postedBy variable
     */
    List<Message> findAllByPostedBy(int postedBy);
    /**
     * Custom method to find whether a message identified by messageId exists
     * @param messageId the message id of message that is looked up
     * @return true if message exists, false otherwise
     */
    boolean existsByMessageId(String messageId);
}

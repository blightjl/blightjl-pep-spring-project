package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

/**
 * The AccountRepository to handle CRUD operations for the Account entity.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    /**
     * Custom method to find whether an account by username exists
     * @param username the username to search an account by
     * @return true if account by username exists, false otherwise
     */
    boolean existsByUsername(String username);
    /**
     * Custom method to find an account by account credentials 
     * @param username the username to find an account by
     * @param password the password to find an account by
     * @return true if the username and password combination works, false otherwise
     */
    Optional<Account> findByUsernameAndPassword(String username, String password);

}

package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

/** 
 * The Account Service that calls on the Account Repository to use business logic for the app.
 */
@Service
public class AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * This method adds an account with the given credential if the username and password credential provided is of
     * the appropriate length and the username does not exist in the database.
     *
     * @param account the transient account object {which does not contain the account id} only the username and password
     * @return the persisted account object if registering is successful, null orhterwise
     */
    public Account registerAccount(Account account) {
        return this.accountRepository.save(account);
    }

    /**
     * This method attempts to log in with an account with the given credential by calling the method
     * on the account repository.
     *
     * @param account the account to be authenticated
     * @return the account that was logged in if login was successful, null otherwise
     */
    public Account logIntoAccount(Account account) {
        Optional<Account> optionalAccount = this.accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (optionalAccount.isPresent()) {
            System.out.println(optionalAccount.get().toString());
            return optionalAccount.get();
        }
        return null;
    }

    /**
     * This method checks whether an account exists or not either by the provided account id
     *
     * @param account_id the account_id to be checked for existence in the database
     * @return true if account was found, false otherwise
     */
    public boolean accountExists(int account_id) {
        return this.accountRepository.existsById(account_id);
    }

    /**
     * This method checks whether a username already exists in the account repository or not
     *
     * @param username the username to look for an account in the account repository
     * @return true if account was found, false otherwise
     */
    public boolean usernameExists(String username) {
        return this.accountRepository.existsByUsername(username);
    }
}

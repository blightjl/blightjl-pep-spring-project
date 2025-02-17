package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account) {
        return this.accountRepository.save(account);
    }

    public Account logIntoAccount(Account account) {
        return this.accountRepository.save(account);
    }

    public boolean accountExists(String username, int account_id, boolean byUsername) {
        return this.accountRepository.existsById(account_id);
    }
}

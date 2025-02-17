package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Account;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class AccountService {
    public Account registerAccount(Account account) {
        return null;
    }

    public Account logIntoAccount(Account account) {
        return null;
    }

    public boolean accountExists(String username, int account_id, boolean byUsername) {
        return false;
    }
}

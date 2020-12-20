package com.service;

import com.entity.Account;
import com.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account getOne(String username) {
        return accountRepository.getOne(username);
    }
}
